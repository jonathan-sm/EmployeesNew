package Employees.book.service;

import java.util.List;
import java.util.Map;

public interface ShoppingBasketService {
        List<Integer> add(List<Integer> ids);
        Map<Integer, Integer> get();
}
