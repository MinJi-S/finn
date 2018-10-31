package net.herit.domain;

import lombok.Data;

@Data
public class ChsDeviceModelDto {
	String id;
	String chsDeviceTypeCode;
	String name;
	String modelNo;
	String devcMdlGrpCd;
	String devcMdlGrpNm;
	String devcMdlCd;
	String devcMdlNm;
	String modelVendor;
	String modelVendorCd;
	String disabled;
	String source;
	String platformApiVer;
	String connType;
	String pvType;
	String subType;
	String createTime;
    String updateTime;
}