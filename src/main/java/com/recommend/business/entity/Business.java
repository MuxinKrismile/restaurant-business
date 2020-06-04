package com.recommend.business.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "business")
public class Business implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private String businessId;

    private String name;

    private String address;

    private String city;

    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    private String latitude;

    private String longitude;

    private String stars;

    @Column(name = "review_count")
    private String reviewCount;

    @Column(name = "is_open")
    private String isOpen;

    private String attributes;

    private String categories;

    private String hours;


}
