package com.example.demo123.DAO;

import com.example.demo123.DTO.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
@Mapper

public interface PostDAO {
   void insertPost(PostDTO postDTO);
   PostDTO selectPost(int no);
   int getRows();
   ArrayList<PostDTO> getListPostPage(Map<String, Integer> map);
   int deletePost(Map<String, Integer> map);
   int updatePost(PostDTO postDTO);
   ArrayList<PostDTO> getListSearchPost(String search, int limit, int contentNo);
   int getSearchRows(String search);
   String getMemberName(int member_no);
}

