package com.example.entities;

import jakarta.persistence.*;
import org.yaml.snakeyaml.events.Event;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cif;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    private String phoneNumber;

    public Manufacturer() {
    }

    public Manufacturer(Long id, String cif, String name, Address address, String phoneNumber) {
        this.id = id;
        this.cif = cif;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public Manufacturer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCif() {
        return cif;
    }

    public Manufacturer setCif(String cif) {
        this.cif = cif;
        return this;
    }

    public String getName() {
        return name;
    }

    public Manufacturer setName(String name) {
        this.name = name;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Manufacturer setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Manufacturer setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", cif='" + cif + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}