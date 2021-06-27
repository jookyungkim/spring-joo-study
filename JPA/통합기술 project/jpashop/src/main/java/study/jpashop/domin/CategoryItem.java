package study.jpashop.domin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpashop.domin.item.Item;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CategoryItem extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public CategoryItem(Category category, Item item) {
        this.category = category;
        this.item = item;
    }
}
