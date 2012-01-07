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
public class restaurants extends SeleneseTestCase{

    public FileWriter log;
    public FileWriter err;
    public FileWriter fin;

    public void setUp()throws Exception {
        setUp("http://www.facebook.com", "*firefox");
        }
 
    private String BASE_WORK_FOLDER="./";
    public void testNew() throws Exception {
    Boolean first = true;
    
    String logName = "out_restaurants_paloalto_20111029.txt";
    log = new FileWriter(logName,false); 

 

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
    String city = "Palo Alto";
    String state = "CA";
    String search_term = "Restaurant";    
    
    String place_xpath = "id('pagelet_nearby_results')/div/ul/li[1]/div/div[2]/div[1]/div/a";
    String url_xpath = "id('pagelet_nearby_results')/div/ul/li[1]/div/div[2]/div[1]/div/a/@href";
    String type_xpath = "id('pagelet_nearby_results')/div/ul/li[1]/div/div[2]/div[2]/div/div[1]";
    String about_xpath = "id('pagelet_nearby_results')/div/ul/li[1]/div/div[2]/div[2]/div/div[2]";
    
    String pg_xpath = "//*[@id='u1tvl0_12']/../../../../../../div[2]/div[1]/div[1]/label[2]/input[1]";
    String pg_stat_xpath1 = "id('pagelet_nav_stats')/div[2]/div[1]/div";
    String pg_stat_xpath2 = "id('pagelet_nav_stats')/div[2]/div[2]/div";
    String place, type, about,place_url,page_stat1,page_stat2, message, err_message;
    
    url = "/pages/Palo-Alto-California/104022926303756";

    try {
       Boolean popUp = false;
       selenium.open(url);
       hangOn(1000);
       selenium.setTimeout("2000");
       popUp = selenium.isAlertPresent();
       if (popUp){
          selenium.chooseOkOnNextConfirmation();
       }
       /*=============================================================*/
       /*======SIGN ON TO FACEBOOK====================================*/
       
       
       selenium.type("email","");
       selenium.type("pass", "");
       selenium.click("xpath=//*[@value='Log In']");
       Thread.sleep(2000);
       selenium.waitForPageToLoad("30000");
       /*=============================================================*/
       /*==========LOOP THROUGH PLACES================================*/    
       String search_id = selenium.getAttribute("xpath=//*[contains(@title,'Enter a category')]/@id");
       System.out.println("AutoComplete ID = " + search_id);
       
       selenium.click(search_id);
       selenium.type(search_id, "Restauran"); 
       //selenium.waitForPageToLoad("30000");
       Thread.sleep(5000);
       selenium.typeKeys(search_id, "t"); 
       //selenium.waitForPageToLoad("30000");
       Thread.sleep(10000);
       selenium.keyPress(search_id,"13");  
       //selenium.waitForPageToLoad("30000");
       Thread.sleep(5000);
       
       
       /*=============================================================*/
       /*==========LOOP THROUGH PAGES (AJAX)==========================*/
       int j = 1;
       int p = 20;
       
       while (j<=p){
          int i = 1;
          int n = 10;
          Thread.sleep(2000);
          while (i<=n){

        	   Thread.sleep(2000);
        	   place = getValueByXPath("id('pagelet_nearby_results')/div/ul/li[" + i + "]/div/div[2]/div[1]/div/a");
        	   type = getValueByXPath("id('pagelet_nearby_results')/div/ul/li[" + i + "]/div/div[2]/div[2]/div/div[1]");
        	   about = getValueByXPath("id('pagelet_nearby_results')/div/ul/li[" + i + "]/div/div[2]/div[2]/div/div[2]");
        	   place_url = getAttributeByXPath("id('pagelet_nearby_results')/div/ul/li[" + i + "]/div/div[2]/div[1]/div/a/@href");
        	   page_stat1 = getValueByXPath(pg_stat_xpath1);
        	   page_stat2 = getValueByXPath(pg_stat_xpath2);
       
        	   String[] scrapeDataArray = {quote(city),quote(state),quote(search_term),quote(place), quote(type), quote(about),quote(place_url),quote(page_stat1),quote(page_stat2),quote(url)};
        	   message = join(scrapeDataArray, ",");
       
        	   System.out.println(message);
        	   log.append(message + "\n");
               log.flush();
               
        	   i=i+1;
          }
          /*===========GET ID IF CHANGED==========================*/
          //search_id = selenium.getAttribute("xpath=id('pagelet_main_column_public')/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input/@id"); 
          //selenium.click("xpath=id('" + search_id + "')/../../div[2]/div[1]/div[1]/label[2]/input[1]");
          selenium.click("xpath=//*[@id='" + search_id + "']/../../../../../../div[2]/div[1]/div[1]/label[2]/input[1]");
          j=j+1; 
       }
                            
    }                                   
      catch (Exception e){
            String[] errNoteArray = {url, e.getMessage()};
            err_message = join(errNoteArray, ",");
               }

 

                     selenium.close();
 

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
                            