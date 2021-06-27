package study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpashop.domin.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
