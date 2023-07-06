package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private TaskMapper taskMapper;
     private DbService service;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

//    @GetMapping
//    public List<TaskDto> getTask() {
//        return new ArrayList<>();
//    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId){
        return new TaskDto(1L, "test title", "test_content");
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @DeleteMapping
    public void deleteTask(Long taskId){}

    @PutMapping()
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    public void createTask (TaskDto taskDto){}

}
