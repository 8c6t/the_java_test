package com.hachicore.thejavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        // all();
        // exception();
        // timeout();
        // timeoutPreemptively();

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isEqualTo(10);
    }

    private void timeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    private void timeoutPreemptively() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    private void all() {
        Study study = new Study(-10);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + "여야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다")
        );
    }

    private void exception() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Study(-10);
        });
        assertEquals("limit은 0보다 커야한다", exception.getMessage());
    }

    @Test
    @DisplayName("스터디 다시 만들기")
    void create_new_study_again() {
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}