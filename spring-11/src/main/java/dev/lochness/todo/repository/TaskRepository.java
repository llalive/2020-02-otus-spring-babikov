package dev.lochness.todo.repository;

import dev.lochness.todo.domain.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

    Flux<Task> findAllByUserId(String userId);

    Mono<Task> findByUserIdAndId(String userId, String taskId);

    Mono<Task> save(Task task);
}