package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    //private TaskMapper taskMapper;

    // private DbService dbService;
    @GetMapping
    public List<TaskDto> getTask() {
        return new ArrayList<>();
    }

    @GetMapping
    public TaskDto getTask(Long taskId){
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping
    public void deleteTask(Long taskId){}

    @PutMapping
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    public void createTask (TaskDto taskDto){}

}
