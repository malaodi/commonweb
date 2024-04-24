package com.module.pojo;

import java.util.Date;


/**
 * 标签表 sorttype
 *
 * @author administrator
 *
 */
public class Sorttype {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 标签名称
     */
    private String sortname;
    /**
     * 标签描述
     */
    private String intro;
    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 设置：ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：标签名称
     */
    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    /**
     * 获取：标签名称
     */
    public String getSortname() {
        return sortname;
    }

    /**
     * 设置：标签描述
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 获取：标签描述
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置：创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }
}
