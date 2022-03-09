package com.example.demo123.Controller;


import com.example.demo123.DAO.SignupDAO;
import com.example.demo123.DTO.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {
    @Autowired
    SqlSession ss;

    @PostMapping("/signup")
    public ModelAndView signupForm(@ModelAttribute("signupDTO") SignupDTO signupDTO, HttpSession session){
        SignupDAO signupDAO = null;
        ModelAndView mav = new ModelAndView("redirect:/signupDone");
        session.setAttribute("memberID", signupDTO.getId());
        ss.insert("insertMember", signupDTO);
        return mav;
    }

    @GetMapping("/signupDone")
    public ModelAndView signupDone(Model model, HttpSession session){
        ModelAndView mav = new ModelAndView();
        session.getAttribute("memberID");
        mav.addObject("signupDTO", new SignupDTO());
        mav.setViewName("signup-done");
        return mav;
    }

}