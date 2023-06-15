package com.example.order.model.order;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.order.model.member.Member;
import com.example.order.model.product.Product;

@Data
public class OrderProductForm {
	@NotNull
	private Long product_id;
	private String name;
	private int stock;
	private long price;
	@Min(1)
	@Max(999)
	private int count;

	public static Order toOrder(OrderProductForm orderProductForm, Member member, Product product) {
		Order order = new Order();
		order.setMember(member);
		order.setProduct(product);
		order.setCount(orderProductForm.getCount());
		order.calcOrderPrice();
		return order;
	}
}
