package com.recommend.business.bean;

import com.recommend.business.entity.BusinessEs;

import java.util.List;

@lombok.Data
public class ListData {
    Integer num;
    List<BusinessEs> dataList;
}
