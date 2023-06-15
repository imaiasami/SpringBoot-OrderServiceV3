package com.example.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.order.model.member.Member;
import com.example.order.model.order.Order;
import com.example.order.model.product.Product;
import com.example.order.repository.OrderRepository;
import com.example.order.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {
	private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    
    // 주문하기
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.saveOrder(order);
        // 상품 재고 수정
        order.getProduct().ajustStock(-order.getCount());
        productRepository.updateProduct(order.getProduct());
    }
    
    // 주문목록
    public List<Order> findOrdersByMemberId(String member_id) {
    	return orderRepository.findOrdersByMemberId(member_id);
    }
    
    // 주문상세정보
    public Order findOrderById(Long order_id) {
    	return orderRepository.findOrderById(order_id);
    }
    
    // 주문삭제
    @Transactional
    public void removeOrder(Order order) {
    	// 상품 찾기
    	Product findProduct = productRepository.findProductById(order.getProduct().getProduct_id());
    	
    	// 재고 수량 조정
    	findProduct.ajustStock(order.getCount());
    	
    	// 주문 내역 삭제
    	orderRepository.removeOrderById(order.getOrder_id());
    	
    	// 상품 재고 정보 수정
    	productRepository.updateProduct(findProduct);
    	
    }


    
    
}
