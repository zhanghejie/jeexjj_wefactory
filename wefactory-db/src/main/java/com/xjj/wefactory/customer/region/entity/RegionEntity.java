/****************************************************
 * Description: Entity for 地区信息
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.region.entity;

import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RegionEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public RegionEntity(){}
    private Long pid;//pid
    private String name;//name
    private Integer type;//1, 2, 3, 4
    private Integer code;//code
    /**
     * 返回pid
     * @return pid
     */
    public Long getPid() {
        return pid;
    }
    
    /**
     * 设置pid
     * @param pid pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }
    
    /**
     * 返回name
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 返回1, 2, 3, 4
     * @return 1, 2, 3, 4
     */
    public Integer getType() {
        return type;
    }
    
    /**
     * 设置1, 2, 3, 4
     * @param type 1, 2, 3, 4
     */
    public void setType(Integer type) {
        this.type = type;
    }
    
    /**
     * 返回code
     * @return code
     */
    public Integer getCode() {
        return code;
    }
    
    /**
     * 设置code
     * @param code code
     */
    public void setCode(Integer code) {
        this.code = code;
    }
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.region.entity.RegionEntity").append("ID="+this.getId()).toString();
    }
}

