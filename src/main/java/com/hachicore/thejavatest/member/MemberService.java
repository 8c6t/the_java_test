package com.hachicore.thejavatest.member;

import com.hachicore.thejavatest.domain.Member;
import com.hachicore.thejavatest.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newStudy);

    void notify(Member member);

}
