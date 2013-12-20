package com.socklabs.poc.app;

import com.rabbitmq.client.ConnectionFactory;
import com.socklabs.poc.connection.AbstractConnectionConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * This configuration class extends the abstract connection configuration class
 * to create a connections as needed. Note that we are using our own property
 * calls through the environment object along with the protected connection
 * factory method from the class we are extending.
 */
public class RabbitmqConfig extends AbstractConnectionConfig {

	@Resource
	private Environment environment;

	@Bean(name = "app-rabbitmq-connection")
	public ConnectionFactory connectionFactory() {
		return rabbitMqConnectionFactory(
				environment.getRequiredProperty("other.host", String.class),
				environment.getRequiredProperty("other.port", Integer.class),
				environment.getRequiredProperty("other.user", String.class),
				environment.getRequiredProperty("other.pass", String.class));
	}

}
