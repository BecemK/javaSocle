package fr.spark.socle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spark.socle.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
