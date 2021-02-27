package pl.sda.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoDto> findAllCompleted(){

        List<TodoEntity> todoEntities = todoRepository.findAllByCompleted(false);

        List<TodoDto> todoDtos = TodoMapper.mapEntityLiustToDoList
    }
}
