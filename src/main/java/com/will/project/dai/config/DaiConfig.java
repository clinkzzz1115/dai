package com.will.project.dai.config;

import org.springframework.data.domain.PageRequest;

/**
 * Created by jiayi.chen on 2016/12/31.
 */
public class DaiConfig {

	/**
	 * ES索引名字
	 */
	public static final String ES_CANDIDATE_INDEX_NAME = "dai";
	/**
	 * ES类型
	 */
	public static final String ES_CANDIDATE_TYPE_NAME = "candidate";

	/**
	 * 默认分页
	 */
	public static final PageRequest DEFAULT_PAGE_REQUEST = new PageRequest(0, 20);

	/**
	 * IK分词器
	 */
	public static final String ANALYZER_IK_MAX_WORD = "ik_max_word";
	/**
	 * IK
	 */
	public static final String SEARCH_ANALYZER_IK_MAX_WORD = "ik_max_word";
}
