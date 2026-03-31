package com.example.profilresearch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "\"User\"")
public class User {
    @Id
    private String mail;

    private String surname;
    private String firstname;
}
