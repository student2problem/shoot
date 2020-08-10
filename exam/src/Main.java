import com.zzxx.exam.controller.ClientContext;
import com.zzxx.exam.entity.EntityContext;
import com.zzxx.exam.entity.ExamInfo;
import com.zzxx.exam.entity.Question;
import com.zzxx.exam.entity.QuestionInfo;
import com.zzxx.exam.service.ExamService;
import com.zzxx.exam.ui.*;
import com.zzxx.exam.util.Config;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        MenuFrame menuFram = new MenuFrame();
        ExamService service = new ExamService();
        ClientContext controller = new ClientContext();
        EntityContext  entityContext = new EntityContext();
        MsgFrame msgFrame = new MsgFrame();
        ExamFrame examFrame = new ExamFrame();
        Config config = new Config("config.properties");
        ResultFrame resultFrame = new ResultFrame();

    //注入依赖
        controller.setLoginFrame(loginFrame);
        controller.setMenuFrame(menuFram);
        loginFrame.setController(controller);
        controller.setService(service);
        service.setEntityContext(entityContext);
        controller.setMsgFrame(msgFrame);
        msgFrame.setController(controller);
        controller.startShow();
        menuFram.setController(controller);
        controller.setExamFrame(examFrame);
        controller.setConfig(config);
        config.setController(controller);
        config.setService(service);
        service.setConfig(config);
        examFrame.setController(controller);
        resultFrame.setController(controller);
        controller.setResultFrame(resultFrame);



    }
}
