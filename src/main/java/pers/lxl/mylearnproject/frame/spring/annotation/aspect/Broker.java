package pers.lxl.mylearnproject.frame.spring.annotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component//切面的类仍然是一个 Bean ，需要 @Component 注解标注
@Aspect
//切面理解为一个拦截器
        //AspectJ 注解：
        //注解	说明
        //@Before	前置通知，在连接点方法前调用
        //@Around	环绕通知，它将覆盖原有方法，但是允许你通过反射调用原有方法
        //@After	后置通知，在连接点方法后调用
        //@AfterReturning	返回通知，在连接点方法执行并正常返回后调用，要求连接点方法在执行过程中没有发生异常
        //@AfterThrowing	异常通知，当连接点方法异常时调用
class Broker {
    //execution 的正则表达式
//execution：代表执行方法的时候会触发
//* ：代表任意返回类型的方法
//pojo.Landlord：代表类的全限定名
//service()：被拦截的方法名称
//如果多出都需要写这样的表达式难免会有些复杂
//可以通过使用 @Pointcut 注解来定义一个切点来避免这样的麻烦
    @Pointcut("execution(* pers.lxl.mylearnproject.frame.spring.annotation.pojo.Landlord.service())")
    public void lService() {
        System.out.println("Aha! Pointcut!");
    }

    @Before("execution(* pers.lxl.mylearnproject.frame.spring.annotation.pojo.Landlord.service())")
    public void before() {
        System.out.println("带租客看房");
        System.out.println("谈价格");
    }

    @After("execution(* pers.lxl.mylearnproject.frame.spring.annotation.pojo.Landlord.service())")
    public void after() {
        System.out.println("交钥匙");
    }
    //  使用 @Around 注解来同时完成前置和后置通知
    @Around("execution(* pers.lxl.mylearnproject.frame.spring.annotation.pojo.Landlord.service())")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("带租客看房");
        System.out.println("谈价格");

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("交钥匙");
    }
}

