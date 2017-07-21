package com.xyx.common.bean;

import javax.persistence.*;

@Entity
@Table(name = "commonData_sex", schema = "SpaceWebsite", catalog = "")
public class CommonDataSex {
    private int id;
    private String sexName;
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
    @Column(name = "sex_name")
    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
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

        CommonDataSex that = (CommonDataSex) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (sexName != null ? !sexName.equals(that.sexName) : that.sexName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sexName != null ? sexName.hashCode() : 0);
        result = 31 * result + sortInt;
        return result;
    }
}
