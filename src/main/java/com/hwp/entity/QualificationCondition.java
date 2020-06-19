package com.hwp.entity;

public class QualificationCondition {

    private String startDate;  //开始时间
    private String endDate;  //结束时间

    private Integer check;
    private Integer type;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QualificationCondition{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", check=" + check +
                ", type=" + type +
                '}';
    }
}
