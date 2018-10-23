package com.easy.expandablerecyclerview;

import com.easy.expandablerecyclerview.bean.CarBrandBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fuyujie
 * @package: com.easy.expandablerecyclerview
 * @fileNmae DataSource
 * @date 2018/10/22 15:56
 * @describe
 * @org easylinking
 * @email f279259625@gmail.com
 */
public class DataSource {

    private static String[] BRAND = new String[]{
            "奥迪", "阿斯顿马丁", "奔驰",
            "宝马", "保时捷", "大众",
            "丰田", "红旗", "捷豹",
            "凯迪拉克", "卡尔森", "雷克萨斯",
            "乐视", "林肯", "雷诺",
            "劳斯莱斯", "玛莎拉蒂", "迈巴赫",
            "日产", "双龙", "泰卡特",
            "沃尔沃", "现代", "雪铁龙",
            "英菲尼迪", "正道汽车"
    };
    private static String[] TAG = new String[]{
            "A", "B", "C",
            "D", "E", "F",
            "G", "H", "I",
            "J", "K", "L",
            "M", "N", "O",
            "P", "Q", "R",
            "S", "T", "U",
            "V", "W", "X",
            "Y", "Z"
    };


    public List<CarBrandBean> getData() {

        List<CarBrandBean> brandBeans = new ArrayList<>();

        for (int i = 0; i < TAG.length; i++) {

            CarBrandBean carBrandBean = new CarBrandBean();
            carBrandBean.setTag(TAG[i]);
            carBrandBean.setName(BRAND[i]);
            carBrandBean.setGroup(true);
            carBrandBean.setExpand(false);

            List<CarBrandBean> seriesBeans = new ArrayList<>();
            for (int z = 0; z < 3; z++) {
                CarBrandBean seriesBean = new CarBrandBean();
                seriesBean.setGroup(false);
                seriesBean.setName(BRAND[i] + "系列:" + z);
                seriesBean.setTag(TAG[i]);
                seriesBean.setParent(BRAND[i]);
                seriesBeans.add(seriesBean);
            }

            carBrandBean.setSeries(seriesBeans);

            brandBeans.add(carBrandBean);
        }

        return brandBeans;

    }

}
