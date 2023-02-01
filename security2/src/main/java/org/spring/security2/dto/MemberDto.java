package org.spring.security2.dto;

import lombok.*;
import org.spring.security2.entities.GenderAttributeConverter;

import javax.persistence.Convert;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberDto {

    private Long id;
    @NotBlank(message = "이메일이 입력되지 않았습니다.")
    private String email;
    @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
    private String password;
    @Convert(converter = GenderAttributeConverter.class)
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String gender;


}
