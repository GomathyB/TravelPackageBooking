package com.example.Apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.example.Apigateway.util.JwtUtil;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;

	@Autowired
	private JwtUtil util;

	public static class Config {
	}

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			if (validator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					return handleUnauthorized(exchange.getResponse(), "Missing authorization header");
				}

				String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}

				try {
					String role = util.extractRolesFromToken(authHeader);
					String requestedPath = exchange.getRequest().getPath().toString();
					String method = exchange.getRequest().getMethod().name();

					if (!isAuthorized(role, requestedPath, method)) {
						return handleUnauthorized(exchange.getResponse(), "Unauthorized access");
					}

				} catch (Exception e) {
					return handleUnauthorized(exchange.getResponse(), "Invalid token");
				}
			}
			return chain.filter(exchange);
		};
	}

	private boolean isAuthorized(String role, String path, String method) {
		if ("ADMIN".equalsIgnoreCase(role)) {
			return ((path.startsWith("/userRoles")
					&& (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")))
					|| (method.equalsIgnoreCase("GET")
							&& (path.startsWith("/travelPackage") || path.startsWith("/booking")
									|| path.startsWith("/payment") || path.startsWith("/review"))));

		} else if ("TRAVEL_AGENT".equalsIgnoreCase(role)) {
			return ((path.startsWith("/travelPackage")
					&& (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("PUT")
							|| method.equalsIgnoreCase("DELETE") || method.equalsIgnoreCase("POST")))
					|| (path.startsWith("/booking")
							&& (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("PUT")))
					|| (path.startsWith("/payment") && method.equalsIgnoreCase("GET"))
					|| (path.startsWith("/review") && method.equalsIgnoreCase("GET")));
		}

		else if ("CUSTOMER".equalsIgnoreCase(role)) {
			return ((path.startsWith("/travelPackage") && method.equalsIgnoreCase("GET"))
					|| (path.startsWith("/booking")
							&& (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("POST")
									|| method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("DELETE")))
					|| (path.startsWith("/payment")
							&& (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")))
					|| (path.startsWith("/review")
							&& (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("POST"))));
		}

		return false;
	}

	private Mono<Void> handleUnauthorized(ServerHttpResponse response, String message) {
		response.setStatusCode(HttpStatus.FORBIDDEN);
		return response.setComplete();
	}
}
