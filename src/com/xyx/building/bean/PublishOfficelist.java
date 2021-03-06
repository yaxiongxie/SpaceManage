package com.xyx.building.bean;

import com.xyx.common.bean.CommonDataDecoratedegree;
import com.xyx.common.bean.CommonDataStatus;

import javax.persistence.*;

@Entity
@Table(name = "publish_officelist", schema = "SpaceWebsite", catalog = "")
public class PublishOfficelist {
    private int id;
    private String title;
    private int rentPrice;
    private int areaNum;
    private int floorNum;
    private String information;
    private String createTime;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
    private String ownerInformation;
    private String ownerName;
    private String ownerTelephone;
    private String buildNum;
    private String doorNum;
    private PublishOfficebuildinglist publishOfficebuildinglistByOfficeBuildingId;
    private CommonDataDecoratedegree commonDataDecoratedegreeByDecorateId;
    private CommonDataStatus commonDataStatusByStatusId;

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
    @Column(name = "rent_price")
    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Basic
    @Column(name = "areaNum")
    public int getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(int areaNum) {
        this.areaNum = areaNum;
    }

    @Basic
    @Column(name = "floorNum")
    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
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
    @Column(name = "createTime")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
    @Column(name = "ownerInformation")
    public String getOwnerInformation() {
        return ownerInformation;
    }

    public void setOwnerInformation(String ownerInformation) {
        this.ownerInformation = ownerInformation;
    }

    @Basic
    @Column(name = "ownerName")
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Basic
    @Column(name = "ownerTelephone")
    public String getOwnerTelephone() {
        return ownerTelephone;
    }

    public void setOwnerTelephone(String ownerTelephone) {
        this.ownerTelephone = ownerTelephone;
    }

    @Basic
    @Column(name = "buildNum")
    public String getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(String buildNum) {
        this.buildNum = buildNum;
    }

    @Basic
    @Column(name = "doorNum")
    public String getDoorNum() {
        return doorNum;
    }

    public void setDoorNum(String doorNum) {
        this.doorNum = doorNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublishOfficelist that = (PublishOfficelist) o;

        if (id != that.id) return false;
        if (rentPrice != that.rentPrice) return false;
        if (areaNum != that.areaNum) return false;
        if (floorNum != that.floorNum) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (information != null ? !information.equals(that.information) : that.information != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (image1 != null ? !image1.equals(that.image1) : that.image1 != null) return false;
        if (image2 != null ? !image2.equals(that.image2) : that.image2 != null) return false;
        if (image3 != null ? !image3.equals(that.image3) : that.image3 != null) return false;
        if (image4 != null ? !image4.equals(that.image4) : that.image4 != null) return false;
        if (image5 != null ? !image5.equals(that.image5) : that.image5 != null) return false;
        if (image6 != null ? !image6.equals(that.image6) : that.image6 != null) return false;
        if (ownerInformation != null ? !ownerInformation.equals(that.ownerInformation) : that.ownerInformation != null)
            return false;
        if (ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null) return false;
        if (ownerTelephone != null ? !ownerTelephone.equals(that.ownerTelephone) : that.ownerTelephone != null)
            return false;
        if (buildNum != null ? !buildNum.equals(that.buildNum) : that.buildNum != null) return false;
        if (doorNum != null ? !doorNum.equals(that.doorNum) : that.doorNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + rentPrice;
        result = 31 * result + areaNum;
        result = 31 * result + floorNum;
        result = 31 * result + (information != null ? information.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (image1 != null ? image1.hashCode() : 0);
        result = 31 * result + (image2 != null ? image2.hashCode() : 0);
        result = 31 * result + (image3 != null ? image3.hashCode() : 0);
        result = 31 * result + (image4 != null ? image4.hashCode() : 0);
        result = 31 * result + (image5 != null ? image5.hashCode() : 0);
        result = 31 * result + (image6 != null ? image6.hashCode() : 0);
        result = 31 * result + (ownerInformation != null ? ownerInformation.hashCode() : 0);
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        result = 31 * result + (ownerTelephone != null ? ownerTelephone.hashCode() : 0);
        result = 31 * result + (buildNum != null ? buildNum.hashCode() : 0);
        result = 31 * result + (doorNum != null ? doorNum.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "officeBuilding_id", referencedColumnName = "id", nullable = false)
    public PublishOfficebuildinglist getPublishOfficebuildinglistByOfficeBuildingId() {
        return publishOfficebuildinglistByOfficeBuildingId;
    }

    public void setPublishOfficebuildinglistByOfficeBuildingId(PublishOfficebuildinglist publishOfficebuildinglistByOfficeBuildingId) {
        this.publishOfficebuildinglistByOfficeBuildingId = publishOfficebuildinglistByOfficeBuildingId;
    }

    @ManyToOne
    @JoinColumn(name = "decorate_id", referencedColumnName = "id", nullable = false)
    public CommonDataDecoratedegree getCommonDataDecoratedegreeByDecorateId() {
        return commonDataDecoratedegreeByDecorateId;
    }

    public void setCommonDataDecoratedegreeByDecorateId(CommonDataDecoratedegree commonDataDecoratedegreeByDecorateId) {
        this.commonDataDecoratedegreeByDecorateId = commonDataDecoratedegreeByDecorateId;
    }

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public CommonDataStatus getCommonDataStatusByStatusId() {
        return commonDataStatusByStatusId;
    }

    public void setCommonDataStatusByStatusId(CommonDataStatus commonDataStatusByStatusId) {
        this.commonDataStatusByStatusId = commonDataStatusByStatusId;
    }
}
