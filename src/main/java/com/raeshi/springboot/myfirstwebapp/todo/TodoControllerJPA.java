package com.raeshi.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {
	private TodoRepository todoRepository;
	
	public TodoControllerJPA(TodoRepository todoRepository) {
		super();
		this.todoRepository=todoRepository;
	}

	@RequestMapping("list-todos")
	public String listAllTodos (ModelMap model) {
//		List<Todo> todos = todoService.findByUsername(getLoggedInUsername(model));
		List<Todo> todos = todoRepository.findByUsername(getLoggedInUsername(model));
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}


	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage (ModelMap model) {
		Todo todo = new Todo(0, getLoggedInUsername(model), "", LocalDate.now(), false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodoPage (ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors())
			return "todo";
		
		String username = getLoggedInUsername(model);
		todo.setUserName(username);
		//todoRepository.save method could only take entity as parameter
		todoRepository.save(todo);
//		todoService.addTodo(getLoggedInUsername(model), todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo (@RequestParam int id) {
//		todoService.deleteById(id);
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showUpdateTodoPage (@RequestParam int id, ModelMap model) {
//		Todo todo =todoService.findById(id);
		Todo todo =todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodoPage (ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors())
			return "todo";

		String username = getLoggedInUsername(model);
		todo.setUserName(username);
		//todoRepository.save method could only take entity as parameter
		todoRepository.save(todo);
//		todo.setUserName((String)model.getAttribute("name"));
//		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}