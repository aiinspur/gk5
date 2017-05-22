package com.tigerj.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TimerTaskTest {
	
	//@Scheduled(cron = "0/1 * * * * ?") // 每20秒执行一次
	//@Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduler() {
        System.out.println(Thread.currentThread().getName()+" in----");
        
        try {
			Thread.sleep(20*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        System.out.println(Thread.currentThread().getName()+" end----");
        
    }

}
