package com.recommend.business.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Document(indexName = "business")
public class BusinessEs implements Serializable {

    @Id
    private String businessId;

    private String name;

    private String address;

    private String city;

    private String state;

    private String postalCode;

    private String latitude;

    private String longitude;

    private String stars;

    private String reviewCount;

    private String isOpen;

    private String attributes;

    private String categories;

    private String hours;

}
