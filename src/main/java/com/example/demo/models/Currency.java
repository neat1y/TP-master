package com.example.demo.models;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Entity
@Table(name ="currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id ;
    @Column(name = "euro")
    @NotNull
    private double euro;

    @Column(name ="dollar")
    @NotNull
    private double dollar;
    @Column(name="currencydate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date currencyDate;
    @OneToMany(mappedBy = "currency")
    private List<Operations> operations;

    public Currency() {
    }
    public Currency(double euro, double dollar, Date currencyDate) {
        this.euro = euro;
        this.dollar = dollar;
        this.currencyDate = currencyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEuro() {
        return euro;
    }

    public void setEuro(double euro) {
        this.euro = euro;
    }

    public double getDollar() {
        return dollar;
    }

    public void setDollar(double dollar) {
        this.dollar = dollar;
    }

    public Date getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(Date currencyDate) {
        this.currencyDate = currencyDate;
    }
}
