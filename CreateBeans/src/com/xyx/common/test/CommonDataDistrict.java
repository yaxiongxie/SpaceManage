package com.xyx.common.test;

import javax.persistence.*;

@Entity
@Table(name = "commonData_district", schema = "SpaceWebsite", catalog = "")
public class CommonDataDistrict {
    private int id;
    private String districtName;
    private String districtFirstChar;
    private int sortInt;
    private CommonDataArea commonDataAreaByAreaTypeId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "district_name")
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Basic
    @Column(name = "district_firstChar")
    public String getDistrictFirstChar() {
        return districtFirstChar;
    }

    public void setDistrictFirstChar(String districtFirstChar) {
        this.districtFirstChar = districtFirstChar;
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

        CommonDataDistrict that = (CommonDataDistrict) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (districtName != null ? !districtName.equals(that.districtName) : that.districtName != null) return false;
        if (districtFirstChar != null ? !districtFirstChar.equals(that.districtFirstChar) : that.districtFirstChar != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        result = 31 * result + (districtFirstChar != null ? districtFirstChar.hashCode() : 0);
        result = 31 * result + sortInt;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "area_type_id", referencedColumnName = "id", nullable = false)
    public CommonDataArea getCommonDataAreaByAreaTypeId() {
        return commonDataAreaByAreaTypeId;
    }

    public void setCommonDataAreaByAreaTypeId(CommonDataArea commonDataAreaByAreaTypeId) {
        this.commonDataAreaByAreaTypeId = commonDataAreaByAreaTypeId;
    }
}
