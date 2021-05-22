package dev.lochness.todo;

import dev.lochness.todo.domain.Task;
import dev.lochness.todo.domain.User;
import dev.lochness.todo.repository.TaskRepository;
import dev.lochness.todo.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.LinkedList;
import java.util.List;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class QueueToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueToDoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(UserRepository userRepository, TaskRepository taskRepository) {

        List<Task> tasks = List.of(
                Task.builder()
                        .name("Buy milk")
                        .desc("Don't forget to buy milk until 05/11!")
                        .build(),
                Task.builder()
                        .name("Sell bike")
                        .desc("Should sell it before winter season. Need money.")
                        .build(),
                Task.builder()
                        .name("Buy DOGE")
                        .desc("Buy more DOGE! DOGE to the MOON!")
                        .build());

        taskRepository.saveAll(tasks).blockLast();

        User firstUser = User.builder()
                .username("Potemk1n")
                .email("vasiliy.potemkin@otus.ru")
                .build();

        firstUser.setTasks(new LinkedList<>(tasks.subList(0, 1)));

        User secondUser = User.builder()
                .username("doge_rise")
                .email("dogetothemoon@2021.com")
                .build();

        secondUser.addTask(tasks.get(2));

        return args -> userRepository.saveAll(List.of(firstUser, secondUser))
                .subscribe();
    }
}
