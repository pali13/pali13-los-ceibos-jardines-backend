package com.palisuar.losceibosjardines.dto;

import com.palisuar.losceibosjardines.entity.Client;

public class ClientDTO {
    private String name;
    private String email;
    private String phone;
    private String address;

    public ClientDTO() {

    }

    public ClientDTO(Client client) {
        this.name = client.getName();
        this.email = client.getEmail();
        this.phone = client.getPhone();
        this.address = client.getAddress();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void SetEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
