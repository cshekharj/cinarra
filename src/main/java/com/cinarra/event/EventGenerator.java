package com.cinarra.event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * Cinarra EventGenerator 
 *
 */
public class EventGenerator 
{
	private static DateFormat formater = new SimpleDateFormat("dd.MM.yyyy hh:mm:sss");
	private static DateFormat fileNameformat = new SimpleDateFormat("dd-MM-yyyy");
	private static char separator = ';';
	
    public static void main( String[] args )
    {
    	Calendar today = Calendar.getInstance();
    	//create log files
    	for(int i=0;i<2;i++){
    		today.add(Calendar.DAY_OF_WEEK, -i);
        	createLog(today);
    	}
    }

	private static void createLog(Calendar cal) {
		try{
    		File file = new File("./event-"+fileNameformat.format(cal.getTime())+".log");
    		//if file doesn't exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
    		BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName(),true));
    		Date curDate = null;
    		
    		for(int i=0;i<10;i++){
    			curDate = new Date(System.currentTimeMillis());
    			curDate.setDate(cal.get(Calendar.DATE));
    			curDate.setMonth(cal.get(Calendar.MONTH));
    			writer.write(formater.format(curDate)+separator+randomNum(1, 10)+separator+new Random().nextInt(100)+"\n");
    			writer.flush();
    			Thread.sleep(randomNum(1, 3)*1000);
    		}
    		 writer.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
    
    /*
     * Generate random event type between 1 to 10
     */
    public static int randomNum(int min, int max) {
    	
    	return new Random().nextInt((max - min) + 1) + min;
    }
}
