package com.study.erum.repository;

import com.study.erum.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberRepository {

    int save(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    List<MemberDTO> findAll();

    MemberDTO findById(Long id);

    void delete(Long id);

    MemberDTO findByMemberEmail(String loginEmail);

    int update(MemberDTO memberDTO);
}
