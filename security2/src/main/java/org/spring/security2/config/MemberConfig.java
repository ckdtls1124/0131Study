package org.spring.security2.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@Log4j2
@EnableWebSecurity
@RequiredArgsConstructor
public class MemberConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.authorizeHttpRequests()
                .antMatchers("/index", "/", "/member/join", "/member/login").permitAll()
                .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/member/**").hasAnyRole("MEMBER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/manager/**").hasAnyRole("MANAGER");

        http.formLogin()
            .loginPage("/member/login")
            .loginProcessingUrl("/member/loginDo")
            .usernameParameter("email")
            .defaultSuccessUrl("/index", true)
            .failureUrl("/member/login");

        http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/member/login");


        http.userDetailsService(userDetailsService);


        return http.build();
    }

@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
