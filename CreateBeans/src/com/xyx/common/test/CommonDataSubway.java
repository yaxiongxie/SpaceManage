package com.xyx.common.test;

import javax.persistence.*;

@Entity
@Table(name = "commonData_subway", schema = "SpaceWebsite", catalog = "")
public class CommonDataSubway {
    private int id;
    private String subwayName;
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
    @Column(name = "subway_name")
    public String getSubwayName() {
        return subwayName;
    }

    public void setSubwayName(String subwayName) {
        this.subwayName = subwayName;
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

        CommonDataSubway that = (CommonDataSubway) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (subwayName != null ? !subwayName.equals(that.subwayName) : that.subwayName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subwayName != null ? subwayName.hashCode() : 0);
        result = 31 * result + sortInt;
        return result;
    }
}
