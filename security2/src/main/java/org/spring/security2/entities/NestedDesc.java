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
@Table(name = "Nesteddescs")
public class NestedDesc extends BaseEntity{


    @Column(unique = true)
    private String Nesteddesc;
    @ManyToOne
    private NestedTitle title;
}
