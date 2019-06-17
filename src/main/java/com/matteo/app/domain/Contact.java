package com.matteo.app.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Integer id;

    @Column(name = "firstName")
    @NotEmpty(message = "*Please provide contact first name")
    private String firstName;

    @Column(name = "lastName")
    @NotEmpty(message = "*Please provide contact last name")
    private String lastName;

    @Column(name = "phone")
    @NotEmpty(message = "*Please provide contact phone")
    private String phone;

    @Column(name = "email")
    @NotEmpty(message = "*Please provide contact email")
    private String email;

}