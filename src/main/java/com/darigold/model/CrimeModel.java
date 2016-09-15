package com.darigold.model;


import java.time.LocalDate;

public class CrimeModel {
    private String mainCrimeDesc;
    private double distance;
    private LocalDate crimeDate;

    public CrimeModel(String mainCrimeDesc, double distance, LocalDate crimeDate) {
        this.mainCrimeDesc = mainCrimeDesc;
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


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
