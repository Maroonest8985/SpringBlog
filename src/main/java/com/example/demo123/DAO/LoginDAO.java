package com.example.demo123.DAO;

import com.example.demo123.DTO.LoginDTO;
import com.example.demo123.DTO.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
@Mapper
public interface LoginDAO {
   public void loginMember(String id, String pwd);
}
