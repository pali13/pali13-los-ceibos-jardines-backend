package com.palisuar.losceibosjardines.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String lastVisit;

    @Column(nullable = false)
    private String recommendedMaintenance;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference(value = "client-park")
    private Client client;

    @OneToMany(mappedBy = "park")
    @JsonManagedReference(value = "park-bills")
    private List<Bill> bills;
}
