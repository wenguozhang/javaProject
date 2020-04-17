package com.wgz.frame.utils;
 
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @Description:根据corn表达式查询下一次执行时间
 * @author: wenguozhang 
 * @date:   2019年5月15日 下午4:09:03  
 */
public class CronUtil {
	
	/**
	* 格式: 长日期格式,带时分秒
    */
    public final static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss"; 
    
    public static String getNextTimePoint(String cron){
    	CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME);
    	Date currentTime = Date.from(Instant.now());
        Date nextTimePoint = cronSequenceGenerator.next(currentTime); // currentTime为计算下次时间点的开始时间
		return sdf.format(nextTimePoint);
    }
    
    public static String getNextTimePoint(String cron, String fristTime) throws ParseException {
    	CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME);
    	Date currentTime = sdf.parse(fristTime);
        Date nextTimePoint = cronSequenceGenerator.next(currentTime); // currentTime为计算下次时间点的开始时间
		return sdf.format(nextTimePoint);
    }
    
    public static void main(String[] args) throws ParseException {
    	String cron = "0 0 */1 * * *"; 
    	try {
			System.out.println("nextTimePoint:" + getNextTimePoint(cron,"2018-09-09 13:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME);
//    	Date t1 = sdf.parse("2018-09-09 12:55:55");
//    	Date t2 = sdf.parse("2018-09-09 13:55:55");
//    	System.out.print(t2.getTime()-t1.getTime());
    }
    
    
    
}