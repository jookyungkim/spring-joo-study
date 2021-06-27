package study.jpashop.repository;

import study.jpashop.domin.Member;
import study.jpashop.dto.MemberSerchCondition;
import study.jpashop.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSerchCondition condition);

}
