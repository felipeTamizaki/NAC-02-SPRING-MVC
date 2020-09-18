package br.com.fiap.EpicTask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", point=" + point + ", status="
				+ status + "]";
	}
}
