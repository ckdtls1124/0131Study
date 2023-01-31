package org.spring.security2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Httpdescs")
public class HttpDesc extends BaseEntity{


    @Column(unique = true)
    private String httpDesc;
    @ManyToOne
    private HttpTitle title;
}
