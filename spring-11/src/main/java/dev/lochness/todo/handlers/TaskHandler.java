package dev.lochness.todo.handlers;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface TaskHandler {
    Mono<ServerResponse> list(String userId);

    Mono<ServerResponse> details(String userId, String taskId);
}
