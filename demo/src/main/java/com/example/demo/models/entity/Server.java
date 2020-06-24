package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Server implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String dns;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String ip;

    @Min(value = 0, message = "Precio mínimo 0 USD")
    @NotNull
    private double price;

    @Min(value = 1, message = "Capacidad mínima 1 GB")
    @NotNull
    private int capacity;

    private boolean enable;

    private Date createAt;

    private Date updateAt;

    @PrePersist
    public void init() {

        Date date = new Date();

        this.createAt = date;
        this.updateAt = this.createAt;
        this.enable = true;

    }

    public Server() {
    }

    public Server(String dns, String ip, double price, int capacity) {

        this.dns = dns;
        this.ip = ip;
        this.price = price;
        this.capacity = capacity;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDns() {
        return this.dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Server)) {
            return false;
        }
        Server server = (Server) o;
        return Objects.equals(id, server.id) && Objects.equals(dns, server.dns) && Objects.equals(ip, server.ip)
                && price == server.price && capacity == server.capacity && enable == server.enable
                && Objects.equals(createAt, server.createAt) && Objects.equals(updateAt, server.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dns, ip, price, capacity, enable, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", dns='" + getDns() + "'" + ", ip='" + getIp() + "'" + ", price='"
                + getPrice() + "'" + ", capacity='" + getCapacity() + "'" + ", enable='" + isEnable() + "'"
                + ", createAt='" + getCreateAt() + "'" + ", updateAt='" + getUpdateAt() + "'" + "}";
    }

}