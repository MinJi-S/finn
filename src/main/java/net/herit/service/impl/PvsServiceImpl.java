package net.herit.service.impl;

import net.herit.dao.PvsDao;
import net.herit.dto.ChsDeviceModelDto;
import net.herit.service.PvsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("pvsSvc")
public class PvsServiceImpl implements PvsService {

    @Resource(name="pvsDao")
    private PvsDao pvsDao;

    public String getDemo() throws Exception {
        return "demo";
    }

    public List<ChsDeviceModelDto> getChsDeviceModel() throws Exception {
        return pvsDao.selectChsDeviceModel();
    }
}
