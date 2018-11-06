package net.herit.service;

import net.herit.dto.ChsDeviceModelDto;

import java.util.List;

public interface PvsService {
	
	String getDemo() throws Exception;
	List<ChsDeviceModelDto> getChsDeviceModel() throws Exception;
	
}