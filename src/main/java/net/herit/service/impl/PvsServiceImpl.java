package net.herit.service.impl;

import net.herit.config.ApplicationProperties;
import net.herit.dao.PvsDao;
import net.herit.dto.ChsDeviceModelDto;
import net.herit.service.PvsService;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service("pvsSvc")
public class PvsServiceImpl implements PvsService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Resource(name="pvsDao")
    private PvsDao pvsDao;

    public String getDemo() throws Exception {
        return "demo";
    }

    public List<ChsDeviceModelDto> getChsDeviceModel() throws Exception {
        return pvsDao.selectChsDeviceModel();
    }

    public String getQuery(String operation, HttpServletRequest request) throws Exception {

        // get parameter
        String modelNo = request.getParameter("modelNo");
        String typeCode = request.getParameter("typeCode");
        String mac = request.getParameter("mac");
        String sn = request.getParameter("sn");
        String homeCode = request.getParameter("homeCode");
        String uuid = request.getParameter("uuid");

        DefaultExecutor excutor = new DefaultExecutor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(baos);

        excutor.setStreamHandler(streamHandler);

        String path = "";

        switch (operation) {
            case "delete":      // 사용자/단말초기화
                path = this.applicationProperties.getPvsQueryPathDelete();
                break;
            case "user":        // 사용자 등록
                path = this.applicationProperties.getPvsQueryPathUser();
                break;
            case "device":      // 단말 등록
                path = this.applicationProperties.getPvsQueryPathDevice();
                break;
        }

        CommandLine cmdLine = CommandLine.parse(path);

        cmdLine.addArgument(modelNo, false);
        cmdLine.addArgument(typeCode, false);
        cmdLine.addArgument(mac, false);
        cmdLine.addArgument(sn, false);
        cmdLine.addArgument(homeCode, false);
        cmdLine.addArgument(uuid, false);

        System.out.println(">> /pvs/query/" + operation);
        System.out.println(">> path = " + path);
        System.out.println(">> cmdLine argument = " + cmdLine.toString());

        int exitCode = excutor.execute(cmdLine);
        System.out.println(">> exitCode = " + Integer.valueOf(exitCode));

        String resultString = baos.toString(StandardCharsets.UTF_8.name());
        // resultString = resultString.replaceAll("(\\r|\\n)", "");
        resultString = resultString.replaceAll("\\r", "");			// \\n은 하지말자 이쁘게 출력하기 위함

        System.out.println(">> resultString = " + resultString);

        return resultString;
    }
}
