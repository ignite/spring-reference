package com.gmail.at.zhuikov.aleksandr.servlet.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmail.at.zhuikov.aleksandr.servlet.domain.Order;
import com.gmail.at.zhuikov.aleksandr.servlet.repository.OrderRepository;


@Controller
public class OrdersController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrdersController.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@ModelAttribute
	public List<Order> loadAllOrders() {
		return orderRepository.getAll();
	}
	
	@RequestMapping(value = "/orders", method = GET)
	public String list(List<Order> orders) {
		LOG.info("Loaded " + orders.size() + " orders");
		return "orders";
	}
}