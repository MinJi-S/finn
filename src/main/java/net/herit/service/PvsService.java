package net.herit.service;

import net.herit.dto.ChsDeviceModelDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PvsService {
	
	String getDemo() throws Exception;
	List<ChsDeviceModelDto> getChsDeviceModel() throws Exception;
	String getDeleteQuery(HttpServletRequest request) throws Exception;
	String getUserQuery(HttpServletRequest request) throws Exception;
	String getDeviceQuery(HttpServletRequest request) throws Exception;
}