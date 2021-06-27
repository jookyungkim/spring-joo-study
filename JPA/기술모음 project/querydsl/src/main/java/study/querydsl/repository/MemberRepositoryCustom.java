package study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.dto.MemberSerchCondition;
import study.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSerchCondition condition);
    Page<MemberTeamDto> searchPageSimple(MemberSerchCondition condition, Pageable pageable); // import org.springframework.data.domain.Pageable
    Page<MemberTeamDto> searchPageComplex(MemberSerchCondition condition, Pageable pageable);
}
