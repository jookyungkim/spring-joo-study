package study.jpashop.domin.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.jpashop.domin.BaseEntity;
import study.jpashop.domin.CategoryItem;
import study.jpashop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
public class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    // 주문취소 재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 주문생성 재고 감소
    public void removeStock(int quantity) {
        int restStock = 0;
        restStock = this.stockQuantity - quantity;

        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }

    public Item(String name, int price, int stockQuantity, List<CategoryItem> categoryItems) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryItems = categoryItems;
    }

    public void itemCheangAll(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
