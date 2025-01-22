package com.example.todoapp.controller;

import com.example.todoapp.service.TodoService;
import com.example.todoapp.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TodoController {

    private final TodoService todoService;

    // コンストラクタでTodoServiceを注入
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // "/" へのGETリクエストを処理
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos()); // todoリストをビューに渡す
        return "index"; // templates/index.html を表示
    }

    // 新しいタスクを追加
    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.saveTodo(todo);
        return "redirect:/"; // タスク追加後、トップページにリダイレクト
    }

    // タスクを削除
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/"; // タスク削除後、トップページにリダイレクト
    }
}
