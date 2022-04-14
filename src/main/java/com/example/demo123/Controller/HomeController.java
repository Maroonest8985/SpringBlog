package com.example.demo123.Controller;

import com.example.demo123.DTO.PostDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;

import com.example.demo123.DTO.SignupDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @GetMapping("/home")
    public ModelAndView home(PostDTO postDTO, Model model, HttpServletRequest request){

        model.addAttribute("data", "this is home");
        model.addAttribute("navbar", "home");
        model.addAttribute("signupDTO", new SignupDTO());
        List<PostDTO> arrPostDTO = new ArrayList<>();
        PostDTO featPostDTO = new PostDTO();
        int nowPage = 1;
        if(request.getParameter("pageNo") != null){
            nowPage = Integer.parseInt(request.getParameter("pageNo"));
        }
        int maxPage = 0;////총 페이지 갯수 -> getRows 나누기 contentNo의 몫
        int contentNo = 4;//페이지에 표시될 항목 갯수;
        int limit = ((nowPage-1)*contentNo)+1;
        try(SqlSession ss = sqlSessionFactory.openSession()){
            maxPage = ss.selectOne("getRows");
            if((maxPage-1)%contentNo == 0){
                maxPage = (maxPage/contentNo); //게시글이 4의배수로 떨어질 때 추가 페이지 생성x
            }else{
                maxPage = (maxPage/contentNo) + 1;//최대 페이지 갯수
            }
            Map<String, Integer> map = new HashMap<String, Integer>();
            Map<String, Integer> featMap = new HashMap<String, Integer>();
            if(nowPage == 1){
                map.put("limit", 1);
                map.put("contentNo", contentNo);
                featMap.put("limit", 0);
                featMap.put("contentNo", 1);
            }else{
                map.put("limit", limit);
                map.put("contentNo", contentNo);
            }
            arrPostDTO = ss.selectList("getListPostPage", map);
            featPostDTO = ss.selectOne("getListPostPage", featMap);
        }catch(SqlSessionException e){
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("maxPage", maxPage);
        mav.addObject("nowPage", nowPage);
        mav.addObject("arrPostDTO", arrPostDTO);
        mav.addObject("featPostDTO", featPostDTO);
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

        return "error";
    }
    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("data", "this is contact page");
        model.addAttribute("navbar", "contact");
        model.addAttribute("signupDTO", new SignupDTO());

        return "error";
    }
    @GetMapping("/error")
    public String error(){
        return "error";
    }


}


