package net.herit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.herit.config.ApplicationProperties;
import net.herit.service.MsgService;
import net.herit.service.PvsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource(name="msgSvc")
    private MsgService msgService;

	@ApiOperation(value = "디렉토리 및 파일 리스트 조회")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "info", value = "디렉토리 명(dir, ags, pas, iss, eps, etc)", required = true, dataType = "string", paramType = "path", defaultValue = ""),
	})
	@CrossOrigin(origins="*")
	@RequestMapping(method=RequestMethod.GET, value="/msg/server/list")
	public String list(HttpServletRequest request) throws Exception {
		
        // get parameter
		String parameter = request.getParameter("info");
		String cmd = "";

		if("dir".equals(parameter)) {
			cmd = "ls " + applicationProperties.getMsgBasePath();
		} else {
			cmd = "ls " + applicationProperties.getMsgBasePath() + parameter;
		}

        String output = msgService.commandExc(cmd);

        ArrayList<String> outputList = new ArrayList<String>();
        String[] split = output.toString().split("\n");

        for(int i=0; i < split.length; i++) {
            if(!"".equals(split[i])) {
                outputList.add(i, split[i]);
            }
        }
        // response
		ObjectMapper responseMapper = new ObjectMapper();
		String responseJson = responseMapper.writeValueAsString(outputList/*resultMap*/);
		
		return responseJson;
	}

	@ApiOperation(value = "API 전문 정보 조회")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "info", value = "디렉토리 명(ags, pas, iss, eps, etc)", required = true, dataType = "string", paramType = "path", defaultValue = "etc"),
			@ApiImplicitParam(name = "filename", value = "info 디렉토리 하위에 있는 파일명", required = true, dataType = "string", paramType = "path", defaultValue = "R2D2_postman_collection.json"),
	})
    @CrossOrigin(origins="*")
    @RequestMapping(method=RequestMethod.GET, value="/msg/server/json")
    public String json(HttpServletRequest request) throws Exception {

		// get parameter
		String parameterInfo = request.getParameter("info");
		String parameterFilename = request.getParameter("filename");

		System.out.println(parameterInfo);
		System.out.println(parameterFilename);

		String cmd = "";

		cmd = "cat " + applicationProperties.getMsgBasePath() + parameterInfo + "/" + parameterFilename;

		String output = msgService.commandExc(cmd);

        return output;
    }
}
