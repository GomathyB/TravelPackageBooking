package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRoles {
	private int userId;
	private String name;
	private String email;
	private String password;
	private String role; 
}
