package br.com.fiap.EpicTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.EpicTask.model.Task;

public interface taskRepository extends JpaRepository<Task, Long> {

}
