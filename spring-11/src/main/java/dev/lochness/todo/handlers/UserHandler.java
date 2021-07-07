package dev.lochness.todo.handlers;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface UserHandler {
    Mono<ServerResponse> list();

    Mono<ServerResponse> details(String userId);
}
