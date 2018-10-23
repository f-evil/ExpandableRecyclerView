package com.easy.expandablerecyclerview.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author fuyujie
 * @package: com.easy.expandablerecyclerview.bean
 * @fileNmae CarBrandBean
 * @date 2018/10/22 15:34
 * @describe
 * @org easylinking
 * @email f279259625@gmail.com
 */
public class CarBrandBean implements Serializable {

    private String tag;
    private String name;
    private String parent;
    private boolean isExpand = false;
    private boolean isGroup=false;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<CarBrandBean> mSeries;

    public List<CarBrandBean> getSeries() {
        return mSeries;
    }

    public void setSeries(List<CarBrandBean> series) {
        mSeries = series;
    }
}
