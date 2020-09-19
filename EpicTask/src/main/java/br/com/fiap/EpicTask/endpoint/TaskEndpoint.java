package br.com.fiap.EpicTask.endpoint;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.EpicTask.model.Task;
import br.com.fiap.EpicTask.repository.taskRepository;

@Controller
@RequestMapping("/task")
public class TaskEndpoint {

	@Autowired 
	private taskRepository dao;

	@GetMapping
	public ModelAndView tasks() {
		List<Task> tasks = dao.findAll();
		ModelAndView modelAndView = new ModelAndView("task");
		modelAndView.addObject("tasks", tasks);
		return modelAndView;
	}
	
	@PostMapping
	public String save(@Valid Task task, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			return "task_new";
		}
		dao.save(task);
		attribute.addFlashAttribute("message", "Post-it criado");
		return "redirect:task";
	}
	
	@RequestMapping("new")
	public String newTask(Task task) {
		return "task_new";
	}
	
	@GetMapping("{id}")
	public ModelAndView updateTaskForm(@PathVariable long id) {
		Optional<Task> task = dao.findById(id);
		ModelAndView modelAndView = new ModelAndView("task_edit");
		modelAndView.addObject("task", task);
		return modelAndView;
	}
	
	@PostMapping("update")
	public String updateTask(@Valid Task task, BindingResult result) {
		if(result.hasErrors()) {
			return "task_edit";
		}
		dao.save(task);
		return "redirect:/task";
	}
	
	@RequestMapping("delete/{id}")
	public String removeTaskSingle(@PathVariable long id, RedirectAttributes attribute) {
		dao.deleteById(id);
		attribute.addFlashAttribute("message", "Post-it removido");
		return "redirect:/task";
	}
	
}
