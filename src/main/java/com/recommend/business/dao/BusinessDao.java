package com.recommend.business.dao;

import com.recommend.business.entity.Business;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessDao extends JpaRepository<Business, Long>{
    @Query(value = "select * from business where business_id=:businessId", nativeQuery = true)
    public List<Business> findBusiness(@Param("businessId") String businessId);
}
