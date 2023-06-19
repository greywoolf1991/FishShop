package com.example.fishshop.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class ClientM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "client_name")
    String name;

    @Column(name = "wanted_item")
    Long item;

    @Column(name = "contact")
    String contact;

    @Column(name = "actual")
    boolean actual;
}
