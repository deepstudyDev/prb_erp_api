package com.prb.erp.schedual;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
 
@Component
public class CronTable {
  Logger logger = LoggerFactory.getLogger(this.getClass());
  private AtomicInteger loopCounter = new AtomicInteger();
   

  @Autowired
  private StopWatch watch;
   
  @PostConstruct
  public void init() {
    watch.start();
  }
 
  @Scheduled(cron="*/10 * * * * *") 
  public void tick() throws InterruptedException{
    /*watch.stop();
    String taskName = "task-";
    taskName = taskName + String.valueOf(loopCounter.getAndIncrement());
   
    workOrderDetailService.updateRunOrderCount();
    watch.start(taskName);*/
  }
 
  @Bean
  public StopWatch watch() {
    return new StopWatch();
  }
}