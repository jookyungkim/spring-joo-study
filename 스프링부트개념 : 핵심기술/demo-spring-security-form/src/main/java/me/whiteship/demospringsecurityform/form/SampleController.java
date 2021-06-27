package me.whiteship.demospringsecurityform.form;

import me.whiteship.demospringsecurityform.account.Account;
import me.whiteship.demospringsecurityform.account.AccountRepository;
import me.whiteship.demospringsecurityform.account.UserAccount;
import me.whiteship.demospringsecurityform.book.BookRepository;
import me.whiteship.demospringsecurityform.comon.CurrentUser;
import me.whiteship.demospringsecurityform.comon.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account ) {

        //SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if(account == null) {
            model.addAttribute("message", "Hello String Securtiy");
        } else {
            model.addAttribute("message", "Hello, "+account.getUsername());
        }

        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @CurrentUser Account account ) {
        model.addAttribute("message", "dashboard, "+account.getUsername());
        sampleService.dashboard();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, @CurrentUser Account account ) {
        model.addAttribute("message", "admin, "+account.getUsername());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, @CurrentUser Account account) {
        model.addAttribute("message", "user, "+account.getUsername());
        model.addAttribute("books", bookRepository.findCurrentUserBooks());
        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> stringCallable() {

        SecurityLogger.log("MVC");
        return () -> {

            SecurityLogger.log("callable");
            return "Async Handler";
        };
    }

    @GetMapping("/async-setvice")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, before async service");
        return "Async Setvice";
    }
}
