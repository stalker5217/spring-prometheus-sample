package com.example.springmonitoring;

import java.util.concurrent.Executor;
import java.util.random.RandomGenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GaugeController {
	private final Executor executorForGauge;

	@GetMapping("/gauge")
	public String gauge() {
		for (int i = 0 ; i < 10 ; i++) {
			executorForGauge.execute(() -> {
				try {
					log.info("sleep!");
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			});
		}

		return "hello prometheus!";
	}
}
