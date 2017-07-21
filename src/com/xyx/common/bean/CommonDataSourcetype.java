package com.xyx.common.bean;

import javax.persistence.*;

@Entity
@Table(name = "commonData_sourcetype", schema = "SpaceWebsite", catalog = "")
public class CommonDataSourcetype {
    private int id;
    private String sourceTypeName;
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
    @Column(name = "source_type_name")
    public String getSourceTypeName() {
        return sourceTypeName;
    }

    public void setSourceTypeName(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName;
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

        CommonDataSourcetype that = (CommonDataSourcetype) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (sourceTypeName != null ? !sourceTypeName.equals(that.sourceTypeName) : that.sourceTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sourceTypeName != null ? sourceTypeName.hashCode() : 0);
        result = 31 * result + sortInt;
        return result;
    }
}
