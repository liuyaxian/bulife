package com.ruiya.bean;

/**
 * @desc:
 * @author: admin
 * @since: 2022/1/20 10:18
 * @history:
 */
public class CustAction {
    /**
     * 用户IP
     *
     * @Fields custIP
     */
    private String custIP;

    /**
     * 用户代码
     *
     * @Fields custno
     */
    private String custno;

    /**
     * 用户动作代码
     *
     * @Fields action
     */
    private String action;

    /**
     * 描述
     *
     * @Fields remark
     */
    private String remark;

    /**
     * 动作关联的流水号
     *
     * @Fields serialno
     */
    private String serialno;

    /**
     * 来源
     *
     * @Fields callFrom
     */
    private String callFrom;

    public String getCustIP() {
        return custIP;
    }

    public void setCustIP(String custIP) {
        this.custIP = custIP;
    }

    public String getCustno() {
        return custno;
    }

    public void setCustno(String custno) {
        this.custno = custno;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getCallFrom() {
        return callFrom;
    }

    public void setCallFrom(String callFrom) {
        this.callFrom = callFrom;
    }

    @Override
    public String toString() {
        return "CustAction [custIP=" + custIP + ", custno=" + custno + ", action=" + action + ", remark=" + remark
                + ", serialno=" + serialno + ", callFrom=" + callFrom + "]";
    }
}
