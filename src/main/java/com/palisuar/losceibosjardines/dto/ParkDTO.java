package com.palisuar.losceibosjardines.dto;

import java.util.List;

public class ParkDTO {
    private final Long id;
    private final String name;
    private final List<PayDTO> pays; // Incluimos los pagos asociados

    public ParkDTO(Long id, String name, List<PayDTO> pays) {
        this.id = id;
        this.name = name;
        this.pays = pays;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<PayDTO> getPays() {
        return this.pays;
    }
}
