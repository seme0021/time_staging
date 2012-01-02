package com.time.scrapers;

import com.thoughtworks.selenium.*;


import net.sourceforge.jwebunit.junit.WebTester;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Pattern;

import junit.framework.*;

@SuppressWarnings({ "deprecation", "unused" })
public class restaurant_detail extends SeleneseTestCase{

    public FileWriter log;
    public FileWriter err;
    public FileWriter fin;

    public void setUp()throws Exception {
        setUp("http://www.facebook.com", "*firefox");
        }
 
    private String BASE_WORK_FOLDER="./";
    public void testNew() throws Exception {
    Boolean first = true;
    
    String logName = "rest_detail_3_20111120.txt";
    log = new FileWriter(logName,false); 
    
    String file = "fb_places_santa_monica2.txt";
    BufferedReader br = new BufferedReader(new FileReader(file));  
    String line = null; 
 

    String[] workParams;
 
    long start, end;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    DateFormat durationFormat = new SimpleDateFormat("HH:mm:ss");
    durationFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    String summary;
    int count;

  
    start = System.currentTimeMillis();
    first = true;
    count = 0;
    int waitCount;
    String url;
    
    /*======================================================*/
    /*==========METADATA SEARCH=============================*/

    String like_xpath = "id('pagelet_nav_stats')/div[2]/div[1]/div";
    String place_xpath = "id('pagelet_header')/div/div[2]/div[2]/h1/span";
    String talk_xpath = "id('pagelet_nav_stats')/div[2]/div[2]/div";
    String here_xpath = "id('pagelet_nav_stats')/x:div[2]/x:div[3]/x:div";
    String about_xpath = "id('about_text_more')/span";
    String locale_xpath = "id('pagelet_header')/div/div[2]/div[2]/div/span/a[1]";
    String type_xpath = "id('pagelet_header')/div/div[2]/div[2]/div/span";
    String else1_xpath = "id('pagelet_pages_elsewhere')/div/ul/li[1]/div/a/@href";
    String else2_xpath = "id('pagelet_pages_elsewhere')/div/ul/li[2]/div/a/@href";
    String else3_xpath = "id('pagelet_pages_elsewhere')/div/ul/li[3]/div/a/@href";   
    String addr_xpath = "//*[contains(text(),'Address')]/../td[1]/div[1]/a[1]";
    String more_xpath = "id('about_text_less')/div/a";   
    String phone_xpath = "//*[contains(text(),'Phone')]/../td[1]/div[1]/a[1]";
    String web_xpath = "//*[contains(text(),'Website')]/../td[1]/div/a/@href";
    String time_xpath = "//*[contains(text(),'Hours')]/../td[1]";
 
    String id, inpt_name, place, like, talk, here, about, locale, type, else1, else2, else3,addr,phone,web,time, message, err_message = null;
    
    
    
    while ((line = br.readLine()) != null){ 
    	System.out.println(line);
    	workParams = line.split(",");
    	count++;
    	
        id = workParams[0];
        url = workParams[1]; 
        inpt_name = workParams[2];   

    try {
       Boolean popUp = false;
       selenium.open(url);
       hangOn(1000);
       selenium.setTimeout("30000");
       popUp = selenium.isAlertPresent();
       if (popUp){
          selenium.chooseOkOnNextConfirmation();
       }
       
       /*=============================================================*/
       /*==========GET RESTAURANT DETAIL==============================*/    
       Thread.sleep(5000);
       
       
 
          Thread.sleep(2000); 

        	   Thread.sleep(2000);
        	   place = getValueByXPath(place_xpath);
        	   like = getValueByXPath(like_xpath);
        	   talk = getValueByXPath(talk_xpath); 
        	   here = getValueByXPath(here_xpath);
        	   about = getValueByXPath(about_xpath);
        	   locale = getValueByXPath(locale_xpath);
        	   type = getValueByXPath(type_xpath);
        	   else1 = getAttributeByXPath(else1_xpath);
        	   else2 = getAttributeByXPath(else2_xpath);
        	   else3 = getAttributeByXPath(else3_xpath);
        	   addr = getValueByXPath(addr_xpath);
        	   phone = getValueByXPath(phone_xpath);
        	   web = getAttributeByXPath(web_xpath);
        	   time = getValueByXPath(time_xpath);
        	   
       
        	   String[] scrapeDataArray = {quote(id),quote(place),quote(like),quote(talk),quote(here),quote(about),quote(locale),quote(type),
        			                       quote(else1),quote(else2),quote(else3), quote(place),quote(addr),quote(phone),
        			                       quote(time),quote(web), quote(url)};
        	   message = join(scrapeDataArray, ",");
       
        	   System.out.println(message);
        	   log.append(message + "\n");
               log.flush();    
        	  
    }         
    
      catch (Exception e){
            String[] errNoteArray = {url, e.getMessage()};
            err_message = join(errNoteArray, ",");
               }
                     System.out.println(err_message);
                     //selenium.close();
  
           }
    }
    private String quote(String val) {
        return "\"" + val + "\"";
}
/*
private String findMoreWork() {
        //Get a list of work files...
        String workFileName = "";
        File dir = new File(BASE_WORK_FOLDER);
    FilenameFilter workFileFilter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
                return name.startsWith("1056");
        }
    };
    String[] workFiles = dir.list(workFileFilter);

    //Pick the next work file and "claim it" by changing the file's extension to ".go"
    int i = 0;
    while ((i < workFiles.length) && (workFileName.length() == 0)) {
                //try to take on this work...
                try {
                        File wf = new File(BASE_WORK_FOLDER + "/" + workFiles[0]);
                        String wfName = wf.getPath();
                        wfName = wfName.replace(".wrk", ".go");
                        wf.renameTo(new File(wfName));
                        //We successfully renamed the file.  It's our responsibility now!
                        workFileName = wfName;
                } catch (Exception e) {
                        //Maybe someone else picked up this work while I was "thinking about it"
                        //Be sure to loop back around and try to get another work file
                        i++;
                }
    }

        return workFileName;  //empty if no work was found or available
}
*/
    
    
private String join(String[] strings, String separator) {
    StringBuffer sb = new StringBuffer();
    for (int i=0; i < strings.length; i++) {
        if (i != 0) sb.append(separator);
            sb.append(strings[i]);
        }
        return sb.toString();
}
public String removeSpaces(String s) {
          StringTokenizer st = new StringTokenizer(s," ",false);
          String t="";
          while (st.hasMoreElements()) t += st.nextElement();
          return t;
        }
private int getNumberOfValues(int optMax, int begItr, String xPathBeg  ,String xPathEnd){
        String xp_str_exist = "xpath=" + xPathBeg + begItr + xPathEnd;
        boolean exists = selenium.isElementPresent(xp_str_exist);
        if (exists){
       int no_itr = begItr;
       boolean opt_present=true;
       while ((++no_itr <= optMax) && (opt_present)){
             String xp_str = "xpath=" + xPathBeg + no_itr + xPathEnd;
             opt_present = selenium.isElementPresent(xp_str);
       }

           return no_itr-2;
        }
        else {
                return 1;
        }
 }
private String getValueByXPath(String xPath){

        String txt = "xpath=" + xPath;
        Boolean exist = selenium.isElementPresent(txt);
        if (exist) {
           String val = selenium.getText(txt);
           val = val.replaceAll("\n", " ");
           val = val.replaceAll("\\b\\s{2,}\\b", " ");
           return val.trim();
        }
        else {
                return "";
        }

}
private String getAttributeByXPath(String xPath){

    String txt = "xpath=" + xPath;
    Boolean exist = selenium.isElementPresent(txt);
    if (exist) {
       String val = selenium.getAttribute(txt); 
       return val.trim();
    }
    else {
            return "";
    }

}

private void clickNext(String xPath){
	String txt = "xpath=" + xPath;
	Boolean exist = selenium.isElementPresent(txt);
	if (exist){
		selenium.click(txt);
	}
	else {
		try{
		Thread.sleep(5000);
		selenium.click(txt);
		}
		catch (InterruptedException e){
            e.printStackTrace();
           }
	}
	
	
}
public void waitForElement(final String waitingElement, String timeoutMessage) {
    new Wait() {
        public boolean until() {
            return selenium.isElementPresent(waitingElement);
        }
    }.wait(timeoutMessage);
}

     private static void hangOn(int msecs){
             try{
                     Thread.sleep(msecs);
             }
             catch (InterruptedException e){
                     e.printStackTrace();
             }
     }

     }
                            