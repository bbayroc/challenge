package com.example.notifications.examples;

public final class DemoRunner {

    private DemoRunner() {
    }

    public static void main(String[] args) {

        System.out.println();
        System.out.println("==================================");
        System.out.println(" Notifications Library Demo");
        System.out.println("==================================");
        System.out.println();

        NotificationExample.main(args);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();

        TemplateExample.main(args);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();

        EventExample.main(args);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();

        UnifiedExample.main(args);

    }

}