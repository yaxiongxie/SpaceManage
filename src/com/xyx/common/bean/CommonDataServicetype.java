package com.xyx.common.bean;

import javax.persistence.*;

@Entity
@Table(name = "commonData_servicetype", schema = "SpaceWebsite", catalog = "")
public class CommonDataServicetype {
    private int id;
    private String serviceTypeName;
    private int sortInt;
    private String information;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "service_type_name")
    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    @Basic
    @Column(name = "sort_int")
    public int getSortInt() {
        return sortInt;
    }

    public void setSortInt(int sortInt) {
        this.sortInt = sortInt;
    }

    @Basic
    @Column(name = "information")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonDataServicetype that = (CommonDataServicetype) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (serviceTypeName != null ? !serviceTypeName.equals(that.serviceTypeName) : that.serviceTypeName != null)
            return false;
        if (information != null ? !information.equals(that.information) : that.information != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (serviceTypeName != null ? serviceTypeName.hashCode() : 0);
        result = 31 * result + sortInt;
        result = 31 * result + (information != null ? information.hashCode() : 0);
        return result;
    }
}
