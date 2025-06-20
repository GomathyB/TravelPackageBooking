package com.cts.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.dto.Payment;
import com.cts.exception.PaymentIdNotFoundException;

@FeignClient(name = "PAYMENT", path = "/payment")
public interface PaymentClient {
	@PutMapping("/updatePayment")
	public String updatePayment(@RequestBody Payment payment) ;
	@GetMapping("/viewPayemntById/{pid}")
	public Payment viewPaymentById(@PathVariable("pid") int paymentId) throws PaymentIdNotFoundException;
	
}
