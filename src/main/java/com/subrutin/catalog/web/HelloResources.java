package com.subrutin.catalog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.HelloMessageResponseDTO;
import com.subrutin.catalog.service.GreetingService;

@RestController
public class HelloResources {
	
	Logger log = LoggerFactory.getLogger(HelloResources.class);
	
	private GreetingService greetingService;
	
	
	public HelloResources(GreetingService greetingService) {
		super();
		this.greetingService = greetingService;
	}



	@GetMapping("/hello")
	public ResponseEntity<HelloMessageResponseDTO> helloWorld() {
		log.trace("ini TRACEEE");
		log.debug("INI debuggg");
			
		log.info("this is log INFO");
		log.warn("INI LOG WARN");
		log.error("INI Error");
		HelloMessageResponseDTO dto = new HelloMessageResponseDTO();
		dto.setMessage(greetingService.sayGrreeting());
		return ResponseEntity.accepted().body(dto);
	}
}
