package org.spring.security2.entities;

import lombok.*;
import org.spring.security2.constant.Role;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "membertb")
@ToString
@Builder
public class Members extends BaseEntity{


    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
//    @Convert(converter = PasswordEnDecodeConverter.class)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Convert(converter = GenderAttributeConverter.class)
    private String gender;

}
