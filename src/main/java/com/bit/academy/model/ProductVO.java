package com.bit.academy.model;

import lombok.Data;
@Data
public class ProductVO {

    private int p_id;
    private String p_name;
    private int p_price;
    private String p_brand;
    private String p_spec;
    private String thumb_name;
    private String thumb_route;
    private String thumb_100;
    private String thumb_300;
    private String thumb_600;
    private String detailimg_name;
    private String detailimg_route;

    //option
    private int po_id;
    private String po_value;
    private int po_price;
    private int po_stock;



    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getP_price() {
        return p_price;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public String getP_brand() {
        return p_brand;
    }

    public void setP_brand(String p_brand) {
        this.p_brand = p_brand;
    }

    public String getP_spec() {
        return p_spec;
    }

    public void setP_spec(String p_spec) {
        this.p_spec = p_spec;
    }

    public String getThumb_name() {
        return thumb_name;
    }

    public void setThumb_name(String thumb_name) {
        this.thumb_name = thumb_name;
    }

    public String getThumb_route() {
        return thumb_route;
    }

    public void setThumb_route(String thumb_route) {
        this.thumb_route = thumb_route;
    }

    public String getThumb_100() {
        return thumb_100;
    }

    public void setThumb_100(String thumb_100) {
        this.thumb_100 = thumb_100;
    }

    public String getThumb_300() {
        return thumb_300;
    }

    public void setThumb_300(String thumb_300) {
        this.thumb_300 = thumb_300;
    }

    public String getThumb_600() {
        return thumb_600;
    }

    public void setThumb_600(String thumb_600) {
        this.thumb_600 = thumb_600;
    }

    public String getDetailimg_name() {
        return detailimg_name;
    }

    public void setDetailimg_name(String detailimg_name) {
        this.detailimg_name = detailimg_name;
    }

    public String getDetailimg_route() {
        return detailimg_route;
    }

    public void setDetailimg_route(String detailimg_route) {
        this.detailimg_route = detailimg_route;
    }
}


