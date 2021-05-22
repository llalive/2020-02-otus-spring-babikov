package dev.lochness.todo.handlers;

import dev.lochness.todo.domain.Task;
import dev.lochness.todo.repository.TaskRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TaskHandlerImpl implements TaskHandler {

    private final TaskRepository taskRepository;

    public TaskHandlerImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Flux<Task> list(String userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Mono<Task> details(String userId, String taskId) {
        return taskRepository.findByUserIdAndId(userId, taskId);
    }
}
