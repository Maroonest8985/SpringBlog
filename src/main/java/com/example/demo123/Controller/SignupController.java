package com.example.demo123.Controller;


import com.example.demo123.DAO.SignupDAO;
import com.example.demo123.DTO.SignupDTO;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignupController {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @PostMapping("/signup")
    public ModelAndView signupForm(HttpSession session, HttpServletRequest request){
        SignupDTO signupDTO = new SignupDTO();
        String email = request.getParameter("email");
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        signupDTO.setEmail(email);
        signupDTO.setId(id);
        signupDTO.setPwd(pwd);
        ModelAndView mav = new ModelAndView();
        session.setAttribute("memberID", signupDTO.getId());
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            ss.insert("insertMember", signupDTO);
            mav.setViewName("redirect:/signupDone");
        } catch (SqlSessionException e) {
            e.printStackTrace();
            mav.setViewName("error");
        }finally {
            ss.close();
        }
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

    @PostMapping("/checkid")
    @ResponseBody
    public int checkid(HttpServletRequest request){
        String id = request.getParameter("userid");
        String status = null;
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            status = ss.selectOne("checkId", id);
        } catch (SqlSessionException e) {
            e.printStackTrace();
        }finally {
            ss.close();
        }

        if(status != null){
            return 0;
        }else{
            return 1;
        }
    }

}
