package com.example.demo123.Controller;


import com.example.demo123.DTO.PostDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @GetMapping("/newpost")
    public String newPost(){
        return "newpost";
    }

    @PostMapping("/uploadpost")
    public ModelAndView insertPost(HttpServletRequest request, HttpSession session){
        int member_no = (int) session.getAttribute("member_no");
        PostDTO postDTO = new PostDTO();
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String text1 = text.replace("\r\n", "<br>");
        String img = "img"; //temporary value
        postDTO.setMember_no(member_no);
        postDTO.setTitle(title);
        postDTO.setText(text1);
        postDTO.setImg(img);
        ModelAndView mav = new ModelAndView();
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            ss.insert("insertPost", postDTO);
            mav.setViewName("redirect:/home");
        }catch(SqlSessionException e){
            e.printStackTrace();
            mav.setViewName("redirect:/error");
        }finally {
            ss.close();
        }
        return mav;

    }

    @GetMapping("/deletePost")
    public ModelAndView deletePost(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String post_no = request.getParameter("post_no");
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            ss.delete("deletePost", post_no);
            mav.setViewName("redirect:/home");
        }catch(SqlSessionException e){
            e.printStackTrace();
            mav.setViewName("error");
        }finally {
            ss.close();
        }
        return mav;
    }


    @GetMapping("/readpost") // no post page add
    public ModelAndView readPost(PostDTO postDTO, Model model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("post");
        String post_no = request.getParameter("post_no");
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            postDTO = ss.selectOne("selectPost", post_no);
        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally {
            ss.close();
        }
        String title = postDTO.getTitle();
        String text = postDTO.getText();
        String img = postDTO.getImg();
        String date = postDTO.getDate();
        int member_no = postDTO.getMember_no();
        String member_name = postDTO.getMember_name();

        mav.addObject("post_no", post_no);
        mav.addObject("title", title);
        mav.addObject("text", text);
        mav.addObject("img", img);
        mav.addObject("date", date);
        mav.addObject("member_no", member_no);
        mav.addObject("member_name", member_name);

        return mav;
    }
}
