package br.com.fiap.EpicTask.endpoint;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.EpicTask.model.Task;
import br.com.fiap.EpicTask.repository.taskRepository;

@RestController
@RequestMapping("/task")
public class TaskEndpoint {

	private final taskRepository dao;
	
	// private MessageSource messageSource;
	
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
	public String save(BindingResult result, Task task, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			return "task_new";
		}
		dao.save(task);
		// String message = messageSource.getMessage("task.add.seccess", 
		//		null,LocaleContextHolder.getLocale());
		attribute.addFlashAttribute("Adicionado", "Post-it criado");
		return "redirect:task";
	}
	
	@RequestMapping("new")
	public String newTask(Task task) {
		return "task_new";
	}
	
	@GetMapping("{id}")
	public ModelAndView updateTaskForm(@PathVariable long id) {
		Optional<Task> task = dao.findById(id);
		ModelAndView modelAndView = new ModelAndView("task");
		modelAndView.addObject("task", task);
		return modelAndView;
	}
	
	@PostMapping("update")
	public String updateTask(Task task, BindingResult result) {
		if(result.hasErrors()) {
			return "task_edit";
		}
		dao.save(task);
		return "redirect:/task";
	}
	
}
