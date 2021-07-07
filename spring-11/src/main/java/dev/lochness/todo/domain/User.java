package dev.lochness.todo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@Document
public class User {
    @Id
    private String id;
    private String username;
    private String email;

    private List<Task> tasks;

    public void addTask(Task task) {
        if (tasks == null)
            tasks = new LinkedList<>();
        tasks.add(task);
    }
}