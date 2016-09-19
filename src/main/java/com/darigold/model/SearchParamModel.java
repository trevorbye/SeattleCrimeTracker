package com.darigold.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by trevorBye on 9/15/16.
 */
public class SearchParamModel {

@NotEmpty
@Size(min = 6, max = 40)
private String searchAddress;

@NotEmpty(message = "enter a value.")
private String searchDistance;

@NotEmpty(message = "enter a value.")
private String searchTime;


public String getSearchAddress() {
    return searchAddress;
}

public void setSearchAddress(String searchAddress) {
    this.searchAddress = searchAddress;
}

public String getSearchDistance() {
    return searchDistance;
}

public void setSearchDistance(String searchDistance) {
    this.searchDistance = searchDistance;
}

public String getSearchTime() {
    return searchTime;
}

public void setSearchTime(String searchTime) {
    this.searchTime = searchTime;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SearchParamModel that = (SearchParamModel) o;

    if (searchAddress != null ? !searchAddress.equals(that.searchAddress) : that.searchAddress != null)
        return false;
    if (searchDistance != null ? !searchDistance.equals(that.searchDistance) : that.searchDistance != null)
        return false;
    return searchTime != null ? searchTime.equals(that.searchTime) : that.searchTime == null;

}

@Override
public int hashCode() {
    int result = searchAddress != null ? searchAddress.hashCode() : 0;
    result = 31 * result + (searchDistance != null ? searchDistance.hashCode() : 0);
    result = 31 * result + (searchTime != null ? searchTime.hashCode() : 0);
    return result;
}
}
