package org.spring.security2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.security2.dto.MemberDto;
import org.spring.security2.entities.Members;
import org.spring.security2.repository.MembersRepository;
import org.spring.security2.service.MemberServ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberControl {

    private final MemberServ memberServ;


//    JOIN=====================================================

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/joinP";
    }

    @PostMapping("/join")
    public String joinPost(@Valid MemberDto memberDto, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "member/joinP";
        }
        memberServ.insert(memberDto);
        return "redirect:member/login";
    }

//    Login ==================================================

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }


//    Select All ==================================================

    @GetMapping("/memberAll")
    public String memberAll(Model model) {
        List<MemberDto> allMembers = memberServ.showmemberAll();
        model.addAttribute("allMembers", allMembers);
        return "member/member";
    }

//    Roles ==================================================

    @GetMapping("/admin")
    public String admin() {
        return "member/admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "member/manager";
    }

//    Detail Info=============================================

    @GetMapping("/detail/{id}")
    public String selectDetail(@PathVariable("id") Long id, Model model) {
        MemberDto member = memberServ.selectDetail(id);
        model.addAttribute("allMembers", member);
        return "member/member";
    }

//   Select Containing ========================================================

    @GetMapping("/user/search")
    public String searchContain(@RequestParam(value = "search", required = false) String search, Model model) {
        List<MemberDto> memberDtoList = memberServ.selectContaining(search);
        model.addAttribute("allMembers", memberDtoList);
        return "member/member";
    }

    //    Update ==================================================================
//    @GetMapping("/updateP/{id}")
//    public String updatePage(@PathVariable("id") Long id,Model model) {
//        MemberDto member = memberServ.selectDetail(id);
//        model.addAttribute("dto", new MemberDto());
//        model.addAttribute("memberInfo", member);
//        return "member/update";
//    }


    @PostMapping("/update")
    public String updateMember(MemberDto memberDto) {
            memberServ.update1(memberDto.getEmail(), memberDto.getId(), memberDto.getPassword(), memberDto.getGender());
        return "index";
    }
//    =======Update Ajax============================================
    @GetMapping("/updateP?id={id}")
    public @ResponseBody MemberDto findInfosUpdateP(@PathVariable("id") Long id,Model model) {
        MemberDto member = memberServ.selectDetail(id);
        return member;
    }




//    ==============================================================

}
