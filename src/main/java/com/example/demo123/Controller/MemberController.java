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
        return new ModelAndView("member-info");
    }

    @GetMapping("/delete-member")
    public ModelAndView deleteMember(HttpSession session){
        int no = (int) session.getAttribute("member_no");
        ModelAndView mav = new ModelAndView("member-delete");
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            ss.delete("deleteMember", no);
            session.invalidate();
            mav = new ModelAndView("member-delete");
        }catch(SqlSessionException e){
            e.printStackTrace();
            mav = new ModelAndView("error");
        }finally{
            ss.close();
        }
        return mav;
    }
}
