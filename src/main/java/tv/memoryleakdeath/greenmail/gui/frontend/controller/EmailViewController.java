package tv.memoryleakdeath.greenmail.gui.frontend.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icegreen.greenmail.store.MailFolder;
import com.icegreen.greenmail.store.StoredMessage;
import com.icegreen.greenmail.user.GreenMailUser;

import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.servlet.http.HttpServletRequest;
import tv.memoryleakdeath.greenmail.gui.backend.GreenmailServerService;
import tv.memoryleakdeath.greenmail.gui.frontend.controller.model.MailMessageModel;
import tv.memoryleakdeath.greenmail.gui.frontend.controller.model.MailUserDataModel;

@Controller
@RequestMapping("/email")
public class EmailViewController extends BaseFrontendController {
	private static final Logger logger = LoggerFactory.getLogger(EmailViewController.class);

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
        model.setInboxMessages(buildMailMessages(inbox.getMessages()));
        return model;
    }
    
    private List<MailMessageModel> buildMailMessages(List<StoredMessage> storedMessages) {
    	List<MailMessageModel> messages = new ArrayList<>();
    	for(StoredMessage message : storedMessages) {
    		MailMessageModel inboxMessage = new MailMessageModel();
    		try {
				inboxMessage.setFrom(getAddress((InternetAddress) message.getMimeMessage().getFrom()[0]));
				inboxMessage.setReceivedDate(message.getReceivedDate());
				inboxMessage.setSubject(message.getMimeMessage().getSubject());
				inboxMessage.setBody(getBody(message.getMimeMessage()));
			} catch (MessagingException e) {
				logger.error("Unable to parse email!");
			}
    		messages.add(inboxMessage);
    	}
    	return messages;
    }
    
    private String getAddress(InternetAddress address) {
    	return address.getAddress();
    }
    
    private String getBody(MimeMessage message) {
    	try {
			Object content = message.getContent();
			if(content instanceof String s) {
				return s;
			} else if (content instanceof MimeMultipart multi) {				
				BodyPart bodyPart = multi.getBodyPart(0);
				return String.valueOf(bodyPart.getContent());
			} else {
				logger.error("Unknown mail message format encountered!");
			}
		} catch (IOException | MessagingException e) {
			logger.error("Unable to parse email!");
		}
    	return null;
    }
}
