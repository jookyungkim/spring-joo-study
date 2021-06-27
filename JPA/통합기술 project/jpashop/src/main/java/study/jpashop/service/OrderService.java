package study.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpashop.domin.Delivery;
import study.jpashop.domin.Member;
import study.jpashop.domin.Order;
import study.jpashop.domin.OrderItem;
import study.jpashop.domin.item.Item;
import study.jpashop.dto.OrderSearch;
import study.jpashop.repository.ItemRepository;
import study.jpashop.repository.MemberRepository;
import study.jpashop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /*
        주문
    */
    @Transactional(readOnly = false)
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findById(itemId).get();

        // 배송정보생성
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .build();

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /*
        주문 취소
     */
    @Transactional(readOnly = false)
    public void cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.get().orderCancel();
    }

    /*
        주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
