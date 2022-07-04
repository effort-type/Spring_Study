package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // 1. 선언
    private final MemberService memberService;

    // 2. 생성자 만들기
    // 3. Autowired 어노테이션 달아주기 => 그러면 연결된다.
    // @Autowired // 생성자에 Autowired 어노테이션이 붙어있으면 스프링이 스프링컨테이너에 등록되어있는 memberService를 등록시켜준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
