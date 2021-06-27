package study.jpashop.domin.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpashop.domin.CategoryItem;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("M")
public class Movie extends Item {

    private String airector;
    private String actor;
    private String dtype;

    public void movieCheangAll(String airector, String actor) {
        this.airector = airector;
        this.actor = actor;
    }

    @Builder
    public Movie(String name, int price, int stockQuantity, List<CategoryItem> categoryItems, String airector, String actor, String dtype) {
        super(name, price, stockQuantity, categoryItems);
        this.airector = airector;
        this.actor = actor;

        if(dtype == null) {
            dtype = "M";
        }

        this.dtype = dtype;
    }
}
