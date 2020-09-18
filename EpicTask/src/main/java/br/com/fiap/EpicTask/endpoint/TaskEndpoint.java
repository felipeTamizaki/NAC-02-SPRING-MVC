package br.com.fiap.EpicTask.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.EpicTask.repository.taskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskEndpoint {

	
	@Autowired 
	private taskRepository dao;
	
	@GetMapping
	public String tasks() {
		
		return "tasks";
	}
}
