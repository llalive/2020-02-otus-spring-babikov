package dev.lochness.todo;

import dev.lochness.todo.domain.Task;
import dev.lochness.todo.domain.User;
import dev.lochness.todo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.LinkedList;
import java.util.Queue;

@EnableConfigurationProperties
@SpringBootApplication
public class QueueToDoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QueueToDoApplication.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        initTestData(userRepository);
    }

    private static void initTestData(UserRepository userRepository) {
        User firstUser = User.builder()
                .username("Potemk1n")
                .email("vasiliy.potemkin@otus.ru")
                .build();
        Queue<Task> tasks = new LinkedList<>();
        tasks.add(Task.builder()
                .name("Buy milk")
                .desc("Don't forget to buy milk until 05/11!")
                .build());
        tasks.add(Task.builder()
                .name("Sell bike")
                .desc("Should sell it before winter season. Need money.")
                .build());
        firstUser.setTasks(tasks);
        userRepository.save(firstUser);

        User secondUser = User.builder()
                .username("doge_rise")
                .email("dogetothemoon@2021.com")
                .build();

        tasks = new LinkedList<>();
        tasks.add(Task.builder()
                .name("Buy DOGE")
                .desc("Buy more DOGE! DOGE to the MOON!")
                .build());
        secondUser.setTasks(tasks);
    }
}
