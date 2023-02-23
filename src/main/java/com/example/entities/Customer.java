package com.example.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;


    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany
    @ToString.Exclude
    private List<Address> addresses = new ArrayList<>();

    private String phone;


}
