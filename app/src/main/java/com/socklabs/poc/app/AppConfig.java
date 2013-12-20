package com.socklabs.poc.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * This is the top-level configuration class that imports the rabbitmq and addon
 * configuration classes. Note that there is an order in which this classes are
 * imported.
 */
@Configuration
@Import({ RabbitmqConfig.class, AddonConfig.class })
public class AppConfig {}
