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
        
        System.out.println("nextTimePoint:" + getNextTimePoint(cron));
    }
    
    public static Date getNextTimePoint(String cron) {
    	CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
    	 Date currentTime = new Date();
         Date nextTimePoint = cronSequenceGenerator.next(currentTime); // currentTime为计算下次时间点的开始时间
    	return nextTimePoint;
    }
}