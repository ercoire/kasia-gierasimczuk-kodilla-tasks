package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    void shouldGetAllTasks(){
        //Given
        List<Task> mockTasks = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(mockTasks);

        //When
        List<Task> actual = dbService.getAllTasks();

        //Then
        assertEquals(mockTasks, actual);

    }

    @Test
    public void shouldGetTask() throws TaskNotFoundException {
        //Given
        Long taskId = 1L;
        Task mockTask = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTask));

        //When
        Task result = dbService.getTask(taskId);

        //Then
        assertEquals(mockTask, result);
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task1 = new Task();
        Task task2 = new Task();
        when(taskRepository.save(task1)).thenReturn(task2);

        //When
        Task result = dbService.saveTask(task1);

        //Then
        assertEquals(task2, result);
    }

    @Test
    public void testDeleteTask() {
        //Given
        Long taskId = 1L;

        //When
        dbService.deleteTask(taskId);

        //Then
        verify(taskRepository).deleteById(taskId);
    }

}