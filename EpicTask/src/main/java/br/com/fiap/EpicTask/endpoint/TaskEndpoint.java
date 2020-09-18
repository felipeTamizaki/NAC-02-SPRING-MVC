package br.com.fiap.EpicTask.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.EpicTask.model.Task;
import br.com.fiap.EpicTask.repository.taskRepository;

@RestController
@RequestMapping("/task")
public class TaskEndpoint {

	private final taskRepository dao;
	
	@Autowired 
	public TaskEndpoint(taskRepository dao) {
		this.dao = dao;
	}

	@GetMapping
	public ModelAndView tasks() {
		List<?> tasks = dao.findAll();
		ModelAndView modelAndView = new ModelAndView("tasks");
		modelAndView.addObject("tasks", tasks);
		return modelAndView;
	}
	
	@PostMapping
	public String save(BindingResult result, Task task) {
		if(result.hasErrors()) {
			return "tasks_new";
		}
		dao.save(task);
		return "redirect:task";
	}
	
}
