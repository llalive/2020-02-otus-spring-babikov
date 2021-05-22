package dev.lochness.todo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Task {
    @Id
    private String id;
    private String name;
    private String desc;

    @DBRef
    private User user;
}
