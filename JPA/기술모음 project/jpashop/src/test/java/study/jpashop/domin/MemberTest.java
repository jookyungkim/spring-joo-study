package study.jpashop.domin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    // 등록
    @Autowired
    EntityManager em;

    @Test
    @Commit
    public void createdMember() {

        /*Address address = Address.builder()
                .city("경기도 가평군 상면")
                .street("행현리 305번지")
                .zipcode("0001")
                .build();*/

        Address address = new Address();
        address.setCity("경기도 가평군 상면");
        address.setStreet("행현리 305번지");
        address.setZipcode("0001");

        Member member = Member.builder()
                .name("king")
                .address(address)
                .build();

        em.persist(member);

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());

    }

}