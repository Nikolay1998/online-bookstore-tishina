package com.tishina.model;

import java.util.Date;

public class Arrival {
    private Integer id;
    private Integer unique_names;
    private Integer amount;
    private Date a_date;

    public Arrival(Integer id, Integer unique_names, Integer amount, Date a_date) {
        this.id = id;
        this.unique_names = unique_names;
        this.amount = amount;
        this.a_date = a_date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUnique_names() {
        return unique_names;
    }

    public void setUnique_names(Integer unique_names) {
        this.unique_names = unique_names;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getA_date() {
        return a_date;
    }

    public void setA_date(Date a_date) {
        this.a_date = a_date;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "id=" + id +
                ", unique_names=" + unique_names +
                ", amount=" + amount +
                ", a_date=" + a_date +
                '}';
    }
}
