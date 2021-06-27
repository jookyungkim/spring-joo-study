package study.jpashop.domin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpashop.domin.item.Item;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class OrderItem extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    private void setOrderPrcieAndCount(int orderPrice, int count) {
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItem createOrderItem(Item item, int OrderPrice, int count) {
        OrderItem orderItem = new OrderItem();

        orderItem.setItem(item);
        orderItem.setOrderPrcieAndCount(OrderPrice, count);

        item.removeStock(count);
        return orderItem;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // 주문취소 상품 재고원복
    public void cencel() {
        getItem().addStock(count);
    }

    // 주문 가격 조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
