package org.spring.security2.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.aspectj.apache.bcel.classfile.Module;
import org.spring.security2.constant.Role;
import org.spring.security2.dto.MemberDto;
import org.spring.security2.entities.Members;
import org.spring.security2.repository.MembersRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServ {

    private final MembersRepository membersRepository;
    private final PasswordEncoder passwordEncoder;

    public void insert(MemberDto memberDto) {

        Members members=Members.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .role(Role.MEMBER.toString())
                .build();
        membersRepository.save(members);
    }

    public List<MemberDto> showmemberAll() {
        List<MemberDto> allMembersDto=new ArrayList<>();
        List<Members> allMembersEnt=membersRepository.findAll();
        for(Members i:allMembersEnt){
        MemberDto dtos=MemberDto.builder()
                .email(i.getEmail())
                .password(i.getPassword())
                .createTime(i.getCreateTime())
                .updateTime(i.getUpdateTime())
                .role(i.getRole())
                .build();
            allMembersDto.add(dtos);
        }

        return allMembersDto;
    }

    public MemberDto detailMember(String email) {

        Optional<Members> result=membersRepository.findByEmail(email);
        Members detail=result.get();
        MemberDto dto=MemberDto.builder()
                .email(detail.getEmail())
                .password(detail.getPassword())
                .role(detail.getRole())
                .build();
        return dto;
    }
}
