package com.example.demo123.DAO;

import com.example.demo123.DTO.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@Mapper
public interface MemberDAO {
    public List<MemberDTO> getList(int no);
    public void deleteMember(int no);
}
