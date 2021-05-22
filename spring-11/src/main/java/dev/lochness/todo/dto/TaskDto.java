package dev.lochness.todo.dto;

import dev.lochness.todo.domain.Task;
import dev.lochness.todo.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {
    private String id;
    private String name;
    private String desc;

    private User user;

    public static TaskDto from(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .desc(task.getDesc())
                .user(task.getUser())
                .build();
    }
}
