package study.jpashop.repository;

//import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import study.jpashop.dto.MemberSerchCondition;
import study.jpashop.dto.MemberTeamDto;

import javax.persistence.EntityManager;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamDto> search(MemberSerchCondition condition) {
        return null;
    }
}
