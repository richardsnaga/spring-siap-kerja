package com.subrutin.catalog.service.impl;

import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.config.ApplicationProperties;
import com.subrutin.catalog.config.CloudProperties;
import com.subrutin.catalog.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{
	
	Logger log = LoggerFactory.getLogger(GreetingServiceImpl.class);
	
	private ApplicationProperties appProperties;
	
	private CloudProperties cloudProperties;
	
	public GreetingServiceImpl(ApplicationProperties appProperties, CloudProperties cloudProperties) {
		super();
		this.appProperties = appProperties;
		this.cloudProperties = cloudProperties;
	}

	@Override
	public String sayGrreeting() {
		log.trace("ini TRACEEE");
		log.debug("INI debuggg");
			
		log.info("this is log INFO");
		log.warn("INI LOG WARN");
		log.error("INI Error");
		System.out.println(cloudProperties.getApiKey());
		TimeZone timezone = TimeZone.getTimeZone(appProperties.getTimezone());
		return appProperties.getWelcomeText() + ", our timezone: " + timezone.getDisplayName() + 
				", our currency: " + appProperties.getCurrency();
	}
	
}
