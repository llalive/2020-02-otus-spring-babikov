package dev.lochness.todo.handlers;

import dev.lochness.todo.dto.TaskDto;
import dev.lochness.todo.repository.TaskRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class TaskHandlerImpl implements TaskHandler {

    private final TaskRepository taskRepository;

    public TaskHandlerImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Mono<ServerResponse> list(String userId) {
        return taskRepository.findAllByUserId(userId)
                .map(TaskDto::from)
                .collectList()
                .flatMap(tasks -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(tasks));
    }

    @Override
    public Mono<ServerResponse> details(String userId, String taskId) {
        return taskRepository.findByUserIdAndId(userId, taskId)
                .map(TaskDto::from)
                .flatMap(task -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(task));
    }
}
