package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;


    @Test
    void shouldGetTasks() throws Exception {
        //Given
        List<Task> mockTasks = new ArrayList<>();
        Task task1 = new Task();
        Task task2 = new Task();
        mockTasks.add(task1);
        mockTasks.add(task2);
        when(dbService.getAllTasks()).thenReturn(mockTasks);

        List<TaskDto> mockTaskDtos = new ArrayList<>();
        TaskDto dto1 = new TaskDto(1L, "title", "content");
        TaskDto dto2 = new TaskDto(2L, "title2", "content2");
        mockTaskDtos.add(dto1);
        mockTaskDtos.add(dto2);
        when(taskMapper.mapToTaskDtoList(mockTasks)).thenReturn(mockTaskDtos);

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(mockTaskDtos.size()));
    }

    @Test
    void shouldGetTask() throws Exception {
        //Given
        Long taskId = 1L;
        Task mockTask = new Task();
        when(dbService.getTask(taskId)).thenReturn(mockTask);

        TaskDto mockTaskDto = new TaskDto(1L, "title", "content");
        when(taskMapper.mapToTaskDto(mockTask)).thenReturn(mockTaskDto);

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/tasks/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(mockTaskDto.getId()));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given
        Long taskId = 1L;

        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/{taskId}", taskId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Then
        verify(dbService).deleteTask(taskId);

    }


    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.saveTask(task)).thenReturn(task);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(taskDto.getId()));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

}