package com.matio.pojo;

import java.util.Date;

public class Video {
    private String videoId;

    private String videoPublisherId;

    private String videoTitle;

    private Date videoPublishTime;

    private String videoDesc;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoPublisherId() {
        return videoPublisherId;
    }

    public void setVideoPublisherId(String videoPublisherId) {
        this.videoPublisherId = videoPublisherId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public Date getVideoPublishTime() {
        return videoPublishTime;
    }

    public void setVideoPublishTime(Date videoPublishTime) {
        this.videoPublishTime = videoPublishTime;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }
}