package com;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public class FirstTask extends DefaultTask {

    @Input
    String property = "NEW";

    @TaskAction
    public void printer() {

        System.out.println("Hello from gradle!");

        System.out.println(property);

        System.getProperties().setProperty("Bazalt", "ok");

        System.out.println(System.getProperties());
    }

    public String getProperty() {
        return property;
    }
}
