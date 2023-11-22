package com.example.demo.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;
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
    @Column(name="currencyDate")
    private Timestamp currencyDate;
    @OneToMany(mappedBy = "currency")
    private List<Operations> operations;

    public Currency() {
    }
    public Currency(double euro, double dollar, Timestamp currencyDate) {
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

    public Timestamp getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(Timestamp currencyDate) {
        this.currencyDate = currencyDate;
    }
}
