package com.example.demo123.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("data", "this is home");
        model.addAttribute("navbar", "home");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("data", "this is about page");
        model.addAttribute("navbar", "about");
        return "index";
    }
    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("data", "this is contact page");
        model.addAttribute("navbar", "contact");
        return "index";
    }
    @GetMapping("/blog")
    public String blog(Model model){
        model.addAttribute("data", "this is blog page");
        model.addAttribute("navbar", "blog");
        return "index";
    }
}


