package com.example.demo123.Controller;


import com.example.demo123.DTO.SignupDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping("/post")
    public String post(Model model){
        model.addAttribute("signupDTO", new SignupDTO());
        return "post";
    }

}
