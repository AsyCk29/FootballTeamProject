package com.team.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Player extends  Base {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "fid", nullable = false)
    private Long fid;
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @Column(unique = true)
    @NotEmpty
    @NotNull
    @Email
    private String email;
    @NotEmpty
    @NotNull
    private String phone;
    @NotBlank
    @NotEmpty
    @NotNull
    private String password;
    @Min(18) Integer age;


}
