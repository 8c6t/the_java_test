package com.hachicore.thejavatest.study;

import com.hachicore.thejavatest.domain.Member;
import com.hachicore.thejavatest.domain.Study;
import com.hachicore.thejavatest.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
class StudyServiceTest {

    @Mock MemberService memberService;
    @Autowired StudyRepository studyRepository;

    @Container
    static GenericContainer postgreSQLContainer = new GenericContainer("postgres")
            .withExposedPorts(5432)
            .withEnv("POSTGRES_DB", "studytest");

    @BeforeAll
    static void beforeAll() {
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
        postgreSQLContainer.followOutput(logConsumer);
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("==========");
        System.out.println(postgreSQLContainer.getMappedPort(5432));
//        System.out.println(postgreSQLContainer.getLogs());
        studyRepository.deleteAll();
    }

//    @BeforeAll
//    static void beforeAll() {
//        postgreSQLContainer.start();
//        // System.out.println(postgreSQLContainer.getJdbcUrl());
//    }
//
//    @AfterAll
//    static void afterAll() {
//        postgreSQLContainer.stop();
//    }

    @Test
    void createNewStudy() {
        // given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        String email = "hachicore@gmail.com";

        Member member = new Member();
        member.setId(1L);
        member.setEmail(email);

        Study study = new Study(10, "테스트");

        given(memberService.findById(1L)).willReturn(Optional.of(member));

        // when
        studyService.createNewStudy(1L, study);

        // then
        assertEquals(1L, study.getOwnerId());
        then(memberService).should(times(1)).notify(study);
        then(memberService).shouldHaveNoMoreInteractions();
    }

    @Test
    void openStudy() {
        // given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");
        assertNull(study.getOpenedDateTime());

        // when
        studyService.openStudy(study);

        // then
        assertEquals(StudyStatus.OPENED, study.getStatus());
        assertNotNull(study.getOpenedDateTime());
        then(memberService).should().notify(study);
    }

}
