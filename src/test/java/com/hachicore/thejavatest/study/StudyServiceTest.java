package com.hachicore.thejavatest.study;

import com.hachicore.thejavatest.domain.Member;
import com.hachicore.thejavatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Test
    void createNewStudy(@Mock MemberService memberService,
                        @Mock StudyRepository studyRepository) {

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        String email = "hachicore@gmail.com";

        Member member = new Member();
        member.setId(1L);
        member.setEmail(email);

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty())
        ;

        // 1
        Optional<Member> byId = memberService.findById(1L);
        assertEquals(email, byId.get().getEmail());

        // 2
        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        // 3
        assertEquals(Optional.empty(), memberService.findById(3L));

        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

    }
}