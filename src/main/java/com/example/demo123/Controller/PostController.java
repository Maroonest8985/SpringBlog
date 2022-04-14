package com.example.demo123.Controller;


import com.example.demo123.DTO.CommentDTO;
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
import java.util.*;

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
        String img = "img"; //temporary value
        postDTO.setMember_no(member_no);
        postDTO.setTitle(title);
        postDTO.setText(text);
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

    @GetMapping("/deletepost")
    public ModelAndView deletePost(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView();
        int post_no = Integer.parseInt(request.getParameter("post_no"));
        int member_no = (int) session.getAttribute("member_no");
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("member_no", member_no);
            map.put("post_no", post_no);
            System.out.println(post_no);
            System.out.println(member_no);
            int result = ss.delete("deletePost", map);
            if(result > 0){
                mav.setViewName("redirect:/home");
            }else{
                mav.setViewName("error");
            }
        }catch(SqlSessionException e){
            e.printStackTrace();
            mav.setViewName("error");
        }finally {
            ss.close();
        }
        return mav;
    }

    @GetMapping("/editpost")
    public ModelAndView editPost(PostDTO postDTO, Model model, HttpServletRequest request){
        SqlSession ss = sqlSessionFactory.openSession();
        String post_no = request.getParameter("post_no");
        try{
            postDTO = ss.selectOne("selectPost", post_no);
        }catch(SqlSessionException e){
            e.printStackTrace();
            System.out.println("sql error");
        }finally {
            ss.close();
        }
        String title = postDTO.getTitle();
        String img = postDTO.getImg();
        String text = postDTO.getText();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("editpost");
        mav.addObject("post_no", post_no);
        mav.addObject("title", title);
        mav.addObject("img", img);
        mav.addObject("text", text);
        return mav;
    }


    @PostMapping("/updatepost")
    public ModelAndView updatePost(HttpServletRequest request, HttpSession session){
        int member_no = (int) session.getAttribute("member_no");
        PostDTO postDTO = new PostDTO();
        int post_no = Integer.parseInt(request.getParameter("post_no"));
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String img = "img"; //temporary value
        postDTO.setMember_no(member_no);
        postDTO.setTitle(title);
        postDTO.setText(text);
        postDTO.setImg(img);
        postDTO.setPost_no(post_no);
        ModelAndView mav = new ModelAndView();
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            if(ss.update("updatePost", postDTO) == 0){
                mav.setViewName("error");
            }else{
                 mav.setViewName("redirect:/home");
            }
        }catch(SqlSessionException e){
            e.printStackTrace();
            mav.setViewName("redirect:/error");
        }finally {
            ss.close();
        }
        return mav;
    }


    @GetMapping("/readpost") // no post page add
    public ModelAndView readPost(PostDTO postDTO, CommentDTO commentDTO, Model model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("post");
        String post_no = request.getParameter("post_no");
        List<CommentDTO> comment = new ArrayList<>();
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            postDTO = ss.selectOne("selectPost", post_no);
            Map<String, String> map = new HashMap<>();
            map.put("post_no", post_no);
            comment = ss.selectList("getComment", map);
        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally {
            ss.close();
        }
        String title = postDTO.getTitle();
        String text = postDTO.getText();
        String text1 = text.replace("\r\n", "<br>");
        String img = postDTO.getImg();
        String date = postDTO.getDate();
        int member_no = postDTO.getMember_no();
        String member_name = postDTO.getMember_name();
        mav.addObject("comment", comment);
        mav.addObject("post_no", post_no);
        mav.addObject("title", title);
        mav.addObject("text", text1);
        mav.addObject("img", img);
        mav.addObject("date", date);
        mav.addObject("member_no", member_no);
        mav.addObject("member_name", member_name);

        return mav;
    }

    @PostMapping("/addcomment")
    @ResponseBody
    public CommentDTO insertComment(HttpServletRequest request, HttpSession session, CommentDTO commentDTO){
        String text = request.getParameter("text");
        int member_no = (int) session.getAttribute("member_no");
        long post_no = Long.parseLong(request.getParameter("post_no"));
        String member_name = "";
        commentDTO.setText(text);
        commentDTO.setMember_no(member_no);
        commentDTO.setPost_no(post_no);
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            ss.insert("addComment", commentDTO);
            member_name = ss.selectOne("getMemberName", member_no);
            commentDTO.setMember_name(member_name);
        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return commentDTO;
    }

    @PostMapping("/deletecomment")
    @ResponseBody
    public int deleteComment(HttpServletRequest request, HttpSession session){
        int result = 0;
        int comment_no = 0;
        int member_no = 0;
        comment_no = Integer.parseInt(request.getParameter("comment_no"));
        member_no = (int) session.getAttribute("member_no");
        Map<String, Integer> map = new HashMap<>();
        map.put("comment_no", comment_no);
        map.put("member_no", member_no);
        SqlSession ss = sqlSessionFactory.openSession();
        try{
            if(ss.delete("deleteComment", map) > 0){
                result = 1;
            }else{
                result = 0;
            }

        }catch(SqlSessionException e){
            e.printStackTrace();
        }finally {
            ss.close();
        }
        return result;
    }
}
