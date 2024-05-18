package com.praveen.splitwise;

import com.praveen.splitwise.commands.CommandRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitWiseApplication implements CommandLineRunner {
    private final CommandRegistry commandRegistory;
    private final Scanner scanner;
    @Autowired
    public SplitWiseApplication(CommandRegistry commandRegistry){
        this.commandRegistory = commandRegistry;
        scanner = new Scanner(System.in);
    }
    public void run(String... args) throws Exception {
        while(true){
            System.out.println("Tell me, what do you want to do?");
            String input = scanner.nextLine();
            commandRegistory.execute(input);
        }

    }
    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }

}
