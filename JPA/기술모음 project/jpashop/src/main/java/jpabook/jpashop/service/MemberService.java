package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //lombok  final MemberRepository memberRepository 생성자 접근제어할수 도와줌 
public class MemberService {

	private final MemberRepository memberRepository;
	
	/**
	 * 회원가입
	 */
	@Transactional(readOnly = false)
	public Long join(Member member) {
		valicdateDuplcateMember(member);
		memberRepository.save(member); //중복 회원 검증
		return member.getId();
	}

	private void valicdateDuplcateMember(Member member) {
	  List<Member> findMembers = memberRepository.findByName(member.getName());
	  if(!findMembers.isEmpty()) {
		  throw new IllegalStateException("이미 존재하는 회원입니다.");
	  }
		
	}
	
	/**
	 * 전체 회원 전체 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	/**
	 * 전체 회원 조회
	 */
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}

	@Transactional
	public void update(Long id, String name) {
		Member member = memberRepository.findOne(id);
		member.setName(name);
		
	}
}
