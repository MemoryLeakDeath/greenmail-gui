package tv.memoryleakdeath.greenmail.gui.frontend.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icegreen.greenmail.store.MailFolder;
import com.icegreen.greenmail.user.GreenMailUser;

import jakarta.servlet.http.HttpServletRequest;
import tv.memoryleakdeath.greenmail.gui.backend.GreenmailServerService;
import tv.memoryleakdeath.greenmail.gui.frontend.controller.model.MailUserDataModel;
import tv.memoryleakdeath.greenmail.gui.frontend.controller.model.MailUsersModel;

@Controller
@RequestMapping("/")
public class HomeController extends BaseFrontendController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private GreenmailServerService greenmailService;

    @GetMapping("/")
    public String view(HttpServletRequest request, Model model) {
        setPageTitle(request, model, "title.home");
        addCommonModelAttributes(model);
        model.addAttribute("userData", getUserData());
        return "index";
    }

    private MailUsersModel getUserData() {
        MailUsersModel allUsersModel = new MailUsersModel();
        Collection<GreenMailUser> users = greenmailService.getUsers();
        List<MailUserDataModel> allUsersDataModelList = new ArrayList<>();
        for (GreenMailUser user : users) {
            MailUserDataModel userDataModel = new MailUserDataModel();
            userDataModel.setEmail(user.getEmail());
            MailFolder userInbox = greenmailService.getUserInbox(user);
            userDataModel.setTotalInboxMessages(userInbox.getMessageCount());
            allUsersDataModelList.add(userDataModel);
        }
        allUsersModel.setUserData(allUsersDataModelList);
        return allUsersModel;
    }

}
