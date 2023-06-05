package tv.memoryleakdeath.greenmail.gui.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icegreen.greenmail.store.MailFolder;
import com.icegreen.greenmail.user.GreenMailUser;

import jakarta.servlet.http.HttpServletRequest;
import tv.memoryleakdeath.greenmail.gui.backend.GreenmailServerService;
import tv.memoryleakdeath.greenmail.gui.frontend.controller.model.MailUserDataModel;

@Controller
@RequestMapping("/email")
public class EmailViewController extends BaseFrontendController {

    @Autowired
    private GreenmailServerService greenmailService;

    @PostMapping("/view")
    public String viewUserEmail(HttpServletRequest request, Model model, @RequestParam(name = "email", required = true) String userEmail) {
        setPageTitle(request, model, "text.email.title.view");
        addCommonModelAttributes(model);
        model.addAttribute("userData", getUserData(userEmail));
        return "view-email";
    }

    private MailUserDataModel getUserData(String email) {
        GreenMailUser user = greenmailService.getUser(email);
        MailFolder inbox = greenmailService.getUserInbox(user);
        MailUserDataModel model = new MailUserDataModel();
        model.setEmail(email);
        model.setTotalInboxMessages(inbox.getMessageCount());
        model.setInboxMessages(inbox.getMessages());
        return model;
    }
}
