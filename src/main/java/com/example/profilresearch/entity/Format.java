package com.example.profilresearch.entity;

import jakarta.persistence.*;

@Table(name = "Format")
public enum Format {
    TEXT,
    RADIO,
    CHECKBOX
}
