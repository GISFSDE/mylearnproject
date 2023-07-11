package com.jeethink.project.dzzlrkxt.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * @TableName dz_project
 */

public class DzProject implements Serializable {
    private Checkbox cb = new Checkbox();
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 汇交凭证编号
     */
    private String hjpzbh;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 发改投资代码
     */
    private String fgtzdm;

    /**
     * 资料名称
     */
    private String zlmc;

    /**
     * 所属区县
     */
    private String qy;

    /**
     * 项目位置
     */
    private String projectLocation;

    /**
     * 汇交单位
     */
    private String hjdw;

    /**
     * 汇交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hjTime;

    /**
     * 汇交单位联系人
     */
    private String hjdwlxr;

    /**
     * 汇交单位联系人联系方式
     */
    private String hjdwlxrdh;

    /**
     * 资料形成单位
     */
    private String zlxcdw;

    /**
     * 形成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date xcTime;

    /**
     * 资料形成单位联系人
     */
    private String zlxcdwlxr;

    /**
     * 资料形成单位联系人联系方式
     */
    private String zlxcdwlxrdh;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rkTime;

    /**
     * 入库联系人
     */
    private String rkLxr;

    /**
     * 备注
     */
    private String remark;
    private String order;
    private String drillNum;

    /**
     * 项目范围矢量
     */
    private Object shp;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DzProject dzProject = (DzProject) o;
        return Objects.equals(projectId, dzProject.projectId) && Objects.equals(hjpzbh, dzProject.hjpzbh) && Objects.equals(projectName, dzProject.projectName) && Objects.equals(fgtzdm, dzProject.fgtzdm) && Objects.equals(zlmc, dzProject.zlmc) && Objects.equals(qy, dzProject.qy) && Objects.equals(projectLocation, dzProject.projectLocation) && Objects.equals(hjdw, dzProject.hjdw) && Objects.equals(hjTime, dzProject.hjTime) && Objects.equals(hjdwlxr, dzProject.hjdwlxr) && Objects.equals(hjdwlxrdh, dzProject.hjdwlxrdh) && Objects.equals(zlxcdw, dzProject.zlxcdw) && Objects.equals(xcTime, dzProject.xcTime) && Objects.equals(zlxcdwlxr, dzProject.zlxcdwlxr) && Objects.equals(zlxcdwlxrdh, dzProject.zlxcdwlxrdh) && Objects.equals(rkTime, dzProject.rkTime) && Objects.equals(rkLxr, dzProject.rkLxr) && Objects.equals(remark, dzProject.remark) && Objects.equals(order, dzProject.order) && Objects.equals(drillNum, dzProject.drillNum) && Objects.equals(shp, dzProject.shp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, hjpzbh, projectName, fgtzdm, zlmc, qy, projectLocation, hjdw, hjTime, hjdwlxr, hjdwlxrdh, zlxcdw, xcTime, zlxcdwlxr, zlxcdwlxrdh, rkTime, rkLxr, remark, order, drillNum, shp);
    }
    public Checkbox getCb() {
        return cb;
    }

    public void setSelected(Checkbox cb) {
        this.cb = cb;
    }
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getHjpzbh() {
        return hjpzbh;
    }

    public void setHjpzbh(String hjpzbh) {
        this.hjpzbh = hjpzbh;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFgtzdm() {
        return fgtzdm;
    }

    public void setFgtzdm(String fgtzdm) {
        this.fgtzdm = fgtzdm;
    }

    public String getZlmc() {
        return zlmc;
    }

    public void setZlmc(String zlmc) {
        this.zlmc = zlmc;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public String getHjdw() {
        return hjdw;
    }

    public void setHjdw(String hjdw) {
        this.hjdw = hjdw;
    }

    public Date getHjTime() {
        return hjTime;
    }

    public void setHjTime(Date hjTime) {
        this.hjTime = hjTime;
    }

    public String getHjdwlxr() {
        return hjdwlxr;
    }

    public void setHjdwlxr(String hjdwlxr) {
        this.hjdwlxr = hjdwlxr;
    }

    public String getHjdwlxrdh() {
        return hjdwlxrdh;
    }

    public void setHjdwlxrdh(String hjdwlxrdh) {
        this.hjdwlxrdh = hjdwlxrdh;
    }

    public String getZlxcdw() {
        return zlxcdw;
    }

    public void setZlxcdw(String zlxcdw) {
        this.zlxcdw = zlxcdw;
    }

    public Date getXcTime() {
        return xcTime;
    }

    public void setXcTime(Date xcTime) {
        this.xcTime = xcTime;
    }

    public String getZlxcdwlxr() {
        return zlxcdwlxr;
    }

    public void setZlxcdwlxr(String zlxcdwlxr) {
        this.zlxcdwlxr = zlxcdwlxr;
    }

    public String getZlxcdwlxrdh() {
        return zlxcdwlxrdh;
    }

    public void setZlxcdwlxrdh(String zlxcdwlxrdh) {
        this.zlxcdwlxrdh = zlxcdwlxrdh;
    }

    public Date getRkTime() {
        return rkTime;
    }

    public void setRkTime(Date rkTime) {
        this.rkTime = rkTime;
    }

    public String getRkLxr() {
        return rkLxr;
    }

    public void setRkLxr(String rkLxr) {
        this.rkLxr = rkLxr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDrillNum() {
        return drillNum;
    }

    public void setDrillNum(String drillNum) {
        this.drillNum = drillNum;
    }

    public Object getShp() {
        return shp;
    }

    public void setShp(Object shp) {
        this.shp = shp;
    }
}