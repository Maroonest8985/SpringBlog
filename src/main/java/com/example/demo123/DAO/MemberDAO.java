package com.example.demo123.DAO;

import com.example.demo123.DTO.MemberDTO;

import java.util.List;

public interface MemberDAO {
    public List<MemberDTO> getList(int no);
}
