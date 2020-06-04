package com.recommend.business.controller;

import com.recommend.business.dao.BusinessDao;
import com.recommend.business.dao.BusinessEsDao;
import com.recommend.business.entity.Business;
import com.recommend.business.entity.BusinessEs;
import com.recommend.business.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BusinessController {
    @Autowired
    BusinessDao businessDao;

    @Autowired
    BusinessEsDao businessEsDao;

    @Autowired
    AuthService authService;

    @RequestMapping("/hello")
    public String Hello() {
        return authService.getAuthUrl();
    }

    @RequestMapping("/auth")
    public Object auth() {
        return authService.auth("4285a1f10a72504c590af86037816a5fb701");
    }

    @RequestMapping(value = "/queryBusinesses", method = RequestMethod.GET)
    public Iterable<BusinessEs> queryBusinesses(@RequestParam(required = false) Integer page,
                                                @RequestParam(required = false) Integer size) {
        if (size == null) size = 10;
        if(page==null) page=0;
        Pageable pageable = PageRequest.of(page, size);
        Page<BusinessEs> rs =  this.businessEsDao.findAll(pageable);
        return  rs.toList();
    }

    @RequestMapping(value = "/queryBusinessById", method = RequestMethod.GET)
    public BusinessEs queryBusinessById(@RequestParam String businessId) {
        return businessEsDao.findByBusinessId(businessId);
    }

    @RequestMapping(value = "/queryBusinessByName")
    public Object queryBusinessByName(@RequestParam String name,
                        @RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer size) {
        if (size == null) size = 10;
        if(page==null) page=0;
        Pageable pageable = PageRequest.of(page, size);
        Page<BusinessEs> rs =  this.businessEsDao.findByNameLike(name, pageable);
        return  rs.toList();
    }

    @RequestMapping(value = "/sortBusinessByCategories")
    public Object sortBusinessByCategories(@RequestParam String categories,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        if (size == null) size = 10;
        if(page==null) page=0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "stars"));
        Page<BusinessEs> rs =  this.businessEsDao.findByCategories(categories.trim(), pageable);
        return  rs.toList();
    }

    @RequestMapping(value = "/updateBusiness",method = RequestMethod.PUT)
    public String updateBusiness(@RequestBody BusinessEs data) {
        this.businessEsDao.save(data);
        //Business business=new Business(data.getBusinessId(),data.getName(),data.getAddress(),data.getCity(),data.getState(),data.getPostalCode(),data.getLatitude(),data.getLongitude(),data.getStars(),data.getReviewCount(),data.getIsOpen(),data.getAttributes(),data.getCategories(),data.getHours());
        Business business=new Business();
        business.setBusinessId(data.getBusinessId());
        business.setName(data.getName());
        business.setAddress(data.getAddress());
        business.setCity(data.getCity());
        business.setState(data.getState());
        business.setPostalCode(data.getPostalCode());
        business.setLatitude(data.getLatitude());
        business.setLongitude(data.getLongitude());
        business.setStars(data.getStars());
        business.setReviewCount(data.getReviewCount());
        business.setIsOpen(data.getIsOpen());
        business.setAttributes(data.getAttributes());
        business.setCategories(data.getCategories());
        business.setHours(data.getHours());
        businessDao.save(business);
        return "success";
    }

    @RequestMapping("/init")
    public String initEsData() {
        List<Business> allData = this.businessDao.findAll();
        List<BusinessEs> toSave = new ArrayList<>();
        for (Business item : allData) {
            BusinessEs businessEs = new BusinessEs();
            businessEs.setBusinessId(item.getBusinessId());
            businessEs.setName(item.getName());
            businessEs.setAddress(item.getAddress());
            businessEs.setCity(item.getCity());
            businessEs.setState(item.getState());
            businessEs.setPostalCode(item.getPostalCode());
            businessEs.setLatitude(item.getLatitude());
            businessEs.setLongitude(item.getLongitude());
            businessEs.setStars(item.getStars());
            businessEs.setReviewCount(item.getReviewCount());
            businessEs.setIsOpen(item.getIsOpen());
            businessEs.setAttributes(item.getAttributes());
            businessEs.setCategories(item.getCategories());
            businessEs.setHours(item.getHours());
            toSave.add(businessEs);
        }
        this.businessEsDao.saveAll(toSave);
        return "success";
    }
}
