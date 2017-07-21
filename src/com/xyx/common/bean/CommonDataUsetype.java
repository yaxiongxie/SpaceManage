package com.xyx.common.bean;

import javax.persistence.*;

@Entity
@Table(name = "commonData_usetype", schema = "SpaceWebsite", catalog = "")
public class CommonDataUsetype {
    private int id;
    private String useName;
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
    @Column(name = "use_name")
    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
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

        CommonDataUsetype that = (CommonDataUsetype) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (useName != null ? !useName.equals(that.useName) : that.useName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (useName != null ? useName.hashCode() : 0);
        result = 31 * result + sortInt;
        return result;
    }
}
