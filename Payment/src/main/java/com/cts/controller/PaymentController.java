package com.cts.controller; // Defines the package for the Payment Controller

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PaymentIdNotFoundException;
import com.cts.model.Payment;
import com.cts.service.PaymentService;

import jakarta.validation.Valid;

@RestController // Marks this class as a REST controller
@RequestMapping("/payment") // Defines the base URL mapping for payment-related operations
public class PaymentController {

	@Autowired
	PaymentService service; // Injects PaymentService for handling payment operations

	@PostMapping("/addPayment") // Adds a new payment (POST request)
	public String addPayment(@Valid @RequestBody Payment payment) throws BookingIdNotFoundException {
		return service.addPayment(payment);
	}

	@GetMapping("/viewPayemntById/{pid}") // Retrieves payment details by ID (GET request) [Typo: "viewPaymentById"]
	public Payment viewPaymentById(@PathVariable("pid") int paymentId) throws PaymentIdNotFoundException {
		return service.viewPaymentById(paymentId);
	}

	@GetMapping("/viewAllPayment") // Retrieves all payment records (GET request)
	public List<Payment> viewAllPayment() {
		return service.viewAllPayment();
	}
	@PutMapping("/updatePayment")
	public String updatePayment(@RequestBody Payment payment) {
		System.out.println("InSide Payment controller.."+payment);
		return service.updatePayment(payment);
	}
}
