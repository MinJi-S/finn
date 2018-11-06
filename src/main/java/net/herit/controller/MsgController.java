package net.herit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        StringBuffer output = msgService.commandExc(cmd);

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

		StringBuffer output = msgService.commandExc(cmd);

        return output.toString();
    }
}
