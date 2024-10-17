package com.palisuar.losceibosjardines.entity;

import java.time.LocalDate;

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
public class Pay {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private LocalDate payDate;

    @ManyToOne
    @JoinColumn(name = "park_id", nullable = false)
    @JsonBackReference(value = "park-pays")
    private Park park;
    
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference(value = "client-pays")
    private Client client;
}