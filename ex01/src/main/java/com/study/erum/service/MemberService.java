package com.study.erum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.erum.dto.MemberDTO;
import com.study.erum.repository.MemberRepository;
//import com.study.erum.repository.MemberRepository2;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	//private final MemberRepository2 memberRepository;
	private final MemberRepository memberRepository;
	
	
	public int save (MemberDTO memberDTO) {
		return memberRepository.save(memberDTO);
	}

	public boolean login(MemberDTO memberDTO) {
		MemberDTO loginMember = memberRepository.login(memberDTO);
		if(loginMember != null) {
			return true;
		}else{
			return false;
		}
	}

	public List<MemberDTO> findAll() {
		return memberRepository.findAll();
	}

	public MemberDTO findById(Long id) {
		return memberRepository.findById(id);
	}

	public void delete(Long id) {
		memberRepository.delete(id);
	}

	public MemberDTO findByMemberEmail(String loginEmail) {
		return memberRepository.findByMemberEmail(loginEmail);
	}

	public boolean update(MemberDTO memberDTO) {
		int result = memberRepository.update(memberDTO);
	    if(result > 0){
	      return true;
	    }else{
	      return false;
	    }
	}

	public String emailCheck(String memberEmail) {
	    MemberDTO memberDTO = memberRepository.findByMemberEmail(memberEmail);
	    if(memberDTO == null){
	      return "ok";
	    }else{
	      return "no";
	    }
	}	
	
}
