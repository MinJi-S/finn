package net.herit.service.impl;

import net.herit.dto.ChsDeviceModelDto;
import net.herit.service.PvsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pvsSvc")
public class PvsServiceImpl implements PvsService {

    public String getDemo() throws Exception {
        return "demo";
    }

    public List<ChsDeviceModelDto> getChsDeviceModel() throws Exception {


        return null;
    }
}
