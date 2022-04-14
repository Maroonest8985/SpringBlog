package com.example.demo123.DAO;


import com.example.demo123.DTO.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface CommentDAO {
    public void addComment(CommentDTO commentDTO);
    public void deleteComment();
    public ArrayList<CommentDTO> getComment(String post_no);
}
