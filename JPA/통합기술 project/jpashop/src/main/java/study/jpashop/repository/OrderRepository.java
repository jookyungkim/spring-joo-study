package study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpashop.domin.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCutom {
}
