package dev.lochness.todo.handlers;

import dev.lochness.todo.dto.UserDto;
import dev.lochness.todo.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandlerImpl implements UserHandler {

    private final UserRepository userRepository;

    public UserHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<ServerResponse> list() {
        return userRepository.findAll()
                .map(UserDto::from)
                .collectList()
                .flatMap(users -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(users));

    }

    @Override
    public Mono<ServerResponse> details(String userId) {
        return userRepository.findById(userId)
                .map(UserDto::from)
                .flatMap(user -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user));
    }
}
