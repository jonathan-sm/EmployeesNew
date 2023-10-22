package Employees.book;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
    @SessionScope
    public class ShoppingBasket {
        private final Map<Integer, Integer> itemsCountById = new HashMap<>();

        public List<Integer> add(List<Integer> ids) {
            for (Integer id : ids) {
                if (itemsCountById.containsKey(id)) {
                    itemsCountById.put(id, itemsCountById.get(id) + 1);
                } else {
                    itemsCountById.put(id, 1);
                }
            }

            return ids;
        }

        public Map<Integer, Integer> get() {
            return itemsCountById;
        }
    }

