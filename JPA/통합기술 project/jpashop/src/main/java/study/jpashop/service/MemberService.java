package study.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpashop.domin.Member;
import study.jpashop.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /*
        회원가입
     */
    @Transactional(readOnly = false)
    public Long save(Member member) {
        valiededataDuplcateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void valiededataDuplcateMember(Member member) {
        Long findMemberCount = memberRepository.findCountByName(member.getName());

        if(findMemberCount > 0)
        {
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /*
        전체 회원 조회

     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void update(Long id, String name) {
        Optional<Member> member = memberRepository.findById(id);
        member.get().changeName(name);

    }

}
