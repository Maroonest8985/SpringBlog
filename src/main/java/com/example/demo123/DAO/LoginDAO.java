package com.example.demo123.DAO;

import com.example.demo123.DTO.LoginDTO;
import com.example.demo123.DTO.MemberDTO;

import java.util.Map;

public interface LoginDAO {
   public void loginMember(String id, String pwd);
}
