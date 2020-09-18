package br.com.fiap.EpicTask.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity(name = "tasks")
public class Task extends AbstractEntity{
	
	@NotBlank(message = "task.title.empty")
	private String title;
	
	@NotBlank(message = "task.descripition.empty")
	private String description;
	
	@Size(max = 3, message = "task.point.validate")
	private int point;
	
	@Size(min = 1, max = 3, message = "task.status.validate")
	private int status;

	@Override
	public String toString() {
		return "Task [title=" + title + ", description=" + description + ", point=" + point + ", status=" + status
				+ "]";
	}
}
