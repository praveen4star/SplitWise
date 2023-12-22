package com.praveen.splitwise;

import com.praveen.splitwise.commands.Command;
import com.praveen.splitwise.commands.CommandRegistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitWiseApplication implements CommandLineRunner {
    private  CommandRegistory commandRegistory;
    private Scanner scanner;
    @Autowired
    public SplitWiseApplication(CommandRegistory commandRegistory){
        this.commandRegistory = commandRegistory;
        scanner = new Scanner(System.in);
    }
    public void run(String... args) throws Exception {
        String input = scanner.nextLine();
        commandRegistory.execute(input);
    }
    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }

}
