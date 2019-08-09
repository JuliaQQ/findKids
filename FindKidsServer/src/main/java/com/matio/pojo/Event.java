package com.matio.pojo;

import java.util.Date;

public class Event {
    private Integer eventId;

    private String userId;

    private Integer eventDetail;

    private String eventTarget;

    private Date eventTime;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(Integer eventDetail) {
        this.eventDetail = eventDetail;
    }

    public String getEventTarget() {
        return eventTarget;
    }

    public void setEventTarget(String eventTarget) {
        this.eventTarget = eventTarget;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}