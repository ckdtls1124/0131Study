//package org.spring.security2.config;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import lombok.extern.log4j.Log4j2;
//import org.spring.security2.entities.Members;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Collection;
//
//@Setter
//@Getter
//@ToString
//@Log4j2
//
//public class SecurityUser extends User {
//    private Members members;
//
//
//    public SecurityUser(Members members) {
//        super(members.getEmail(), members.getPassword(), AuthorityUtils.createAuthorityList(members.getRole().toString()));
//        System.out.println("Member email: "+members.getEmail());
//        this.members=members;
//    }
//}
