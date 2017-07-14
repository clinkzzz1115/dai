package com.will.project.dai.controller;

import javax.validation.Valid;

import javax.validation.constraints.NotNull;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.will.project.dai.entity.Candidate;
import com.will.project.dai.entity.Sheet;
import com.will.project.dai.model.CandidateQueryCondition;
import com.will.project.dai.service.CandidateService;

/**
 * created by jiayi.chen on 2017/7/4
 */
@RestController
@Validated
@RequestMapping(value = "/candidate")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CandidateController extends BaseController {

	@Autowired
	private CandidateService candidateService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	ResponseEntity<String> ping() {
		return ResponseEntity.ok("hello,world");
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	ResponseEntity<String> create(
			@RequestBody @Valid @NotNull(message = "candidate不能为空") Candidate candidate) {
		candidate.setId(String.valueOf(System.currentTimeMillis()));
		return candidateService.save(candidate) ? ResponseEntity.ok("ok") : ResponseEntity
				.ok("fail");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	ResponseEntity<String> update(@RequestBody @Valid Candidate candidate) {
		String id = candidate.getId();
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity("id不能为空", HttpStatus.BAD_REQUEST);
		}
		return candidateService.save(candidate) ? ResponseEntity.ok("ok") : ResponseEntity
				.ok("fail");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	ResponseEntity<String> delete(@Valid @NotEmpty(message = "id不能为空") String id) {
		return candidateService.delete(id) ? ResponseEntity.ok("ok") : ResponseEntity.ok("fail");
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	ResponseEntity<Candidate> get(@Valid @NotEmpty(message = "id不能为空") String id) {
		return ResponseEntity.ok(candidateService.getById(id));
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	ResponseEntity<Sheet<Candidate>> query(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "industry", required = false) String industry,
			@RequestParam(value = "profession", required = false) String profession,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "company", required = false) String company,
			@RequestParam(name = "page_index") int pageIndex,
			@RequestParam(name = "page_size") int pageSize) {
		pageIndex = pageIndex > 0 ? pageIndex - 1 : pageIndex;
		Page<Candidate> result = candidateService.query(CandidateQueryCondition.builder()
				.name(name).mobile(mobile).industry(industry).profession(profession).email(email)
				.city(city).company(company).build(), new PageRequest(pageIndex, pageSize,
				new Sort(new Order(Direction.DESC, "id"))));
		return ResponseEntity.ok(Sheet.<Candidate> builder().rowCount(result.getTotalElements())
				.data(result.getContent()).build());
	}
}
