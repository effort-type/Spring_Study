package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    // 오류가 나는 이유는 name 값때문이다. 이를 해결하기 위한 방법 중 하나로
    // @RequestParam(value = "name", required = false)하면 됨.
    // ctl + p 유용하게 사용되니까 기억해두기
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // 파라미터로 넘겨온 name을 넘긴다.
        model.addAttribute("name", name);

        return "hello-template";
    }
}
