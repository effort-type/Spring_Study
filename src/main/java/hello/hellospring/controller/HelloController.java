package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    @ResponseBody 어노테이션이 없을 경우에는 요청이 들어오면 내장 톰캣 서버가 controller에 요청하고
    controller가 viewResolver한테 던져준다. 나한테 알맞는 템플릿을 찾아서 보여주라.
    이 어노테이션이 있을 경우에는 http 응답에 그대로 데이터를 넘겨준다.
    데이터가 문자일 경우에는 그대로 넣어서 주지만 데이터가 객체일 경우에는 디폴트로 json형태로 준다.

    @ResponseBody 어노테이션이 붙어있을 경우에는 viewResolver가 아니라 HttpMessageConverter가 동작함
    단순 문자면 StringConverter가 객체면 JsonConverter가 동작
*/
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

    @GetMapping("hello-String")
    @ResponseBody // http에 body부분에 데이터를 직접 넣어주겠다(return데이터를)는 어노테이션
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 이전과는 다르게 객체를 반환, 출력되는 것은 json
    }


    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
