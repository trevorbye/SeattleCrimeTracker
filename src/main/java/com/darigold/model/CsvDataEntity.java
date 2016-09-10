package com.darigold.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by trevorBye on 9/6/16.
 */
@Entity
@Table(name = "csvData", schema = "policeReports", catalog = "")
public class CsvDataEntity {
    private String cadCdw;
    private String cadNumber;
    private String genOffense;
    private String clearanceNumber;
    private String clearanceDesc;
    private String clearanceSubCat;
    private String clearanceMainCat;
    private String clearanceDt;
    private String hundredBlck;
    private String district;
    private String zone;
    private String censusTract;
    private String vertRadians;
    private String latitude;
    private String location;

    @Basic
    @Column(name = "cadCdw", nullable = false, length = 100)
    public String getCadCdw() {
        return cadCdw;
    }

    public void setCadCdw(String cadCdw) {
        this.cadCdw = cadCdw;
    }

    @Basic
    @Column(name = "cadNumber", nullable = false, length = 100)
    public String getCadNumber() {
        return cadNumber;
    }

    public void setCadNumber(String cadNumber) {
        this.cadNumber = cadNumber;
    }

    @Basic
    @Column(name = "genOffense", nullable = false, length = 100)
    public String getGenOffense() {
        return genOffense;
    }

    public void setGenOffense(String genOffense) {
        this.genOffense = genOffense;
    }

    @Basic
    @Column(name = "clearanceNumber", nullable = false, length = 100)
    public String getClearanceNumber() {
        return clearanceNumber;
    }

    public void setClearanceNumber(String clearanceNumber) {
        this.clearanceNumber = clearanceNumber;
    }

    @Basic
    @Column(name = "clearanceDesc", nullable = false, length = 100)
    public String getClearanceDesc() {
        return clearanceDesc;
    }

    public void setClearanceDesc(String clearanceDesc) {
        this.clearanceDesc = clearanceDesc;
    }

    @Basic
    @Column(name = "clearanceSubCat", nullable = false, length = 100)
    public String getClearanceSubCat() {
        return clearanceSubCat;
    }

    public void setClearanceSubCat(String clearanceSubCat) {
        this.clearanceSubCat = clearanceSubCat;
    }

    @Basic
    @Column(name = "clearanceMainCat", nullable = false, length = 100)
    public String getClearanceMainCat() {
        return clearanceMainCat;
    }

    public void setClearanceMainCat(String clearanceMainCat) {
        this.clearanceMainCat = clearanceMainCat;
    }

    @Basic
    @Column(name = "clearanceDT", nullable = false, length = 100)
    public String getClearanceDt() {
        return clearanceDt;
    }

    public void setClearanceDt(String clearanceDt) {
        this.clearanceDt = clearanceDt;
    }

    @Basic
    @Column(name = "hundredBlck", nullable = false, length = 100)
    public String getHundredBlck() {
        return hundredBlck;
    }

    public void setHundredBlck(String hundredBlck) {
        this.hundredBlck = hundredBlck;
    }

    @Basic
    @Column(name = "district", nullable = false, length = 100)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "zone", nullable = false, length = 100)
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Basic
    @Column(name = "censusTract", nullable = false, length = 100)
    public String getCensusTract() {
        return censusTract;
    }

    public void setCensusTract(String censusTract) {
        this.censusTract = censusTract;
    }

    @Basic
    @Column(name = "vertRadians", nullable = false, length = 100)
    public String getVertRadians() {
        return vertRadians;
    }

    public void setVertRadians(String vertRadians) {
        this.vertRadians = vertRadians;
    }

    @Basic
    @Column(name = "latitude", nullable = false, length = 100)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "location", nullable = false, length = 100)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CsvDataEntity that = (CsvDataEntity) o;

        if (cadCdw != null ? !cadCdw.equals(that.cadCdw) : that.cadCdw != null) return false;
        if (cadNumber != null ? !cadNumber.equals(that.cadNumber) : that.cadNumber != null) return false;
        if (genOffense != null ? !genOffense.equals(that.genOffense) : that.genOffense != null) return false;
        if (clearanceNumber != null ? !clearanceNumber.equals(that.clearanceNumber) : that.clearanceNumber != null)
            return false;
        if (clearanceDesc != null ? !clearanceDesc.equals(that.clearanceDesc) : that.clearanceDesc != null)
            return false;
        if (clearanceSubCat != null ? !clearanceSubCat.equals(that.clearanceSubCat) : that.clearanceSubCat != null)
            return false;
        if (clearanceMainCat != null ? !clearanceMainCat.equals(that.clearanceMainCat) : that.clearanceMainCat != null)
            return false;
        if (clearanceDt != null ? !clearanceDt.equals(that.clearanceDt) : that.clearanceDt != null) return false;
        if (hundredBlck != null ? !hundredBlck.equals(that.hundredBlck) : that.hundredBlck != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (zone != null ? !zone.equals(that.zone) : that.zone != null) return false;
        if (censusTract != null ? !censusTract.equals(that.censusTract) : that.censusTract != null) return false;
        if (vertRadians != null ? !vertRadians.equals(that.vertRadians) : that.vertRadians != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cadCdw != null ? cadCdw.hashCode() : 0;
        result = 31 * result + (cadNumber != null ? cadNumber.hashCode() : 0);
        result = 31 * result + (genOffense != null ? genOffense.hashCode() : 0);
        result = 31 * result + (clearanceNumber != null ? clearanceNumber.hashCode() : 0);
        result = 31 * result + (clearanceDesc != null ? clearanceDesc.hashCode() : 0);
        result = 31 * result + (clearanceSubCat != null ? clearanceSubCat.hashCode() : 0);
        result = 31 * result + (clearanceMainCat != null ? clearanceMainCat.hashCode() : 0);
        result = 31 * result + (clearanceDt != null ? clearanceDt.hashCode() : 0);
        result = 31 * result + (hundredBlck != null ? hundredBlck.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (censusTract != null ? censusTract.hashCode() : 0);
        result = 31 * result + (vertRadians != null ? vertRadians.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
