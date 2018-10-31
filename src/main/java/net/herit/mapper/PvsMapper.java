package net.herit.mapper;

import net.herit.domain.ChsDeviceModelDto;

import java.util.List;

public interface PvsMapper {
	
	String getDemo() throws Exception;
	
	List<ChsDeviceModelDto> getChsDeviceModel() throws Exception;
	
}