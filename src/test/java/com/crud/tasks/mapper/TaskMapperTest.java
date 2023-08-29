package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void shouldMapToTask() {
        //Given
        TaskDto dto = new TaskDto(1L, "test", "dummy");

        //When
        Task actual = taskMapper.mapToTask(dto);

        //Then
       assertEquals(dto.getId(), actual.getId());
       assertEquals(dto.getTitle(), actual.getTitle());
       assertEquals(dto.getContent(), actual.getContent());
    }

    @Test
    void shouldMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title", "content");

        //When
        TaskDto actual = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), actual.getId());
        assertEquals(task.getTitle(), actual.getTitle());
        assertEquals(task.getContent(), actual.getContent());
    }

    @Test
    void shouldMapToTaskDtoList() {
        //Given
        List<Task> list = new ArrayList<>();
        Task test1 = new Task(1L, "title1", "content1");
        Task test2 = new Task(2L, "title2", "content2");
        list.add(test1);
        list.add(test2);

        //When
        List<TaskDto> actual = taskMapper.mapToTaskDtoList(list);

        //Then
        assertEquals(2, actual.size());
        assertEquals(list.get(0).getId(), actual.get(0).getId());
        assertEquals(list.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(list.get(1).getTitle(), actual.get(1).getTitle());

    }
}