package com.socklabs.poc.app;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.socklabs.poc.addon.AbstractAddonConfig;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * This addon config uses the named connection factory that we created to create
 * and expose the addon's beans.
 */
public class AddonConfig extends AbstractAddonConfig {

	@Resource(name = "app-rabbitmq-connection")
	private ConnectionFactory connectionFactory;

	@Bean(name = "app-consumer")
	public Consumer consumer() throws IOException {
		return consumer(connectionFactory);
	}

}
