package com.example.springmonitoring;

import java.util.random.RandomGenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SummaryController {
	private static final DistributionSummary requestLatency = Metrics.summary("request_latency_1");

	private static final RandomGenerator randomGenerator = RandomGenerator.getDefault();

	@Timed(value = "request_latency_2")
	@GetMapping("/summary")
	public String summary() {
		Long startTime = System.currentTimeMillis();
		long sleep = randomGenerator.nextLong(3000);
		try {
			Thread.sleep(sleep);
		} catch (Exception e) {
			log.error("exception is occurred.", e);
		} finally {
			Long endTime = System.currentTimeMillis();
			requestLatency.record(endTime - startTime);
		}

		return "hello prometheus!";
	}
}
