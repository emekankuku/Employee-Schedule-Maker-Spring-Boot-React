package com.example.registration.Controller;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.registration.Service.UserService;
import com.example.registration.dto.TaskDto;
import com.example.registration.model.Task;
import com.example.registration.model.User;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/task")
public class MainController {

    private UserService service;

    public MainController(UserService service) {
        this.service = service;
    }

    @PostMapping("/addTask")
    public Task addTask(@Valid @RequestBody TaskDto dto){
        LocalDateTime dateTime = LocalDateTime.parse(dto.getDate()+"T"+dto.getTime());
        Task task = new Task(dto.getName(), dateTime);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = service.getCurrentUser(auth.getName());
        task.setUser(user);
        return service.saveTask(task);
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // @GetMapping("/")
    // public String showHome(){
    //     return "home";
    // }

    // @GetMapping("/addTask")
    // public String addTask(Model model){
    //     model.addAttribute("task", new Task());
    //     return "addTask";
    // }

    // @PostMapping("/")
    // public String registerTask(@ModelAttribute("task") Task task){
    //     //task.setUser(service.getCurrentUser());
    //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //     User user = service.getCurrentUser(auth.getName());
    //     task.setUser(user);
    //     service.saveTask(task);
    //     return "redirect:/";
    // }

    // @GetMapping("/showTasks")
    // public String getTasks(Model model){
    //     model.addAttribute("tasks", service.getCurrentTasks());
    //     return "showTasks";
    // }

    // @GetMapping("/deleteTask")
    // public String deleteTask(@RequestParam long taskId){
    //     service.deleteTask(taskId);
    //     return "redirect:/showTasks";
    // }    
}
