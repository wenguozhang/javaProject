package com.wgz.task;
 
import org.springframework.scheduling.support.CronSequenceGenerator;
 
import java.util.Date;

/**
 * @Description:根据corn表达式查询下一次执行时间
 * @author: wenguozhang 
 * @date:   2019年5月15日 下午4:09:03  
 */
public class CronSequenceGeneratorTest {
 
    public static void main(String[] args) {
 
        String cron = "0 2 2 * * ?"; //每个五分钟执行一次
 
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
 
        Date currentTime = new Date();
 
        System.out.println("currentTime: " + currentTime);
 
        Date nextTimePoint = cronSequenceGenerator.next(currentTime); // currentTime为计算下次时间点的开始时间
        System.out.println("nextTimePoint: " + nextTimePoint);
 
        Date nextNextTimePoint = cronSequenceGenerator.next(nextTimePoint);
 
        System.out.println("nextNextTimePoint: " + nextNextTimePoint);
    }
}