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
public class restaurant_yelp extends SeleneseTestCase{

    public FileWriter log;
    public FileWriter err;
    public FileWriter fin;

    public void setUp()throws Exception {
        setUp("http://www.yelp.com", "*firefox");
        }
 
    private String BASE_WORK_FOLDER="./";
    public void testNew() throws Exception {
    Boolean first = true;
    
    String logName = "rest_yelp_1_20111120.txt";
    log = new FileWriter(logName,false); 
    
    //String file = "fb_places_santa_monica2.txt";
    //BufferedReader br = new BufferedReader(new FileReader(file));  
    //String line = null; 
 

    String[] workParams;
 
    long start, end;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    DateFormat durationFormat = new SimpleDateFormat("HH:mm:ss");
    durationFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    String summary;
    int count, i,j,k;

  
    start = System.currentTimeMillis();
    first = true;
    count = 0;
    int waitCount;
    String url;
    
    /*======================================================*/
    /*==========METADATA SEARCH=============================*/

    String ctgy_xpath = "id('cat_display')/a";
    String place_xpath = "id('bizInfoHeader')/h1";
    String addr1_xpath = "id('bizInfoContent')/address/span[1]";
    String addr2_xpath = "id('bizInfoContent')/address/span[2]";
    String addr3_xpath = "id('bizInfoContent')/address/span[3]";
    String addr4_xpath = "id('bizInfoContent')/address/span[4]";
    String addr5_xpath = "//*[contains(text(),'Neighborhood')]";
    String phone_xpath = "id('bizPhone')/span[2]/span[2]/span[3]/span";
    String price_xpath = "id('price_tip')";
    String nrate_xpath = "id('bizRating')/span";
    
    //SAMPLE BISUNIESS INFO XPATH -- CREATE LOOP FOR ACTUAL
    String bizc_xpath = "id('bizAdditionalInfo')/dl[1]/dt[2]";
    String bizv_xpath = "id('bizAdditionalInfo')/dl[1]/dd[2]"; 
 
 
    String id, inpt_name, ctgy, place, addr1, addr2, addr3, addr4, addr5, phone, price, nrate, message, err_message = null;
    String[] x1 = null, y1 = null, x2 = null, y2 = null, x3 = null, y3 = null;
    
    
    /*while ((line = br.readLine()) != null){ 
    	System.out.println(line);
    	workParams = line.split(",");
    	count++;
    	
        id = workParams[0];
        url = workParams[1]; 
        inpt_name = workParams[2];   
        */
    
        id = "1";
        url = "/biz/wolfgang-puck-express-santa-monica";

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
        	   ctgy = getValueByXPath(ctgy_xpath);
        	   addr1 = getValueByXPath(addr1_xpath); 
        	   addr2 = getValueByXPath(addr2_xpath);
        	   addr3 = getValueByXPath(addr3_xpath);
        	   addr4 = getValueByXPath(addr4_xpath);
        	   addr5 = getValueByXPath(addr5_xpath);
        	   phone = getAttributeByXPath(phone_xpath);
        	   price = getAttributeByXPath(price_xpath);
        	   nrate = getAttributeByXPath(nrate_xpath); 
        	   
        	   
        	   //Loop through business attributes -- Column 1
        	   for(i=0; i<7; i++) { 
        		  x1[i] = getValueByXPath("id('bizAdditionalInfo')/dl[1]/dt[" + i + "]");
        		  y1[i] = getValueByXPath("id('bizAdditionalInfo')/dl[1]/dd[" + i + "]"); 
        		  System.out.println(x1[i]);  
        	   }
        	   
        	   //Loop through business attributes -- Column 2
        	   for(j=0; j<7; j++){
          		  x2[j] = getValueByXPath("id('bizAdditionalInfo')/dl[2]/dt[" + j + "]");
          		  y2[j] = getValueByXPath("id('bizAdditionalInfo')/dl[2]/dd[" + j + "]"); 
          		  System.out.println(x2[j]);       		   
         		   
         	   }
        	   
        	   //Loop through business attributes -- Column 3
        	   for(k=0; k<7; k++){
          		  x3[j] = getValueByXPath("id('bizAdditionalInfo')/dl[3]/dt[" + k + "]");
          		  y3[j] = getValueByXPath("id('bizAdditionalInfo')/dl[3]/dd[" + k + "]"); 
          		  System.out.println(x3[j]);       		   
         		   
         	   } 
       
        	   String[] scrapeDataArray = {quote(id),quote(ctgy),quote(place),quote(addr1),quote(addr2),quote(addr3),quote(addr4),quote(addr5),
        			                       quote(phone),quote(price),quote(nrate),quote(url)};
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
    //}
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
                            