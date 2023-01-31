package org.spring.security2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.security2.dto.MemberDto;
import org.spring.security2.repository.MembersRepository;
import org.spring.security2.service.MemberServ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberControl {

    private final MemberServ memberServ;


//    JOIN process ==========================================

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/joinP";
    }

    @PostMapping("/join")
    public String joinPost(@Valid MemberDto memberDto, BindingResult result) throws IOException {
        if(result.hasErrors()){
            return "member/joinP";
        }
        memberServ.insert(memberDto);
        return "redirect:member/login";
    }

//    ========================================================

//    Login ==================================================

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }



//    ========================================================
//    All Member ==================================================

    @GetMapping("/memberAll")
    public String memberAll(Model model){
        List<MemberDto> allMembers=memberServ.showmemberAll();
        model.addAttribute("allMembers", allMembers);
        return "member/member";
    }



//    ========================================================
//    Roles ==================================================
    @GetMapping("/admin")
    public String admin(){
        return "member/admin";
    }

    @GetMapping("/manager")
    public String manager(){
        return "member/manager";
    }



}
