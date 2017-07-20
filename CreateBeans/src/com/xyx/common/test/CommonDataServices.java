package com.xyx.common.test;

import javax.persistence.*;

@Entity
@Table(name = "commonData_services", schema = "SpaceWebsite", catalog = "")
public class CommonDataServices {
    private int id;
    private String serviceName;
    private String information;
    private String smallImage;
    private String largeImage;
    private int sortInt;
    private String smallImageColor;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "service_name")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
    @Column(name = "small_image")
    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    @Basic
    @Column(name = "large_image")
    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
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
    @Column(name = "small_image_color")
    public String getSmallImageColor() {
        return smallImageColor;
    }

    public void setSmallImageColor(String smallImageColor) {
        this.smallImageColor = smallImageColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonDataServices that = (CommonDataServices) o;

        if (id != that.id) return false;
        if (sortInt != that.sortInt) return false;
        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
        if (information != null ? !information.equals(that.information) : that.information != null) return false;
        if (smallImage != null ? !smallImage.equals(that.smallImage) : that.smallImage != null) return false;
        if (largeImage != null ? !largeImage.equals(that.largeImage) : that.largeImage != null) return false;
        if (smallImageColor != null ? !smallImageColor.equals(that.smallImageColor) : that.smallImageColor != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (information != null ? information.hashCode() : 0);
        result = 31 * result + (smallImage != null ? smallImage.hashCode() : 0);
        result = 31 * result + (largeImage != null ? largeImage.hashCode() : 0);
        result = 31 * result + sortInt;
        result = 31 * result + (smallImageColor != null ? smallImageColor.hashCode() : 0);
        return result;
    }
}
