package com.easy.expandablerecyclerview.bean;

import java.io.Serializable;

/**
 * @author fuyujie
 * @package: com.easy.expandablerecyclerview.bean
 * @fileNmae CarBean
 * @date 2018/10/22 16:22
 * @describe
 * @org easylinking
 * @email f279259625@gmail.com
 */
public class CarBean implements Serializable {

    private String tag;
    private String name;
    private boolean isExpand = false;

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
}
