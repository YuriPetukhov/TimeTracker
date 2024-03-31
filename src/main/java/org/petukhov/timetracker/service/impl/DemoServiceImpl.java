package org.petukhov.timetracker.service.impl;

import org.petukhov.timetracker.annotation.TrackAsyncTime;
import org.petukhov.timetracker.annotation.TrackTime;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl {
    @TrackTime
    public void demoMethodSync() {
        System.out.println("Executing demoMethodSync...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("DemoMethodSync executed successfully.");
    }
    @TrackAsyncTime
    @Async
    public void demoMethodAsync() {
        System.out.println("Executing demoMethodAsync...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("DemoMethodAsync executed successfully.");
    }
}