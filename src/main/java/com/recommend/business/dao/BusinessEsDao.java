package com.recommend.business.dao;


import com.recommend.business.entity.BusinessEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BusinessEsDao extends ElasticsearchRepository<BusinessEs, Long> {

    Page<BusinessEs> findByNameLike(String name, Pageable pageable);

    Page<BusinessEs> findByCategories(String categories, Pageable pageable);

    BusinessEs findByBusinessId(String businessId);
}
