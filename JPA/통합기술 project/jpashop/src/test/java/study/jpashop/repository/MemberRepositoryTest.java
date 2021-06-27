package study.jpashop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.jpashop.domin.Address;
import study.jpashop.domin.Member;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired MemberRepository memberRepository;

    @Test
    @Commit
    public void findOne() {

        Address address = new Address();
        address.setZipcode("0002");
        address.setCity("경기도 가평군 청평면");
        address.setStreet("102번지");

        Member saveMember = Member.builder()
                .name("kimjoo")
                .address(address)
                .build();

        memberRepository.save(saveMember);

        Optional<Member> findByMember = memberRepository.findById(saveMember.getId());

        findByMember.get().changeName("Kimjjjj");


        assertThat(findByMember.get()).isEqualTo(saveMember);
        assertThat(findByMember.get().getId()).isEqualTo(saveMember.getId());

    }

}