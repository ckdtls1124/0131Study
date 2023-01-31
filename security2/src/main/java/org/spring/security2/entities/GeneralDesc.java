package org.spring.security2.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Generaldescs")
public class GeneralDesc extends BaseEntity{


    @Column(unique = true)
    private String generaldesc;
    @ManyToOne
    private GeneralTitle title;
}
