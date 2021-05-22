package dev.lochness.todo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Queue;

@Data
@Builder
@Document
public class User {
    @Id
    private String id;
    private String username;
    private String email;

    @DBRef
    private Queue<Task> tasks;
}