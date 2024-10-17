package com.palisuar.losceibosjardines.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String currencyType;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference(value = "client-bill")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "park_id")
    @JsonBackReference(value = "park-bills")
    private Park park;

    public Bill() {

    }
}