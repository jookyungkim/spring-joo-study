package study.jpashop.repository;

import study.jpashop.domin.Order;
import study.jpashop.dto.OrderSearch;

import java.util.List;

public interface OrderRepositoryCutom {

    public List<Order> findAllByString(OrderSearch orderSearch);
}
