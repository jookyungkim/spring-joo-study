package study.jpashop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.jpashop.domin.Address;
import study.jpashop.domin.Member;
import study.jpashop.repository.MemberRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberService memberService;

    @Test
    @Commit
    public void findByNameTest() {

        Address address = new Address();
        address.setStreet("102-5번지");
        address.setZipcode("0003");
        address.setCity("서울시 중랑구 상봉동 ");

        Member member = Member.builder()
                .name("jpakim2")
                .address(address)
                .build();

        Long memberId = memberService.save(member);

    }
}