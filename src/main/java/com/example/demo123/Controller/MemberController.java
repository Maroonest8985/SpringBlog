package com.example.demo123.Controller;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @GetMapping("/memberinfo")
    public ModelAndView memberInfo(Model model){
        ModelAndView mav = new ModelAndView("member-info");
        return mav;
    }

    @GetMapping("/delete-member")
    public ModelAndView deleteMember(HttpSession session){
        SqlSession ss = sqlSessionFactory.openSession();
        int no = (int) session.getAttribute("member_no");
        try{
            ss.delete("deleteMember", no);
        }catch(SqlSessionException e){
            e.printStackTrace();
            ModelAndView mav = new ModelAndView("error");
            return mav;
        }finally{
            ModelAndView mav = new ModelAndView("member-delete");
            session.invalidate();
            ss.close();
            return mav;
        }
    }
}
