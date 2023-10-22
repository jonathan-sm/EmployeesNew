package Employees.book.service;

import Employees.book.ShoppingBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketService {
    private final ShoppingBasket shoppingBasket;

    public ShoppingBasketServiceImpl(ShoppingBasket shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    @Override
    public List<Integer> add(List<Integer> ids) {
        return shoppingBasket.add(ids);
    }

    @Override
    public Map<Integer, Integer> get() {
        return shoppingBasket.get();
    }
}