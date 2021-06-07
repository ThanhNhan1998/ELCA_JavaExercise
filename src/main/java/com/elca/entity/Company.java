package com.elca.entity;

import com.opencsv.bean.CsvBindByPosition;

public class Company {

    @CsvBindByPosition(position = 0)
    private long id;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private String foundationDate;

    @CsvBindByPosition(position = 3)
    private Double capital;

    @CsvBindByPosition(position = 4)
    private String country;

    @CsvBindByPosition(position = 5)
    private Boolean isHeadQuarter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(String foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean isHeadQuarter() {
        return isHeadQuarter;
    }

    public void setHeadQuarter(Boolean headQuarter) {
        isHeadQuarter = headQuarter;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", foundationDate='" + foundationDate + '\'' +
                ", capital='" + capital + '\'' +
                ", country='" + country + '\'' +
                ", isHeadQuarter='" + isHeadQuarter + '\'' +
                '}';
    }
}
