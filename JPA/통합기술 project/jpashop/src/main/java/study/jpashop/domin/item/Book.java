package study.jpashop.domin.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.jpashop.domin.CategoryItem;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;
    private String dtype;

    public Book(String author, String isbn, String dtype) {
        this.author = author;
        this.isbn = isbn;
        this.dtype = dtype;
    }

    @Builder
    public Book(String name, int price, int stockQuantity, List<CategoryItem> categoryItems, String author, String isbn, String dtype) {
        super(name, price, stockQuantity, categoryItems);
        if(dtype == null) {
            dtype = "B";
        }

        this.author = author;
        this.isbn = isbn;
        this.dtype = dtype;
    }

    public void bookCheangAll(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }
}
