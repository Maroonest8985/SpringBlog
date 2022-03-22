package com.example.demo123.DAO;

import com.example.demo123.DTO.SignupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SignupDAO {
    int insertMember(SignupDTO signupDTO);
    void checkId(String id);
}
