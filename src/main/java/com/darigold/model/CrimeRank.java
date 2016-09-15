package com.darigold.model;

/**
 * Created by trevorBye on 9/15/16.
 */
public class CrimeRank {
    private String crimeDesc;
    private Integer distinctCount;

    public CrimeRank(String crimeDesc, Integer distinctCount) {
        this.crimeDesc = crimeDesc;
        this.distinctCount = distinctCount;
    }

    public String getCrimeDesc() {
        return crimeDesc;
    }

    public void setCrimeDesc(String crimeDesc) {
        this.crimeDesc = crimeDesc;
    }

    public Integer getDistinctCount() {
        return distinctCount;
    }

    public void setDistinctCount(Integer distinctCount) {
        this.distinctCount = distinctCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrimeRank crimeRank = (CrimeRank) o;

        if (crimeDesc != null ? !crimeDesc.equals(crimeRank.crimeDesc) : crimeRank.crimeDesc != null) return false;
        return distinctCount != null ? distinctCount.equals(crimeRank.distinctCount) : crimeRank.distinctCount == null;

    }

    @Override
    public int hashCode() {
        int result = crimeDesc != null ? crimeDesc.hashCode() : 0;
        result = 31 * result + (distinctCount != null ? distinctCount.hashCode() : 0);
        return result;
    }
}
