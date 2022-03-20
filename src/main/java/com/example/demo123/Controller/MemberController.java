package com.example.demo123.Controller;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
    @Autowired
    SqlSession ss;

    @GetMapping("/memberinfo")
    public ModelAndView memberInfo(Model model){
        ModelAndView mav = new ModelAndView("member-info");
        return mav;
    }
}