package com.example.demo123.Controller;
import com.example.demo123.DAO.LoginDAO;
import com.example.demo123.DTO.LoginDTO;
import com.example.demo123.DTO.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController{
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @PostMapping("/login")
    public ModelAndView login(LoginDAO loginDAO, HttpSession session, HttpServletRequest request){
        LoginDTO loginDTO = new LoginDTO();
        MemberDTO memberDTO = new MemberDTO();
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("pwd", pwd);
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd  HH:mm:ss"));
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            memberDTO = ss.selectOne("loginMember", map);
            System.out.println(id+" has logined." + formattedNow);
        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally {
            ss.close();
        }
        ModelAndView mav = new ModelAndView("redirect:/home");
        ModelAndView maverr = new ModelAndView("redirect:/error");
        if(memberDTO != null){
            int m_no = memberDTO.getNo();
            String m_id = memberDTO.getId();
            String m_email = memberDTO.getEmail();
            String m_pwd = memberDTO.getPwd();
            session.setAttribute("member_id", m_id);
            session.setAttribute("member_no", m_no);
            session.setAttribute("member_email", m_email);
            session.setAttribute("member_pwd", m_pwd);
            return mav;
        }else{
            return maverr;
        }
    }

    @PostMapping("/checklogin") //for login ajax checking
    @ResponseBody
    public int checklogin(HttpServletRequest request){
        MemberDTO memberDTO = new MemberDTO();
        String id = request.getParameter("userid");
        String pwd = request.getParameter("password");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("pwd", pwd);
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            memberDTO = ss.selectOne("loginMember", map);
        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally {
            ss.close();
        }

        if(memberDTO != null){
            return 1;
        }else{
            return 0;
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView mav = new ModelAndView("redirect:/home");
        String id = (String) session.getAttribute("member_id");
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd  HH:mm:ss"));
        session.invalidate();
        System.out.println(id+" has logout." + formattedNow);
        return mav;
    }
}