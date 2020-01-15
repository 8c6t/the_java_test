package com.hachicore.thejavatest.study;

import com.hachicore.thejavatest.domain.Study;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class StudyRepositoryTest {

    @Autowired StudyRepository studyRepository;

    @Test
    void save() {
        studyRepository.deleteAll();
        Study study = new Study(10, "Java");
        studyRepository.save(study);
        List<Study> all = studyRepository.findAll();
        assertEquals(1, all.size());
    }
}