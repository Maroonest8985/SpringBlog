package com.example.demo123.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import com.example.demo123.DTO.SignupDTO;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("data", "this is home");
        model.addAttribute("navbar", "home");
        model.addAttribute("signupDTO", new SignupDTO());
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("data", "this is about page");
        model.addAttribute("navbar", "about");
        model.addAttribute("signupDTO", new SignupDTO());

        return "index";
    }
    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("data", "this is contact page");
        model.addAttribute("navbar", "contact");
        model.addAttribute("signupDTO", new SignupDTO());

        return "index";
    }
    @GetMapping("/error")
    public String error(){
        return "error";
    }
}


