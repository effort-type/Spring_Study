package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;


@Aspect // AOP를 사용하기 전에 달아줘야 사용가능한 어노테이션
public class TimeTraceAop {

    //@Around("execution(* hello.hellospring..*(..))") // 문제 발생 : 순환 참조 문제로 인한 에러 발생, pdf에 필기한 것 참고하기
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed(); // 인라인으로 변경
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }

}