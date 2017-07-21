package com.xyx.common.bean;

import javax.persistence.*;

@Entity
@Table(name = "commonData_decoratedegree", schema = "SpaceWebsite", catalog = "")
public class CommonDataDecoratedegree {
    private int id;
    private String decorateName;
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
    @Column(name = "decorate_name")
    public String getDecorateName() {
        return decorateName;
    }

    public void setDecorateName(String decorateName) {
        this.decorateName = decorateName;
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

        CommonDataDecoratedegree that = (CommonDataDecoratedegree) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (decorateName != null ? !decorateName.equals(that.decorateName) : that.decorateName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (decorateName != null ? decorateName.hashCode() : 0);
        result = 31 * result + sortInt;
        return result;
    }
}
