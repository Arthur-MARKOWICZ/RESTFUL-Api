package com.aprendendo.test.domain.Repository;

import com.aprendendo.test.domain.model.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
