package com.example.demo123.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping("/signup")
    public ModelAndView signup(@ModelAttribute SignupDAO signupDAO, HttpServletRequest request){

        return ModelAndView ;
    }
}


