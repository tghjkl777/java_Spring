package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    MemberService memberService=new MemberServiceImpl(memberRepository);
//    OrderService orderService =new OrderServiceImpl();

    //AppConfig 사용
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        memberService= appConfig.memberService();
        orderService=appConfig.orderService();
    }

    @Test
    void createOrder (){
        Long memberId =1L;
        Member member= new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order=orderService.createOrder(memberId, "itmeA",10000);

        //vip 인 경우에 1000원 할인되는지 확인하는것
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
