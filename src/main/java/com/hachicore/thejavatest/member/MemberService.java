package com.hachicore.thejavatest.member;

import com.hachicore.thejavatest.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

}
