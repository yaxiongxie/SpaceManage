package com.xyx.common.test;

import javax.persistence.*;

@Entity
@Table(name = "building_log", schema = "SpaceWebsite", catalog = "")
public class BuildingLog {
    private int id;
    private String relateId;
    private String logText;
    private String createtime;
    private String operater;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "relate_id")
    public String getRelateId() {
        return relateId;
    }

    public void setRelateId(String relateId) {
        this.relateId = relateId;
    }

    @Basic
    @Column(name = "log_text")
    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    @Basic
    @Column(name = "createtime")
    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildingLog that = (BuildingLog) o;

        if (id != that.id) return false;
        if (relateId != null ? !relateId.equals(that.relateId) : that.relateId != null) return false;
        if (logText != null ? !logText.equals(that.logText) : that.logText != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (relateId != null ? relateId.hashCode() : 0);
        result = 31 * result + (logText != null ? logText.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "operater")
    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }
}
