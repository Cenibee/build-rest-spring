package com.cenibee.learn.springrest.employee;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id") @ToString
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;

}
