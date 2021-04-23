package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    // AppConfig 생성자 주입
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    private final MemberRepository memberRepository=new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy=new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원정보 조회하고
       int discountPrice=discountPolicy.discount(member,itemPrice); // 그 정보를 할인정책에다가 넘김

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
