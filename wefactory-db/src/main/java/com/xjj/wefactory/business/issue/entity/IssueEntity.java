/****************************************************
 * Description: Entity for 常见问题
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.issue.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class IssueEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public IssueEntity(){}
    private Long sellerId;//seller_id
    private String question;//问题
    private String answer;//答案
    private Date addTime;//add_time
    private String status;//status
    /**
     * 返回seller_id
     * @return seller_id
     */
    public Long getSellerId() {
        return sellerId;
    }
    
    /**
     * 设置seller_id
     * @param sellerId seller_id
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
    
    /**
     * 返回问题
     * @return 问题
     */
    public String getQuestion() {
        return question;
    }
    
    /**
     * 设置问题
     * @param question 问题
     */
    public void setQuestion(String question) {
        this.question = question;
    }
    
    /**
     * 返回答案
     * @return 答案
     */
    public String getAnswer() {
        return answer;
    }
    
    /**
     * 设置答案
     * @param answer 答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    /**
     * 返回add_time
     * @return add_time
     */
    public Date getAddTime() {
        return addTime;
    }
    
    /**
     * 设置add_time
     * @param addTime add_time
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    
    /**
     * 返回status
     * @return status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 设置status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.issue.entity.IssueEntity").append("ID="+this.getId()).toString();
    }
}

