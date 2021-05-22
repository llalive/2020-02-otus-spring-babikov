package dev.lochness.todo.dto;

import dev.lochness.todo.domain.Task;
import dev.lochness.todo.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {
    private String id;
    private String username;
    private String email;

    private List<Task> tasks;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .tasks(new ArrayList<>(user.getTasks()))
                .build();
    }
}
