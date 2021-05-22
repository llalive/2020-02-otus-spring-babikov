package dev.lochness.todo.handlers;

import dev.lochness.todo.domain.User;
import dev.lochness.todo.repository.UserRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserHandlerImpl implements UserHandler {

    private final UserRepository userRepository;

    public UserHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<User> list() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> details(String userId) {
        return userRepository.findById(userId);
    }
}
