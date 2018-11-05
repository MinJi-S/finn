package net.herit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.herit.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class MsgController {
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@CrossOrigin(origins="*")		// 글로벌 크로스 셋팅....... 모든 외부 도메인에서 요청 처리 허가
	@RequestMapping(method=RequestMethod.GET, value="/msg/server/list")
	public String list(HttpServletRequest request) throws Exception {
		
		List<String> chsDeviceModelDtoList = new ArrayList<>();
		
		/**
		 * cmd 명령어 실행
		 */
		Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer(); 	// 성공 스트링 버퍼
        StringBuffer errorOutput = new StringBuffer(); 		// 오류 스트링 버퍼
        StringBuffer output = new StringBuffer(); 			// output 스트링 버퍼
        BufferedReader successBufferReader = null; 			// 성공 버퍼
        BufferedReader errorBufferReader = null; 			// 오류 버퍼
        String msg = null; 									// 메시지
 
        List<String> cmdList = new ArrayList<String>();
 
        // 운영체제 구분 (window, window 가 아니면 무조건 linux 로 판단)
        if (System.getProperty("os.name").indexOf("Windows") > -1) {
            cmdList.add("cmd");
            cmdList.add("/c");
        } else {
            cmdList.add("/bin/sh");
            cmdList.add("-c");
        }
        
        // get parameter
		String parameter = request.getParameter("info");
		String cmd = "";
		
		if("dir".equals(parameter)) {
			cmd = "ls " + applicationProperties.getMsgBasePath();
		} else {
			cmd = "ls " + applicationProperties.getMsgBasePath() + parameter;
		}
		
		// 명령어 셋팅
        cmdList.add(cmd);
        
        String[] array = cmdList.toArray(new String[cmdList.size()]);
        
        System.out.println("[/msg/server/list] >> process runtime Excute.");
        
        for(int i=0; i<array.length; i++) {
        	System.out.println("> " + array[i]);
        }
        
        try {
 
            // 명령어 실행
            process = runtime.exec(array);
 
            // shell 실행이 정상 동작했을 경우
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
 
            while ((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg + System.getProperty("line.separator"));
            }
 
            // shell 실행시 에러가 발생했을 경우
            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
            while ((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg + System.getProperty("line.separator"));
            }
 
            // 프로세스의 수행이 끝날때까지 대기
            process.waitFor();
 
            // shell 실행이 정상 종료되었을 경우
            if (process.exitValue() == 0) {
                System.out.println("[/msg/server/list] >> 성공");
                System.out.println(successOutput.toString());
                output = successOutput;
            } else {
                // shell 실행이 비정상 종료되었을 경우
                System.out.println("[/msg/server/list] >> 비정상 종료");
                System.out.println(errorOutput.toString());
                output = errorOutput;
            }
 
            // shell 실행시 에러가 발생
            if (!errorOutput.toString().isEmpty()) {
                // shell 실행이 비정상 종료되었을 경우
                System.out.println("[/msg/server/list] >> 오류");
                System.out.println(errorOutput.toString());
                output.append("\n");
                output.append(errorOutput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                process.destroy();
                if (successBufferReader != null) successBufferReader.close();
                if (errorBufferReader != null) errorBufferReader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    
        // response
		ObjectMapper responseMapper = new ObjectMapper();
		String responseJson = responseMapper.writeValueAsString(output/*resultMap*/);
		
		return responseJson;
	}
}
