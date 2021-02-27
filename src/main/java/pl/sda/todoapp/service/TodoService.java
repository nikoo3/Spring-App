package pl.sda.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.model.CreateTodoDto;
import pl.sda.todoapplication.mapper.TodoMapper;
import pl.sda.todoapp.model.TodoDto;
import pl.sda.todoapp.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoDto> findAllCompleted(){

        List<TodoEntity> todoEntities = todoRepository.findAllByCompleted(true);

        List<TodoDto> todoDtos = TodoMapper.mapEntityListToDoList(todoEntities);

        return todoDtos;
    }


    public List<TodoDto> findAllActive(){

        List<TodoEntity> todoEntities = todoRepository.findAllByCompleted(false);

        List<TodoDto> todoDtos = TodoMapper.mapEntityListToDoList(todoEntities);

        return todoDtos;
    }

    public boolean create(CreateTodoDto todo){
        TodoEntity todoEntity = new TodoEntity(todo.getText());

        try{
            todoRepository.save(todoEntity);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
