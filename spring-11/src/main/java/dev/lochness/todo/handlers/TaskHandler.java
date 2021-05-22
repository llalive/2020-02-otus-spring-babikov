package dev.lochness.todo.handlers;

import dev.lochness.todo.domain.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskHandler {
    Flux<Task> list(String userId);

    Mono<Task> details(String userId, String taskId);
}
