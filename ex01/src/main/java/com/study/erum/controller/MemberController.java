package com.study.erum.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.erum.dto.MemberDTO;
import com.study.erum.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/save")
	public String saveForm() {
		return "save";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
		int saveResult = memberService.save(memberDTO);
		if(saveResult > 0) {
			return "login";
		}else {
			return "save";
		}
		
	}
	
	 @GetMapping("login")
	  public String loginForm(){
	    return "login";
	  }

	  @PostMapping("/login")
	  public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
	    boolean loginResult = memberService.login(memberDTO);
	    if(loginResult){
	      session.setAttribute("loginEmail", memberDTO.getMemberEmail());
	      return "main";
	    }else{
	      return "login";
	    }
	  }
	  
	  // logout
	  @GetMapping("/logout")
	  public String logout(HttpSession session) {
	      session.removeAttribute("loginEmail"); // 세션에서 loginEmail 속성 제거
	      return "redirect:/member/login"; // 로그인 페이지로 리다이렉트
	  }

	  
	  
	  
	  // membmer 리스트 목록
	  @GetMapping("/")
	  public String findAll(Model model) {
		  List<MemberDTO> memberDTOList = memberService.findAll();
		  model.addAttribute("memberList", memberDTOList);
		  return "list";
	  }
	  
	  // 상세조회
	  // /member?Id=1
	  @GetMapping
	  public String findById(@RequestParam("id") Long id, Model model){
	    MemberDTO memberDTO = memberService.findById(id);
	    model.addAttribute("member", memberDTO);
	    return "detail";
	  }
	  
	  //삭제기능
	  @GetMapping("/delete")
	  public String delete(@RequestParam("id") Long id){
	    memberService.delete(id);
	    return "redirect:/member/";
	  }
	  
	  // 수정화면 요청
	  @GetMapping("/update")
	  public String updateFrom(HttpSession session, Model model){
	    //세션에 저장된 나의 이메일 가져오기
	    String loginEmail = (String) session.getAttribute("loginEmail");
	    MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
	    model.addAttribute("member", memberDTO);
	    return "update";
	  }
	  
	  @PostMapping("/update")
	  public String update(@ModelAttribute MemberDTO memberDTO){
	    boolean result = memberService.update(memberDTO);
	    if(result){
	      return "redirect:/member?Id=" + memberDTO.getId();
	    }else{
	      return "index";
	    }
	  }
	  
	  @PostMapping("/email-check")
	  public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
	    System.out.println("memberEmail = " + memberEmail);
	    String checkResult = memberService.emailCheck(memberEmail);
	    return checkResult;
	  }
	  
	  
	  
	  
	  

}
