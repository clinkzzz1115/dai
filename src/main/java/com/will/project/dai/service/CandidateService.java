package com.will.project.dai.service;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import com.will.project.dai.config.DaiConfig;
import com.will.project.dai.entity.Candidate;
import com.will.project.dai.model.CandidateQueryCondition;
import com.will.project.dai.repository.CandidateRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * Created by jiayi.chen on 2017/1/3.
 */
@Service
public class CandidateService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	public boolean save(Candidate candidate) {
		return (!ObjectUtils.isEmpty(candidate)) ? !ObjectUtils.isEmpty(candidateRepository
				.save(candidate)) : false;
	}

	public boolean update(Candidate candidate) {
		if (ObjectUtils.isEmpty(candidate)) {
			logger.info("candidate is empty");
			return false;
		}
		if (!candidateRepository.exists(candidate.getId())) {
			logger.info("id[{}] is not exists", candidate.getId());
			throw new RuntimeException(String.format("未找到id[{}]记录"));
		}
		return !ObjectUtils.isEmpty(candidateRepository.save(candidate));
	}

	public Page<Candidate> query(CandidateQueryCondition condition, PageRequest pageRequest) {
		if (ObjectUtils.isEmpty(pageRequest)) {
			pageRequest = DaiConfig.DEFAULT_PAGE_REQUEST;
		}
		BoolQueryBuilder query = boolQuery();
		if (!ObjectUtils.isEmpty(condition)) {
			if (!StringUtils.isEmpty(condition.getName())) {
				query.must(nestedQuery("baseInfo", matchQuery("baseInfo.name", condition.getName())));
			}
		}
		if (!ObjectUtils.isEmpty(condition)) {
			if (!StringUtils.isEmpty(condition.getProfession())) {
				query.must(nestedQuery("baseInfo",
						matchQuery("baseInfo.profession", condition.getProfession())));
			}
		}
		if (!ObjectUtils.isEmpty(condition)) {
			if (!StringUtils.isEmpty(condition.getMobile())) {
				query.must(nestedQuery("baseInfo",
						matchQuery("baseInfo.mobile", condition.getMobile())));
			}
		}
		if (!ObjectUtils.isEmpty(condition)) {
			if (!StringUtils.isEmpty(condition.getEmail())) {
				query.must(nestedQuery("baseInfo",
						matchQuery("baseInfo.email", condition.getEmail())));
			}
		}
		if (!ObjectUtils.isEmpty(condition)) {
			if (!StringUtils.isEmpty(condition.getIndustry())) {
				query.must(nestedQuery("baseInfo",
						matchQuery("baseInfo.industry", condition.getIndustry())));
			}
		}
		if (!ObjectUtils.isEmpty(condition)) {
			if (!StringUtils.isEmpty(condition.getCompany())) {
				query.must(nestedQuery("workExperiences",
						termQuery("workExperiences.company", condition.getCompany())));
			}
		}
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(query)
				.withPageable(pageRequest).withIndices(DaiConfig.ES_CANDIDATE_INDEX_NAME)
				.withTypes(DaiConfig.ES_CANDIDATE_TYPE_NAME).build();
		Page<Candidate> result = elasticsearchTemplate.queryForPage(searchQuery, Candidate.class);
		logger.info("pageRequest[{}],queryCondition[{}]=>result[{}]", pageRequest, condition,
				result);
		return result;
	}

	public Candidate getById(String id) {
		Assert.notNull(id, "id不能为空");
		return candidateRepository.findOne(id);
	}

	public boolean delete(String id) {
		if (StringUtils.isEmpty(id)) {
			logger.info("id[{}] is empty", id);
			return false;
		}
		if (!candidateRepository.exists(id)) {
			logger.info("id[{}] is not exists", id);
			throw new RuntimeException(String.format("未找到id[{}]记录"));
		}
		candidateRepository.delete(id);
		return true;
	}
}
