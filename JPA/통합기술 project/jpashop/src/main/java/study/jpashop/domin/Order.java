package study.jpashop.domin;

import com.sun.xml.txw2.IllegalSignatureException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    // 연관관계 메소드
    private void setMember(Member member) {
        this.member = member;
    }

    private void addOrderItems(OrderItem orderItems) {
        this.orderItems.add(orderItems);
        orderItems.setOrder(this);
    }

    private void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    // 주문 생성
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {

        Order order = new Order();
        order.setMember(member);
        for(OrderItem orderItem : orderItems) {
           order.addOrderItems(orderItem);
        }

        order.setDelivery(delivery);
        order.changOrderDateAndStatus(LocalDateTime.now(), OrderStatus.ORDER);
        return order;
    }

    public void changOrderDateAndStatus(LocalDateTime orderDate, OrderStatus orderStatus) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    // 주문 취소
    public void orderCancel() {

        if(delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalSignatureException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        setOrderStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems) {
            orderItem.cencel();
        }

    }

    // 주문 총가격 조회
    public int totalPrice() {
        int totalPrcie = 0;

        for (OrderItem orderItem : orderItems) {
            totalPrcie += orderItem.getTotalPrice();
        }
        return totalPrcie;
    }

}
