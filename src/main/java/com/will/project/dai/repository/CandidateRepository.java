package com.will.project.dai.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.will.project.dai.entity.Candidate;

/**
 * Created by jiayi.chen on 2017/1/3.
 */
public interface CandidateRepository extends ElasticsearchRepository<Candidate,String> {

}
