package com.example.order.model.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.order.model.member.Member;
import com.example.order.model.product.Product;

@NoArgsConstructor
@Data
public class Order {
    private Long order_id;
    private Member member;
    private Product product;
    private int count;
    private long order_price;
    private LocalDateTime order_date;

    public void calcOrderPrice() {
        this.order_price = product.getPrice() * count;
    }
}
