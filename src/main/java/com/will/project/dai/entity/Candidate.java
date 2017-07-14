package com.will.project.dai.entity;

import static com.will.project.dai.config.DaiConfig.ANALYZER_IK_MAX_WORD;
import static com.will.project.dai.config.DaiConfig.ES_CANDIDATE_INDEX_NAME;
import static com.will.project.dai.config.DaiConfig.ES_CANDIDATE_TYPE_NAME;
import static com.will.project.dai.config.DaiConfig.SEARCH_ANALYZER_IK_MAX_WORD;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jiayi.chen on 2017/1/1.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = ES_CANDIDATE_INDEX_NAME, type = ES_CANDIDATE_TYPE_NAME)
public class Candidate implements Serializable {

	@Id
	private String id;

	@Field(type = FieldType.Nested)
	@Valid
	private BaseInfo baseInfo;

	@Field(type = FieldType.Nested)
	@Valid
	private List<Experience> workExperiences;

	@Field(type = FieldType.Nested)
	@Valid
	private List<Education> educations;

	@Field(type = FieldType.Nested)
	@Valid
	private List<Project> projects;

	@Field(type = FieldType.Nested)
	@Valid
	private List<Note> notes;

	/**
	 * 备注
	 */
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Note {

		private String id;
		/**
		 * 日期
		 */
		@Field(type = FieldType.String)
		private String date;
		/**
		 * 备注
		 */
		@Field(type = FieldType.String)
		private String note;
	}

	/**
	 * 基本信息
	 */
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	public static class BaseInfo {

		/**
		 * 姓名
		 */
		@Field(index = FieldIndex.not_analyzed, type = FieldType.String)
		@NotEmpty(message = "名字不能为空")
		private String name;
		/**
		 * id
		 */
		@Field(type = FieldType.String)
		private String id;
		/**
		 * 邮件
		 */
		@Field(type = FieldType.String,index = FieldIndex.not_analyzed)
		private String email;
		/**
		 * 电话
		 */
		@Field(type = FieldType.String,index = FieldIndex.not_analyzed)
		private String mobile;
		/**
		 * 行业
		 */
		@Field(type = FieldType.String,index = FieldIndex.not_analyzed)
		private String industry;
		/**
		 * 职位
		 */
		@Field(type = FieldType.String,index = FieldIndex.not_analyzed)
		private String profession;
		/**
		 * 所在城市
		 */
		@Field(type = FieldType.String)
		private String city;
		/**
		 * 年薪
		 */
		@Field(type = FieldType.String)
		private String salary;
		/**
		 * 婚育
		 */
		@Field(type = FieldType.String)
		private String isMarried;
		/**
		 * 语言能力
		 */
		@Field(type = FieldType.String)
		private String languages;
		/**
		 * IT技能
		 */
		@Field(type = FieldType.String)
		private String itSkill;
		/**
		 * 其他技能
		 */
		@Field(type = FieldType.String)
		private String otherSkills;
		/**
		 * 附加信息
		 */
		@Field(type = FieldType.String)
		private String otherInfo;
		/**
		 * 性别(0:女,1:男)
		 */
		@Field(type = FieldType.String)
		private String gender;
		/**
		 * 生日
		 */
		@Field(type = FieldType.String)
		private String birthday;
		/**
		 * 地址
		 */
		@Field(type = FieldType.String)
		private String address;
		/**
		 * linkedin
		 */
		@Field(type = FieldType.String)
		private String linkedin;
		/**
		 * 工作年限
		 */
		@Field(type = FieldType.String)
		private String workYears;
	}

	/**
	 * 工作经历
	 */
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Experience {

		/**
		 * 公司
		 */
		@Field(index = FieldIndex.analyzed, type = FieldType.String, analyzer = ANALYZER_IK_MAX_WORD, searchAnalyzer = SEARCH_ANALYZER_IK_MAX_WORD)
		private String company;
		/**
		 * title
		 */
		@Field(type = FieldType.String)
		private String title;
		/**
		 * 入职时间
		 */
		@Field(type = FieldType.String)
		private String start;
		/**
		 * 离职时间
		 */
		@Field(type = FieldType.String)
		private String end;
		/**
		 * 工作描述
		 */
		@Field(type = FieldType.String)
		private String description;

	}

	/**
	 * 教育信息
	 */
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Education {

		/**
		 * 学校
		 */
		@Field(type = FieldType.String)
		private String school;
		/**
		 * 开始时间
		 */
		@Field(type = FieldType.String)
		private String start;
		/**
		 * 结束时间
		 */
		@Field(type = FieldType.String)
		private String end;
		/**
		 * degree
		 */
		@Field(type = FieldType.String)
		private String degree;
		/**
		 * 主修
		 */
		@Field(type = FieldType.String)
		private String major;
	}

	/**
	 * 项目
	 */
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Project {

		/**
		 * 开始时间
		 */
		@Field(type = FieldType.String)
		private String start;
		/**
		 * 结束时间
		 */
		@Field(type = FieldType.String)
		private String end;
		/**
		 * 项目名称
		 */
		@Field(type = FieldType.String)
		private String name;
		/**
		 * title
		 */
		@Field(type = FieldType.String)
		private String title;
		/**
		 * 项目描述
		 */
		@Field(type = FieldType.String)
		private String description;
	}

}
