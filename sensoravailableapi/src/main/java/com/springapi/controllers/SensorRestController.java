package com.springapi.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.lang.Math;   


@RestController
public class SensorRestController {
	
	@RequestMapping(value="/checker/{sensor_id}",method=RequestMethod.GET)
	public String checkAvailable(@PathVariable("sensor_id") int id) {
		double num = Math.random();
		System.out.println("requesttttttttt:"+id);
		if(num*100 >30)
			return "Available";
		else
			return "Not Available";
	}

}
