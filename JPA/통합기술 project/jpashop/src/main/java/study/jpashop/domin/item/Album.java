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
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;
    private String dtype;

    public void albumCheangAll(String artist, String etc) {
        this.artist = artist;
        this.etc = etc;
    }

    @Builder
    public Album(String name, int price, int stockQuantity, List<CategoryItem> categoryItems, String artist, String etc, String dtype) {
        super(name, price, stockQuantity, categoryItems);
        this.artist = artist;
        this.etc = etc;

        if(dtype == null) {
            dtype = "A";
        }

        this.dtype = dtype;
    }
}
