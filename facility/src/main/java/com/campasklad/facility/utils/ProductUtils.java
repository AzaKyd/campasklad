package com.campasklad.facility.utils;

import com.campasklad.facility.dto.product.PostingProductDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductUtils {

    public static <T extends ProductAttributes> List<T> consolidateProducts(List<T> items) {
        Map<String, T> productMap = new HashMap<>();

        for (T item : items) {
            // Создаём строковый ключ из productId, sizeId, colorId
            String key = item.getProductId() + "-" + item.getSizeId() + "-" + item.getColorId();

            if (productMap.containsKey(key)) {
                // Если ключ уже существует, суммируем количество
                T existing = productMap.get(key);
                existing.setQuantity(existing.getQuantity() + item.getQuantity());
            } else {
                // Если ключа нет, добавляем новый объект
                productMap.put(key, item);
            }
        }

        // Преобразуем значения Map в список
        return new ArrayList<>(productMap.values());
    }
}
