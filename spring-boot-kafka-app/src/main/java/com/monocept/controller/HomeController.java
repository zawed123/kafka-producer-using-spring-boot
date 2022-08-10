package com.monocept.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.model.User;

@RequestMapping("/api/v1/home")
@RestController
public class HomeController {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topic = "test-topic";

	List<String> message = new ArrayList<>();

	private User userFromTopic = null;

	public HomeController() {
		super();
		System.out.println("Home Controller Created");
	}

	@GetMapping(path = "/publishJson/{name}")
	public String publishMessage(@PathVariable String name) {
		User user = new User(101, name, "Delhi");
		this.template.send(topic, user);

		return "Data Publish";
	}

}
