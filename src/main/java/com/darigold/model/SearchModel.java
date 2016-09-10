package com.darigold.model;


public class SearchModel {

    //model for storing search form input

    private String address;
    private int distanceArea;
    private int dataYears;
    private String targetCrime;
    private String countWithinDataYears;

    public SearchModel(String address, int distanceArea, int dataYears, String targetCrime, String countWithinDataYears) {
        this.address = address;
        this.distanceArea = distanceArea;
        this.dataYears = dataYears;
        this.targetCrime = targetCrime;
        this.countWithinDataYears = countWithinDataYears;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistanceArea() {
        return distanceArea;
    }

    public void setDistanceArea(int distanceArea) {
        this.distanceArea = distanceArea;
    }

    public int getDataYears() {
        return dataYears;
    }

    public void setDataYears(int dataYears) {
        this.dataYears = dataYears;
    }

    public String getTargetCrime() {
        return targetCrime;
    }

    public void setTargetCrime(String targetCrime) {
        this.targetCrime = targetCrime;
    }

    public String getCountWithinDataYears() {
        return countWithinDataYears;
    }

    public void setCountWithinDataYears(String countWithinDataYears) {
        this.countWithinDataYears = countWithinDataYears;
    }
}
