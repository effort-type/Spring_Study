package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 이렇게 추가하면 스프링 데이터 jpa가 쿼리문을 생성한다. => 인터페이스 이름만으로도 만들어준다.
    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
