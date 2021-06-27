package study.jpashop.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;
import study.jpashop.domin.Order;
import study.jpashop.domin.OrderStatus;
import study.jpashop.domin.QMember;
import study.jpashop.domin.QOrder;
import study.jpashop.dto.OrderSearch;

import javax.persistence.EntityManager;
import java.util.List;

import static study.jpashop.domin.QMember.member;
import static study.jpashop.domin.QOrder.order;
import static org.springframework.util.StringUtils.hasText;

public class OrderRepositoryImpl implements OrderRepositoryCutom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Order> findAllByString(OrderSearch orderSearch) {
        return queryFactory
                .select(order)
                .from(order)
                .join(order.member, member)
                .where(nameLike(orderSearch.getMemberName()),
                        statusEq(orderSearch.getOrderStatus()))
                .fetch();
    }

    public BooleanExpression nameLike(String name) {
        return hasText(name) ? member.name.like("%"+name+"%") : null;
    }

    public BooleanExpression statusEq(OrderStatus orderStatus) {
        return orderStatus != null ? order.orderStatus.eq(orderStatus) : null;
    }
}
