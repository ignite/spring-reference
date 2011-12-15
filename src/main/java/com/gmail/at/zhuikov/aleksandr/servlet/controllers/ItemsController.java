package com.gmail.at.zhuikov.aleksandr.servlet.controllers;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gmail.at.zhuikov.aleksandr.servlet.domain.Item;
import com.gmail.at.zhuikov.aleksandr.servlet.domain.Order;

@Controller
@RequestMapping("/orders/{orderId}/items")
public class ItemsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemsController.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@ResponseStatus(value = NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public void objectNotFound() {
	}
	
	@ExceptionHandler(BindException.class)
	public ModelAndView itemHasErrors(BindException e) {
		return new ModelAndView("addItem", e.getModel());
	}
	
	@ModelAttribute
	public Item prepareItem(
			@PathVariable Long orderId,
			@RequestParam(defaultValue = "") String product,
			@RequestParam(defaultValue = "0") Double price) {
		
		return new Item(
				hibernateTemplate.load(Order.class, orderId), 
				product,
				price);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "add", method = GET)
	public String createForm() {
		return "addItem";
	}
	
	@Transactional
	@RequestMapping(method = POST)
	public String create(@Valid Item item) {
		LOG.info("Adding item " + item);
		hibernateTemplate.saveOrUpdate(item.getOrder());
		return "redirect:/orders/" + item.getOrder().getId();
	}
}
