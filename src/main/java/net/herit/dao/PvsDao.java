package net.herit.dao;

import net.herit.dto.ChsDeviceModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("pvsDao")
public class PvsDao {

    @Autowired
    @Qualifier("jdbc")
    private JdbcTemplate jdbcTemplate;

    public List<ChsDeviceModelDto> selectChsDeviceModel() {
        // return getSqlSession().selectList("net.herit.mapper.controller.selectSchedulingInfoData");

        String sql = "SELECT ID, CHS_DEVICE_TYPE_CODE, NAME, MODEL_NO, DEVC_MDL_GRP_CD, DEVC_MDL_GRP_NM, DEVC_MDL_CD, DEVC_MDL_NM, MODEL_VENDOR, MODEL_VENDOR_CD, DISABLED, SOURCE, PLATFORM_API_VER, CONN_TYPE, PV_TYPE, SUBTYPE, CREATE_TIME, UPDATE_TIME "
                    + "FROM CHS_DEVICE_MODEL";

        return jdbcTemplate.query(sql.toString(), new Object[] {}, new RowMapper<ChsDeviceModelDto>(){
            @Override
            public ChsDeviceModelDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChsDeviceModelDto result = new ChsDeviceModelDto();
                result.setId(rs.getString(1));
                result.setChsDeviceTypeCode(rs.getString(2));
                result.setName(rs.getString(3));
                result.setModelNo(rs.getString(4));
                result.setDevcMdlGrpCd(rs.getString(5));
                result.setDevcMdlGrpNm(rs.getString(6));
                result.setDevcMdlCd(rs.getString(7));
                result.setDevcMdlNm(rs.getString(8));
                result.setModelVendor(rs.getString(9));
                result.setModelVendorCd(rs.getString(10));
                result.setDisabled(rs.getString(11));
                result.setSource(rs.getString(12));
                result.setPlatformApiVer(rs.getString(13));
                result.setConnType(rs.getString(14));
                result.setPvType(rs.getString(15));
                result.setSubType(rs.getString(16));
                result.setCreateTime(rs.getString(17));
                result.setUpdateTime(rs.getString(18));
                return result;
            }
        });
    }
}
