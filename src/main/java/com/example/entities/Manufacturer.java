package com.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cif;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
    private String phoneNumber;

    public Manufacturer(String cif, String name, Address address, String phoneNumber) {
        this.cif = cif;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}