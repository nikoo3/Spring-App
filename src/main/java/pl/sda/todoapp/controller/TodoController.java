package pl.sda.todoapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.todoapp.model.CreateTodoDto;
import pl.sda.todoapp.model.TodoDto;
import pl.sda.todoapp.service.TodoService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String todos(Model model) {

        List<TodoDto> active = todoService.findAllActive();
        List<TodoDto> completed = todoService.findAllCompleted();

        model.addAttribute("active", active);
        model.addAttribute("completed", completed);

        return "todos";
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public String todo(@PathVariable("id") Long id, Model model) {

        try {
            TodoDto dto = todoService.getById(id);
            model.addAttribute("todo", dto);
        } catch (EntityNotFoundException exception) {
            // TODO: handle exception
            return "redirect:/todo";
        }

        return "todoitem";
    }

    @ResponseBody
    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> completeTodo(@PathVariable("id") Long id) {

        try {
            todoService.complete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/todo/add", method = RequestMethod.GET)
    public String createTodo(Model model) {

        model.addAttribute("todo", new CreateTodoDto());

        return "createtodo";
    }

    @RequestMapping(value = "/todo/add", method = RequestMethod.POST)
    public String createTodo(@ModelAttribute("todo") @Valid CreateTodoDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "createtodo";
        }

        todoService.create(dto);

        return "redirect:/todo";
    }
}