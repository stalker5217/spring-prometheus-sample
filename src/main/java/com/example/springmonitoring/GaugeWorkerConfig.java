package com.example.springmonitoring;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import io.micrometer.core.instrument.Metrics;

@Configuration
public class GaugeWorkerConfig {
	@Bean
	public Executor executorForGauge() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setDaemon(true);
		executor.setThreadNamePrefix("gauge");
		executor.initialize();

		Metrics.gauge(
			"thread_gauge",
			executor,
			ThreadPoolTaskExecutor::getActiveCount
		);

		return executor;
	}
}
