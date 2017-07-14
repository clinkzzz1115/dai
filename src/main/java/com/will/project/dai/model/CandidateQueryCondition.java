package com.will.project.dai.model;

import lombok.Builder;
import lombok.Data;

/**
 * created by jiayi.chen on 2017/7/5
 */
@Builder
@Data
public class CandidateQueryCondition {

	// 姓名
	private String name;

	// 电话
	private String mobile;
	// 电话
	private String email;

	// 行业
	private String industry;

	// 职能
	private String profession;

	// 城市
	private String city;

	// 性别
	private String company;

}
