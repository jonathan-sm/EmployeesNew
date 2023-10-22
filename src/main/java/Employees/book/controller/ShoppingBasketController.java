package Employees.book.controller;

import Employees.book.service.ShoppingBasketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store/order")
public class ShoppingBasketController {
    private final ShoppingBasketService shoppingBasketService;

    public ShoppingBasketController(ShoppingBasketService shoppingBasketService) {
        this.shoppingBasketService = shoppingBasketService;
    }

    @GetMapping("/add")
    public List<Integer> add(@RequestParam List<Integer> ids) {
        return shoppingBasketService.add(ids);
    }

    @GetMapping("/get")
    public Map<Integer, Integer> get() {
        return shoppingBasketService.get();
    }
}