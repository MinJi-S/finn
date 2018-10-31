package net.herit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.herit.domain.ChsDeviceModelDto;
import net.herit.mapper.PvsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

// @Controller		// .jsp와 매핑할 수 있다.
@RestController		// 데이터값을 바로 출력할 수 있다.
@Slf4j
@EnableAutoConfiguration
public class PvsController {

	@Autowired
	private PvsMapper pvsMapper;

	/**
	 * CHS_DEVICE_MODEL 검색
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins="*")		// 글로벌 크로스 셋팅....... 모든 외부 도메인에서 요청 처리 허가
	@RequestMapping(method=RequestMethod.GET, value="/pvs/select_chs_device_model")
	public String pvs(HttpServletRequest request) throws Exception {

		List<ChsDeviceModelDto> chsDeviceModelDtoList = new ArrayList<>();

		// get parameter
		// String parameterDbName = request.getParameter("db");

		chsDeviceModelDtoList = pvsMapper.getChsDeviceModel();

		// Response
		// HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// resultMap.put("result", chsDeviceModelDtoList);

		ObjectMapper responseMapper = new ObjectMapper();
		String responseJson = responseMapper.writeValueAsString(chsDeviceModelDtoList/*resultMap*/);

		return responseJson;
	}
}