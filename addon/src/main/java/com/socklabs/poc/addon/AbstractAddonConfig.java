package com.socklabs.poc.addon;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * This addon configuration class can also expose beans if it needs to, but is
 * primarily meant to provide protected/class-private methods for the class
 * extended it to create beans.
 */
@Configuration
public class AbstractAddonConfig {

	protected Consumer consumer(final ConnectionFactory connectionFactory) throws IOException {
		return new DefaultConsumer(connectionFactory.newConnection().createChannel());
	}

}
