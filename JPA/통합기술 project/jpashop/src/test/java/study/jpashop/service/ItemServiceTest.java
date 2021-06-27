package study.jpashop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.jpashop.domin.item.Book;
import study.jpashop.domin.item.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired ItemService itemService;

    @Test
    @Commit
    @Transactional
    public void updateTest() throws Exception {

        Book item = (Book) itemService.findOne((long) 1).get();
        item.bookCheangAll("김김", "33","B");

    }

    @Test
    @Transactional
    @Commit
    public void saveTest() throws Exception {
        Book book = Book.builder()
                .author("한영한")
                .isbn("aa")
                .name("Jpa test")
                .price(50000)
                .stockQuantity(100)
                .build();

        Long save = itemService.save(book);

        System.out.println("save = " + save);
    }

}