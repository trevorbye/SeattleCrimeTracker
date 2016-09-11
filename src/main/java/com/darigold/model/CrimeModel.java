package com.darigold.model;


import java.time.LocalDate;

public class CrimeModel {
    private String mainCrimeDesc;
    private String address;
    private double distance;
    private LocalDate crimeDate;

    public CrimeModel(String mainCrimeDesc, String address, double distance, LocalDate crimeDate) {
        this.mainCrimeDesc = mainCrimeDesc;
        this.address = address;
        this.distance = distance;
        this.crimeDate = crimeDate;
    }

    public LocalDate getCrimeDate() {
        return crimeDate;
    }

    public void setCrimeDate(LocalDate crimeDate) {
        this.crimeDate = crimeDate;
    }

    public String getMainCrimeDesc() {
        return mainCrimeDesc;
    }

    public void setMainCrimeDesc(String mainCrimeDesc) {
        this.mainCrimeDesc = mainCrimeDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
