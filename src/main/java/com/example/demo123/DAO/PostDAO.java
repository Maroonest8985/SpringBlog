package com.example.demo123.DAO;

import com.example.demo123.DTO.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
@Mapper
public interface PostDAO {
   public void insertPost(PostDTO postDTO);
   public PostDTO selectPost(int no);
   public ArrayList<PostDTO> getListPost();
   public int getRows(int limit, int contentNo);
   public void deletePost(String post_no);
}
