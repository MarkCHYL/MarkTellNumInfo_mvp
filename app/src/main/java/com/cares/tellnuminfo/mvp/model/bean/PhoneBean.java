package com.cares.tellnuminfo.mvp.model.bean;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019/1/22
 * @EMail 2285581945@qq.com
 */
public class PhoneBean {
    /**
     * province : 湖南
     * city : 常德
     * areacode : 0736
     * zip : 415000
     * company : 联通
     * card :
     */

    private String province;
    private String city;
    private String areacode;
    private String zip;
    private String company;
    private String card;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
