/*
 * 
 * Copyright (c) 2018 Solace Corp.
 * 
 */
package org.springframework.cloud.stream.app.randomgen.source;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

/**
 * A random number generator SCS source
 *
 * @author Solace Corp
 */
@EnableBinding(Source.class)
@EnableConfigurationProperties(RandomgenSourceProperties.class)
public class RandomgenSourceConfiguration {
	private final static Random _random = new Random();

	@Autowired
	private RandomgenSourceProperties properties;
	
	// Imperative (procedural) style
	@InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedRate = "1000"))
	public Double emitRandomNumber() {
		return _random.nextDouble() * properties.getRandomMultiplier();
	}
}


