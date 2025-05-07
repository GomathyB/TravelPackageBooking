package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PaymentIdNotFoundException;
import com.cts.model.Payment;
import com.cts.service.PaymentService;
@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	PaymentService service;
	@PostMapping("/addPayment")
	public String addPayment(@RequestBody Payment payment) throws BookingIdNotFoundException
	{
		return service.addPayment(payment);
	}
	@GetMapping("/viewPayemntById/{pid}")
	public Payment viewPaymentById(@PathVariable("pid")int paymentId) throws PaymentIdNotFoundException
	{
		return service.viewPaymentById(paymentId);
	}
	@GetMapping("/viewAllPayment")
	public List<Payment> viewAllPayment()
	{
		return service.viewAllPayment();
	}
}
