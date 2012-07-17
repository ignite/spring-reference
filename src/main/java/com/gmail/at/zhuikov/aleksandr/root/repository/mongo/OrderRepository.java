package com.gmail.at.zhuikov.aleksandr.root.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gmail.at.zhuikov.aleksandr.root.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String>{

}