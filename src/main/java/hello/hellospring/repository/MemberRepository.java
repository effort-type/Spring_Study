package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장 
    Optional<Member> findById(Long id); // id를 통해서 회원 찾기
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
