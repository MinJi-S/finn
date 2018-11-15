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

    private DefaultExecutor excutor = null;
    private ByteArrayOutputStream baos = null;
    private PumpStreamHandler streamHandler = null;


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

    public void commandInit() {
        excutor = new DefaultExecutor();
        baos = new ByteArrayOutputStream();
        streamHandler = new PumpStreamHandler(baos);
    }

    public String getDeleteQuery(HttpServletRequest request) throws Exception {
        String path = this.applicationProperties.getPvsQueryPathDelete();

        // get parameter
        // homeCode, uuid
        String homeCode = request.getParameter("homeCode");
        String uuid = request.getParameter("uuid");

        this.commandInit();
        excutor.setStreamHandler(streamHandler);

        CommandLine cmdLine = CommandLine.parse(path);

        cmdLine.addArgument(homeCode, false);
        cmdLine.addArgument(uuid, false);

        System.out.println(">> /pvs/query/delete");
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

    public String getUserQuery(HttpServletRequest request) throws Exception {
        String path = this.applicationProperties.getPvsQueryPathUser();

        // get parameter
        // oneId, subsNo, homeName, subsType, custNo, svcCode
        String oneId = request.getParameter("oneId");
        String subsNo = request.getParameter("subsNo");
        String homeName = request.getParameter("homeName");
        String subsType = request.getParameter("subsType");
        String custNo = request.getParameter("custNo");
        String svcCode = request.getParameter("svcCode");

        this.commandInit();
        excutor.setStreamHandler(streamHandler);

        CommandLine cmdLine = CommandLine.parse(path);

        cmdLine.addArgument(oneId, false);
        cmdLine.addArgument(subsNo, false);
        cmdLine.addArgument(homeName, false);
        cmdLine.addArgument(subsType, false);
        cmdLine.addArgument(custNo, false);
        cmdLine.addArgument(svcCode, false);

        System.out.println(">> /pvs/query/user");
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

    public String getDeviceQuery(HttpServletRequest request) throws Exception {
        String path = this.applicationProperties.getPvsQueryPathDevice();

        // get parameter
        // modelNo, typeCode, mac, sn, homeCode, uuid, chsDeviceTypeLevel, deviceIdType
        String modelNo = request.getParameter("modelNo");
        String typeCode = request.getParameter("typeCode");
        String mac = request.getParameter("mac");
        String sn = request.getParameter("sn");
        String homeCode = request.getParameter("homeCode");
        String uuid = request.getParameter("uuid");
        String chsDeviceTypeLevel = request.getParameter("chsDeviceTypeLevel");
        String deviceIdType = request.getParameter("deviceIdType");

        this.commandInit();
        excutor.setStreamHandler(streamHandler);

        CommandLine cmdLine = CommandLine.parse(path);

        cmdLine.addArgument(modelNo, false);
        cmdLine.addArgument(typeCode, false);
        cmdLine.addArgument(mac, false);
        cmdLine.addArgument(sn, false);
        cmdLine.addArgument(homeCode, false);
        cmdLine.addArgument(uuid, false);
        cmdLine.addArgument(chsDeviceTypeLevel, false);
        cmdLine.addArgument(deviceIdType, false);

        System.out.println(">> /pvs/query/device");
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
