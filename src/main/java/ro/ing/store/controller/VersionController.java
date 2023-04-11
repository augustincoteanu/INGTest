package ro.ing.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class VersionController {

	
	@RequestMapping(value = "/getVersion", method = RequestMethod.GET)
	public String getVersion() {
		return "Version 1.0";
	}
	
}
