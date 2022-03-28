package com.example.demo123.Controller;
import com.example.demo123.DAO.LoginDAO;
import com.example.demo123.DTO.LoginDTO;
import com.example.demo123.DTO.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController{
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @PostMapping("/login")
    public ModelAndView login(LoginDAO loginDAO, HttpSession session, HttpServletRequest request){
        SqlSession ss = sqlSessionFactory.openSession();
        LoginDTO loginDTO = new LoginDTO();
        MemberDTO memberDTO = new MemberDTO();
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("pwd", pwd);
        try{
            memberDTO = ss.selectOne("loginMember", map);
        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally{
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
        SqlSession ss = sqlSessionFactory.openSession();
        String id = request.getParameter("userid");
        String pwd = request.getParameter("password");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("pwd", pwd);
        try{
            memberDTO = ss.selectOne("loginMember", map);
        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally{
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
        session.invalidate();
        return mav;
    }
}