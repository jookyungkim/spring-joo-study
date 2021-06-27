package study.jpashop.domin;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Category extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


    public void AddChildCategory(Category child) {
        this.child.add(child);
        child.setChangParent(this);
    }

    public Category(String name, Category parent, List<Category> child) {
        this.name = name;
        this.parent = parent;
        this.child = child;
    }

    private void setChangParent(Category parent) {
        this.parent = parent;
    }


}
