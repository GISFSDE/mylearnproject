package com.jeethink.project.dzzlrkxt.domain;

import com.jeethink.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * (DzDrill)实体类
 *
 * @author makejava
 * @since 2023-06-28 15:15:12
 */
public class DzDrill implements Serializable {
    private static final long serialVersionUID = -58281636878074460L;
    /**
     * 钻孔ID
     */
    @Excel(name = "序号")
    private Long drillId;
    /**
     * 钻孔编号
     */
    @Excel(name = "编号")
    private String drillBh;
    /**
     * 钻孔项目ID
     */
    // @Excel(name = "项目ID")
    private Long projectId;
    private String projectName;
    /**
     * 钻孔类型
     */
    @Excel(name = "类型")
    private String drillType;
    /**
     * 钻孔位置-经度
     */
    @Excel(name = "坐标位置Y")
    private String localtionX;
    /**
     * 钻孔位置-纬度
     */
    @Excel(name = "坐标位置X")
    private String localtionY;
    /**
     * 钻孔高程
     */
    @Excel(name = "高程")
    private String drillElevation;
    /**
     * 钻孔孔深
     */
    @Excel(name = "孔深")
    private String drillDepth;
    /**
     * 地下水稳定水位深度
     */
    @Excel(name = "地下水稳定水位深度")
    private String groundwaterDepth;
    /**
     * 地下水稳定水位高程
     */
    @Excel(name = "地下水稳定水位高程")
    private String groundwaterElevation;
    /**
     * 位置矢量
     */
    private String localtion;
    private Checkbox cb = new Checkbox();
    /**
     * 创建时间
     */
    private Date createTime;
    public Checkbox getCb() {
        return cb;
    }

    public void setCb(Checkbox cb) {
        this.cb = cb;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getDrillId() {
        return drillId;
    }

    public void setDrillId(Long drillId) {
        this.drillId = drillId;
    }

    public String getDrillBh() {
        return drillBh;
    }

    public void setDrillBh(String drillBh) {
        this.drillBh = drillBh;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getDrillType() {
        return drillType;
    }

    public void setDrillType(String drillType) {
        this.drillType = drillType;
    }

    public String getLocaltionX() {
        return localtionX;
    }

    public void setLocaltionX(String localtionX) {
        this.localtionX = localtionX;
    }

    public String getLocaltionY() {
        return localtionY;
    }

    public void setLocaltionY(String localtionY) {
        this.localtionY = localtionY;
    }

    public String getDrillElevation() {
        return drillElevation;
    }

    public void setDrillElevation(String drillElevation) {
        this.drillElevation = drillElevation;
    }

    public String getDrillDepth() {
        return drillDepth;
    }

    public void setDrillDepth(String drillDepth) {
        this.drillDepth = drillDepth;
    }

    public String getGroundwaterDepth() {
        return groundwaterDepth;
    }

    public void setGroundwaterDepth(String groundwaterDepth) {
        this.groundwaterDepth = groundwaterDepth;
    }

    public String getGroundwaterElevation() {
        return groundwaterElevation;
    }

    public void setGroundwaterElevation(String groundwaterElevation) {
        this.groundwaterElevation = groundwaterElevation;
    }
    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

