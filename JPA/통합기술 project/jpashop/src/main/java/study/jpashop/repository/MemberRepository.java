package study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.jpashop.domin.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    @Query(value = "select count(m) from Member m where m.name = :name")
    Long findCountByName(@Param("name") String name);
}
