package com.palisuar.losceibosjardines.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String size;

    @Column(nullable = true)
    private String lastVisit;

    @Column(nullable = true)
    private String recommendedMaintenance;

    @Column(nullable = true)
    private Double amount;

    @Column(nullable = true)
    private String typeCurrency;

    @ManyToOne(fetch = FetchType.EAGER) // Esto asegura que el cliente se cargue junto con el parque
    @JoinColumn(name = "client_id", nullable = false) // Clave foránea hacia Client
    private Client client;

    @OneToMany(mappedBy = "park", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("park-pays") // Nombre único para esta relación
    private List<Pay> pays;

    @OneToMany(mappedBy = "park")
    @JsonManagedReference(value = "park-bills")
    private List<Bill> bills;
}
