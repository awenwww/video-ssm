package com.qf.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Speaker {
    private Integer id;
    private String speakerName;
    private String speakerDesc;
    private String speakerJob;
    private String headImgUrl;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "speaker_name")
    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    @Basic
    @Column(name = "speaker_desc")
    public String getSpeakerDesc() {
        return speakerDesc;
    }

    public void setSpeakerDesc(String speakerDesc) {
        this.speakerDesc = speakerDesc;
    }

    @Basic
    @Column(name = "speaker_job")
    public String getSpeakerJob() {
        return speakerJob;
    }

    public void setSpeakerJob(String speakerJob) {
        this.speakerJob = speakerJob;
    }

    @Basic
    @Column(name = "head_img_url")
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", speakerName='" + speakerName + '\'' +
                ", speakerDesc='" + speakerDesc + '\'' +
                ", speakerJob='" + speakerJob + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                '}';
    }
}
