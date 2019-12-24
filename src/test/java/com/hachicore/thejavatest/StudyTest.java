package com.hachicore.thejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    // @EnabledOnOs({ OS.WINDOWS, OS.MAC })
    // @EnabledOnJre({ JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11 })
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "local")
    void create_new_study() {
        String test_env = System.getenv("TEST_ENV");
        // System.out.println(test_env);
        // assumeTrue("LOCAL".equalsIgnoreCase(test_env));

/*
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isEqualTo(10);
        });

        assumingThat("hachicore".equalsIgnoreCase(test_env), () -> {
            System.out.println("hachicore");
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });
*/

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 다시 만들기")
    // @DisabledOnOs(OS.MAC)
    // @EnabledOnJre(JRE.OTHER)
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "hachicore")
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