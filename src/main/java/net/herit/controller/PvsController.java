package net.herit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.herit.dto.ChsDeviceModelDto;
import net.herit.service.PvsService;
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

	// @Autowired
	@Resource(name="pvsSvc")
	private PvsService pvsService;

	/**
	 * CHS_DEVICE_MODEL 검색
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "CHS_DEVICE_MODEL 테이블 조회")
	@CrossOrigin(origins = "*")		// 글로벌 크로스 셋팅....... 모든 외부 도메인에서 요청 처리 허가
	@RequestMapping(method = RequestMethod.GET, value = "/pvs/select_chs_device_model")
	public String pvs(HttpServletRequest request) throws Exception {

		List<ChsDeviceModelDto> chsDeviceModelDtoList = new ArrayList<>();

		chsDeviceModelDtoList = pvsService.getChsDeviceModel();

		// Response
		ObjectMapper responseMapper = new ObjectMapper();
		String responseJson = responseMapper.writeValueAsString(chsDeviceModelDtoList/*resultMap*/);

		return responseJson;
	}

	@ApiOperation(value = "청약 사용자 및 단말 초기화 생성 쿼리")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "homeCode", value = "HOME_CODE OR SUBS_NO", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "uuid", value = "DEVICE UUID", required = true, dataType = "string", paramType = "path"),
	})
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/pvs/query/delete")
	public String queryDelete(HttpServletRequest request) throws Exception {

		ObjectMapper responseMapper = new ObjectMapper();
		String responseJson = responseMapper.writeValueAsString(pvsService.getDeleteQuery(request));
		return responseJson;
	}

	@ApiOperation(value = "사용자 생성 쿼리")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "one_id", value = "ONE_ID", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "subsNo", value = "SUBS_NO", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "homeName", value = "HOME_NAME", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "subsType", value = "SUBS_TYPE", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "custNo", value = "CUST_NO", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "svcCode", value = "SVC_CODE", required = true, dataType = "string", paramType = "path"),
	})
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/pvs/query/user")
	public String queryUser(HttpServletRequest request) throws Exception {
		ObjectMapper responseMapper = new ObjectMapper();
		String responseJson = responseMapper.writeValueAsString(pvsService.getUserQuery(request));
		return responseJson;
	}

	@ApiOperation(value = "단말 생성 쿼리")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "modelNo", value = "MODEL_NO", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "typeCode", value = "TYPE_CODE", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "mac", value = "MAC", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "sn", value = "SN", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "homeCode", value = "HOME_CODE", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "uuid", value = "UUID", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "chsDeviceTypeLevel", value = "CHS_DEVICE_TYPE_MODEL", required = true, dataType = "string", paramType = "path", defaultValue = "0"),
			@ApiImplicitParam(name = "deviceIdType", value = "DEVICE_ID_TYPE", required = true, dataType = "string", paramType = "path", defaultValue = "1"),
	})
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/pvs/query/device")
	public String queryDevice(HttpServletRequest request) throws Exception {
		ObjectMapper responseMapper = new ObjectMapper();
		String responseJson = responseMapper.writeValueAsString(pvsService.getDeviceQuery(request));
		return responseJson;
	}
}