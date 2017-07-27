package com.xyx.building.bean;

import com.xyx.common.bean.*;

import javax.persistence.*;

@Entity
@Table(name = "publish_officebuildinglist", schema = "SpaceWebsite", catalog = "")
public class PublishOfficebuildinglist {
    private int id;
    private String title;
    private String address;
    private int rentMaxPrice;
    private int rentMinPrice;
    private String transport;
    private String information;
    private String buildTime;
    private String createTime;
    private String location;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
    private int floorCount;
    private String informationMatch;
    private String rentArea;
    private int rentCount;
    private int rentMaxArea;
    private int rentMinArea;
    private CommonDataArea commonDataAreaByAreaTypeId;
    private CommonDataStatus commonDataStatusByStatusId;
    private CommonDataSubway commonDataSubwayBySubwayId;
    private CommonDataDistrict commonDataDistrictByDistrictTypeId;
    private CommonDataSourcetype commonDataSourcetypeByBuildingTypeId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "rent_max_price")
    public int getRentMaxPrice() {
        return rentMaxPrice;
    }

    public void setRentMaxPrice(int rentMaxPrice) {
        this.rentMaxPrice = rentMaxPrice;
    }

    @Basic
    @Column(name = "rent_min_price")
    public int getRentMinPrice() {
        return rentMinPrice;
    }

    public void setRentMinPrice(int rentMinPrice) {
        this.rentMinPrice = rentMinPrice;
    }

    @Basic
    @Column(name = "transport")
    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    @Basic
    @Column(name = "information")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Basic
    @Column(name = "buildTime")
    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    @Basic
    @Column(name = "createTime")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "image1")
    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    @Basic
    @Column(name = "image2")
    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    @Basic
    @Column(name = "image3")
    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    @Basic
    @Column(name = "image4")
    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    @Basic
    @Column(name = "image5")
    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    @Basic
    @Column(name = "image6")
    public String getImage6() {
        return image6;
    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    @Basic
    @Column(name = "floor_count")
    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    @Basic
    @Column(name = "information_match")
    public String getInformationMatch() {
        return informationMatch;
    }

    public void setInformationMatch(String informationMatch) {
        this.informationMatch = informationMatch;
    }

    @Basic
    @Column(name = "rent_area")
    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    @Basic
    @Column(name = "rent_count")
    public int getRentCount() {
        return rentCount;
    }

    public void setRentCount(int rentCount) {
        this.rentCount = rentCount;
    }

    @Basic
    @Column(name = "rent_max_area")
    public int getRentMaxArea() {
        return rentMaxArea;
    }

    public void setRentMaxArea(int rentMaxArea) {
        this.rentMaxArea = rentMaxArea;
    }

    @Basic
    @Column(name = "rent_min_area")
    public int getRentMinArea() {
        return rentMinArea;
    }

    public void setRentMinArea(int rentMinArea) {
        this.rentMinArea = rentMinArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublishOfficebuildinglist that = (PublishOfficebuildinglist) o;

        if (id != that.id) return false;
        if (rentMaxPrice != that.rentMaxPrice) return false;
        if (rentMinPrice != that.rentMinPrice) return false;
        if (floorCount != that.floorCount) return false;
        if (rentCount != that.rentCount) return false;
        if (rentMaxArea != that.rentMaxArea) return false;
        if (rentMinArea != that.rentMinArea) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (transport != null ? !transport.equals(that.transport) : that.transport != null) return false;
        if (information != null ? !information.equals(that.information) : that.information != null) return false;
        if (buildTime != null ? !buildTime.equals(that.buildTime) : that.buildTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (image1 != null ? !image1.equals(that.image1) : that.image1 != null) return false;
        if (image2 != null ? !image2.equals(that.image2) : that.image2 != null) return false;
        if (image3 != null ? !image3.equals(that.image3) : that.image3 != null) return false;
        if (image4 != null ? !image4.equals(that.image4) : that.image4 != null) return false;
        if (image5 != null ? !image5.equals(that.image5) : that.image5 != null) return false;
        if (image6 != null ? !image6.equals(that.image6) : that.image6 != null) return false;
        if (informationMatch != null ? !informationMatch.equals(that.informationMatch) : that.informationMatch != null)
            return false;
        if (rentArea != null ? !rentArea.equals(that.rentArea) : that.rentArea != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + rentMaxPrice;
        result = 31 * result + rentMinPrice;
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (information != null ? information.hashCode() : 0);
        result = 31 * result + (buildTime != null ? buildTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (image1 != null ? image1.hashCode() : 0);
        result = 31 * result + (image2 != null ? image2.hashCode() : 0);
        result = 31 * result + (image3 != null ? image3.hashCode() : 0);
        result = 31 * result + (image4 != null ? image4.hashCode() : 0);
        result = 31 * result + (image5 != null ? image5.hashCode() : 0);
        result = 31 * result + (image6 != null ? image6.hashCode() : 0);
        result = 31 * result + floorCount;
        result = 31 * result + (informationMatch != null ? informationMatch.hashCode() : 0);
        result = 31 * result + (rentArea != null ? rentArea.hashCode() : 0);
        result = 31 * result + rentCount;
        result = 31 * result + rentMaxArea;
        result = 31 * result + rentMinArea;
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

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public CommonDataStatus getCommonDataStatusByStatusId() {
        return commonDataStatusByStatusId;
    }

    public void setCommonDataStatusByStatusId(CommonDataStatus commonDataStatusByStatusId) {
        this.commonDataStatusByStatusId = commonDataStatusByStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "subway_id", referencedColumnName = "id")
    public CommonDataSubway getCommonDataSubwayBySubwayId() {
        return commonDataSubwayBySubwayId;
    }

    public void setCommonDataSubwayBySubwayId(CommonDataSubway commonDataSubwayBySubwayId) {
        this.commonDataSubwayBySubwayId = commonDataSubwayBySubwayId;
    }

    @ManyToOne
    @JoinColumn(name = "district_type_id", referencedColumnName = "id")
    public CommonDataDistrict getCommonDataDistrictByDistrictTypeId() {
        return commonDataDistrictByDistrictTypeId;
    }

    public void setCommonDataDistrictByDistrictTypeId(CommonDataDistrict commonDataDistrictByDistrictTypeId) {
        this.commonDataDistrictByDistrictTypeId = commonDataDistrictByDistrictTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "building_type_id", referencedColumnName = "id", nullable = false)
    public CommonDataSourcetype getCommonDataSourcetypeByBuildingTypeId() {
        return commonDataSourcetypeByBuildingTypeId;
    }

    public void setCommonDataSourcetypeByBuildingTypeId(CommonDataSourcetype commonDataSourcetypeByBuildingTypeId) {
        this.commonDataSourcetypeByBuildingTypeId = commonDataSourcetypeByBuildingTypeId;
    }
}
