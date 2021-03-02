package pl.sda.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.model.CreateTodoDto;
import pl.sda.todoapp.model.TodoDto;
import pl.sda.todoapp.repository.TodoRepository;
import pl.sda.todoapp.mapper.TodoMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoDto> findAllCompleted(){

        List<TodoEntity> todoEntities = todoRepository.findAllByCompleted(true);

        List<TodoDto> todoDtos = TodoMapper.mapEntityListToDoList(todoEntities);

        return todoDtos;
    }

    public TodoDto getById(Long id) {

        Optional<TodoEntity> entity = todoRepository.findById(id);
        if (entity.isPresent()) {
            return TodoMapper.mapeEntityToDto(entity.get());
        }
        throw new EntityNotFoundException();
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

    public TodoDto complete(Long id) {
        Optional<TodoEntity> entity = todoRepository.findById(id);
        if (entity.isPresent()) {
            TodoEntity todoEntity = entity.get();
            todoEntity.setCompleted(true);
            todoEntity.setCompletedDate(new Date());

            todoEntity = todoRepository.save(todoEntity);
            return TodoMapper.mapeEntityToDto(todoEntity);
        }

        throw new EntityNotFoundException();
    }

    public TodoDto update(TodoDto dto) {
        Optional<TodoEntity> entity = todoRepository.findById(dto.getId());
        if (entity.isPresent()) {
            TodoEntity todoEntity = entity.get();
            todoEntity.setText(dto.getText());

            todoEntity = todoRepository.save(todoEntity);
            return TodoMapper.mapeEntityToDto(todoEntity);
        }

        throw new EntityNotFoundException();
    }
}
