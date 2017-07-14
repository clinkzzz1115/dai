package com.will.project.dai.es;

import com.will.project.dai.entity.Candidate;
import com.will.project.dai.entity.Candidate.BaseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.will.project.dai.service.CandidateService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsServiceTests {

	@Autowired
	private CandidateService candidateService;

	@Test
	public void testSave() throws Exception {

		candidateService.save(Candidate.builder()
				.baseInfo(BaseInfo.builder().name("will").build()).build());
	}

}
