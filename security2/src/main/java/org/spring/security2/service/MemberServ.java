package org.spring.security2.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.aspectj.apache.bcel.classfile.Module;
import org.spring.security2.constant.Role;
import org.spring.security2.dto.MemberDto;
import org.spring.security2.entities.BaseEntity;
import org.spring.security2.entities.Members;
import org.spring.security2.repository.MembersRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@Service
@RequiredArgsConstructor
public class MemberServ {

    private final MembersRepository membersRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void insert(MemberDto memberDto) {

        Members members = Members.builder().email(memberDto.getEmail()).password(passwordEncoder.encode(memberDto.getPassword())).role(Role.MEMBER).gender(memberDto.getGender()).build();
        membersRepository.save(members);
    }

    public List<MemberDto> showmemberAll() {
        List<MemberDto> allMembersDto = new ArrayList<>();
        List<Members> allMembersEnt = membersRepository.findAll();
        for (Members i : allMembersEnt) {
            MemberDto dtos = MemberDto.builder().id(i.getId()).email(i.getEmail()).password(i.getPassword()).createTime(i.getCreateTime()).updateTime(i.getUpdateTime()).role(i.getRole().toString()).build();
            allMembersDto.add(dtos);
        }

        return allMembersDto;
    }

    public MemberDto detailMember(String email) {

        Optional<Members> result = membersRepository.findByEmail(email);
        Members detail = result.get();
        MemberDto dto = MemberDto.builder().id(detail.getId()).email(detail.getEmail()).password(detail.getPassword()).role(detail.getRole().toString()).build();
        return dto;
    }

    public List<MemberDto> selectContaining(String search) {
        List<Members> result = membersRepository.findByEmailContaining(search);
        List<MemberDto> dtos = new ArrayList<>();
        if (result != null) {
            for (Members i : result) {
                MemberDto dto = MemberDto.builder().email(i.getEmail()).password(i.getPassword()).role(i.getRole().toString()).createTime(i.getCreateTime()).updateTime(i.getUpdateTime()).build();
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public MemberDto selectDetail(Long id) {
        Optional<Members> result=membersRepository.findById(id);
        Members ent=result.get();
        MemberDto dto=MemberDto.builder()
                .id(ent.getId())
                .email(ent.getEmail())
                .password(ent.getPassword())
                .role(ent.getRole().toString())
                .createTime(ent.getCreateTime())
                .updateTime(ent.getUpdateTime())
                .build();
        return dto;
    }


    @Transactional
    public void update1(String email, Long id, String password, String gender){
       Members members= membersRepository.findById(id).get();
       members.setEmail(email);
       members.setPassword(passwordEncoder.encode(password));
       members.setGender(gender);
       membersRepository.save(members);
    }

}
