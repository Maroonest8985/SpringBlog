package com.example.demo123.DAO;


import com.example.demo123.DTO.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface CommentDAO {
    public void addComment(CommentDTO commentDTO);
    public int deleteComment(Map<String, Integer> map);
    public ArrayList<CommentDTO> getComment(Map<String, Integer> map);
    public int getCommentNo();
}
