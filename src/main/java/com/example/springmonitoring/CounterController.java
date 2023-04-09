package com.example.springmonitoring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CounterController {
	private final Counter requestCounter = Metrics.counter("request_count");

	@GetMapping("/counter")
	public String counter() {
		requestCounter.increment();

		return "hello prometheus!";
	}
}
