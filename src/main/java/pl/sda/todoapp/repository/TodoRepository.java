package pl.sda.todoapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.todoapp.entity.TodoEntity;

import java.util.List;

public interface TodoRepository extends CrudRepository<TodoEntity,Long> {

    List<TodoEntity> findAllByCompleted(boolean completed);
}
