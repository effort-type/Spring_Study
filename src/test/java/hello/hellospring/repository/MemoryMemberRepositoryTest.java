package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/*
    전체 Test를 돌리면 순서는 보장되지 않는다.
    제일 먼저 findAll이 Test로 실행됐을 경우에 findByname에서 오류가 발생
    이유는 repository에 spring1과 2가 이미 findAll에서 저장이 되었기 때문이다.

    해결 방법 : 테스트 끝날 때마다 데이터를 클리어 해주면 된다.
*/

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끝날 때마다 데이터를 클리어 해주기 위함
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        
        // Optional의 값을 가지고 올 때는 get()을 사용하면 되는데 좋은 방법은 아니래
        Member result = repository.findById(member.getId()).get();

        // 기대값, 실제값을 넣어서 테스트해볼 때 사용
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
