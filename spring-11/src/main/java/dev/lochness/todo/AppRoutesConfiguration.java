package dev.lochness.todo;

import dev.lochness.todo.handlers.TaskHandler;
import dev.lochness.todo.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

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
                        serverRequest -> taskHandler.list(serverRequest.pathVariable("userId")))
                .GET("/user/{userId}/task/{taskId}", accept(APPLICATION_JSON),
                        serverRequest -> taskHandler.details(
                                serverRequest.pathVariable("userId"),
                                serverRequest.pathVariable("taskId"))
                )
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return route()
                .GET("/user", accept(APPLICATION_JSON),
                        serverRequest -> userHandler.list())
                .GET("/user/{id}", accept(APPLICATION_JSON),
                        serverRequest -> userHandler.details(serverRequest.pathVariable("id")))
                .build();
    }
}
