package com.palisuar.losceibosjardines.dto;

import java.time.LocalDate;

public class PayDTO {
    private final Long id;
    private final String amount;
    private final LocalDate date;
    private final String status;
    private final String clientName; // Solo incluimos el nombre del parque
    private final String parkName; // Solo incluimos el nombre del parque

    public PayDTO(Long id, String amount, LocalDate date, String status, String clientName, String parkName) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.clientName = clientName;
        this.parkName = parkName;
    }

    public Long getId() {
        return this.id;
    }

    public String getAmount() {
        return this.amount;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getStatus() {
        return this.status;
    }

    public String getClientName() {
        return this.clientName;
    }

    public String getParkName() {
        return this.parkName;
    }
}