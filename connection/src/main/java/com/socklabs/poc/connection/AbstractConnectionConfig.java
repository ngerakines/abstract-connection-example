package com.socklabs.poc.connection;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * An abstract class that is more or less a dumb utility to create a rabbitmq
 * connection factory given some parameters. If there were more involved
 * configuration and potentially different environmental info, it would be
 * stored either as java code or as properties contained within this artifact.
 */
@Configuration
@PropertySource({
	"classpath:/com/socklabs/poc/connection/default.properties",
	"classpath:/com/socklabs/poc/connection/${appType}.properties" })
public abstract class AbstractConnectionConfig {

	/**
	 * If we want to provide default connections for specific environments, we
	 * can do so using profiles. The inner class will automatically be
	 * configured when the outer class is referenced if there is a profile that
	 * matches the @Profile annotation.
	 */
	@Configuration
	@Profile({ "development" })
	final class DevelopmentProfileConfig {

		@Resource
		private Environment environment;

		/*
		 * Because this is a configuration class, we can safely create named
		 * beans
		 * with it. If this class is extended twice, the beans are not recreated
		 * because they are singletons.
		 */
		@Bean(name = "defaultRabbitMqConnection")
		public ConnectionFactory defaultConnectionFactory() {
			return rabbitMqConnectionFactory(
					environment.getRequiredProperty("host"),
					environment.getRequiredProperty("port", Integer.class),
					environment.getRequiredProperty("user"),
					environment.getRequiredProperty("pass"));
		}

		/*
		 * An example bean that uses the default connection.
		 */
		@Bean
		public Consumer eventConsumer() throws IOException {
			return new DefaultConsumer(defaultConnectionFactory().newConnection().createChannel());
		}

	}

	protected ConnectionFactory rabbitMqConnectionFactory(
			final String host,
			final int port,
			final String user,
			final String pass) {
		final ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(user);
		connectionFactory.setPassword(pass);
		return connectionFactory;
	}

}
