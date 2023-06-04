package tv.memoryleakdeath.greenmail.gui.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController extends BaseFrontendController {

    @GetMapping("/")
    public String view(HttpServletRequest request, Model model) {
        setPageTitle(request, model, "title.home");
        addCommonModelAttributes(model);
        model.addAttribute("helloMsg", "Hello World!");
        addSuccessMessage(model, "text.success.ok");
        return "index";
    }

}
