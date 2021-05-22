package dev.lochness.todo.handlers;

import dev.lochness.todo.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserHandler {
    Flux<User> list();

    Mono<User> details(String userId);
}
