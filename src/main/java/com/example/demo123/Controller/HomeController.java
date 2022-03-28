package com.example.demo123.Controller;

import com.example.demo123.DTO.PostDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Service;

import com.example.demo123.DTO.SignupDTO;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @GetMapping("/home")
    public ModelAndView home(PostDTO postDTO, Model model){
        SqlSession ss = sqlSessionFactory.openSession();

        model.addAttribute("data", "this is home");
        model.addAttribute("navbar", "home");
        model.addAttribute("signupDTO", new SignupDTO());
        List<PostDTO> arrPostDTO = new ArrayList<>();
        try{
            arrPostDTO = ss.selectList("getListPost");
        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("arrPostDTO", arrPostDTO);
        return mav;
    }

    @GetMapping("/")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView("redirect:/home");
        return mav;
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


