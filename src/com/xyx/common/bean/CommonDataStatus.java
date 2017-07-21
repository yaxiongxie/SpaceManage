package com.xyx.common.bean;

import javax.persistence.*;

@Entity
@Table(name = "commonData_status", schema = "SpaceWebsite", catalog = "")
public class CommonDataStatus {
    private int id;
    private String statusName;
    private int sortInt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status_name")
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Basic
    @Column(name = "sort_int")
    public int getSortInt() {
        return sortInt;
    }

    public void setSortInt(int sortInt) {
        this.sortInt = sortInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonDataStatus that = (CommonDataStatus) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (statusName != null ? !statusName.equals(that.statusName) : that.statusName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (statusName != null ? statusName.hashCode() : 0);
        result = 31 * result + sortInt;
        return result;
    }
}
