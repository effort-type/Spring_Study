package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // new를 사용하지 않기 위해서 변경한 코드 + 같은 레파지토리를 테스트하기 위함
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원이 있으면 안됨
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId(); // 임의로 id반환하도록 한 거임
    }

    private void validateDuplicateMember(Member member) {

        // 한번에 처리하는 방법
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        //        Optional<Member> result = memberRepository.findByName(member.getName());
        //
        //        // result가 null이 아니라 다른 값이 있으면 동작
        //        result.ifPresent(m -> {
        //            throw new IllegalStateException("이미 존재하는 회원입니다.");
        //        });

    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
