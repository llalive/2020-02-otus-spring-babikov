package dev.lochness.todo;

import dev.lochness.todo.domain.Task;
import dev.lochness.todo.domain.User;
import dev.lochness.todo.handlers.TaskHandler;
import dev.lochness.todo.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class AppRoutesConfiguration {

    private final UserHandler userHandler;
    private final TaskHandler taskHandler;

    public AppRoutesConfiguration(UserHandler userHandler, TaskHandler taskHandler) {
        this.userHandler = userHandler;
        this.taskHandler = taskHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> taskRoutes() {
        return route()
                .GET("/user/{userId}/task", accept(APPLICATION_JSON),
                        serverRequest -> taskHandler.list(serverRequest.pathVariable("userId"))
                                .collectList()
                                .flatMap(tasks -> ok().body(tasks, Task.class)))
                .GET("/user/{userId}/task/{taskId}", accept(APPLICATION_JSON),
                        serverRequest -> taskHandler.details(
                                serverRequest.pathVariable("userId"),
                                serverRequest.pathVariable("taskId"))
                                .flatMap(task -> ok().contentType(APPLICATION_JSON)
                                        .body(task, Task.class)
                                        .switchIfEmpty(notFound().build()))
                )
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return route()
                .GET("/user", accept(APPLICATION_JSON),
                        serverRequest -> userHandler.list()
                                .collectList()
                                .flatMap(users -> ok().body(users, User.class)))
                .GET("/user/{id}", accept(APPLICATION_JSON),
                        serverRequest -> userHandler.details(serverRequest.pathVariable("id"))
                                .flatMap(user -> ok().contentType(APPLICATION_JSON)
                                        .body(user, User.class)
                                        .switchIfEmpty(notFound().build()))
                )
                .build();
    }
}
