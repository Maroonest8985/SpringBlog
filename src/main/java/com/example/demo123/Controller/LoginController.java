package com.example.demo123.Controller;
import com.example.demo123.DAO.LoginDAO;
import com.example.demo123.DTO.LoginDTO;
import com.example.demo123.DTO.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController{
    @Autowired
    SqlSession ss;

    @PostMapping("/login")
    public ModelAndView login(LoginDAO loginDAO, HttpSession session, HttpServletRequest request){
        LoginDTO loginDTO = new LoginDTO();
        MemberDTO memberDTO = new MemberDTO();
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("pwd", pwd);
        memberDTO = ss.selectOne("loginMember", map);
        ModelAndView mav = new ModelAndView("redirect:/home");
        ModelAndView maverr = new ModelAndView("redirect:/error");
        if(memberDTO != null){
            int m_no = memberDTO.getNo();
            String m_id = memberDTO.getId();
            String m_email = memberDTO.getEmail();
            String m_pwd = memberDTO.getPwd();
            session.setAttribute("member_id", m_id);
            return mav;
        }else{
            return maverr;
        }
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView mav = new ModelAndView("redirect:/home");
        session.invalidate();
        return mav;
    }
}