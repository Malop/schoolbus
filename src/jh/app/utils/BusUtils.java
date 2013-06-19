package jh.app.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jh.app.XMLParser.XMLContentHandler;
import jh.app.bean.Bus;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.text.format.Time;

public class BusUtils {
	
	private static List<List<Bus>> busList = null;
	private static double oldVersion = 0.1;
	private static double downloadVersion = 0.1;
	//private static String content;
	public static List<Bus> getBusList(int location){
		
		if(busList == null){
			String content = "";
			if(FileUtils.isFileExist("bus/bus.xml"))
				content = FileUtils.readSD("bus/bus.xml");
			else
				content = "<?xml version='1.0' encoding='UTF-8'?><jh><version>1.0</version>	" +
						"<about>worktopingfeng</about>" +
						"<bus>	<number></number><time></time><start></start><route></route><end>/end>	<busNu></busNu>	</bus>" +
						"<about>worktozhaohui</about>" +
						"<bus>	<number></number><time></time><start></start><route></route><end>/end>	<busNu></busNu>	</bus>"+
						"<about>weektopingfeng</about>" +
						"<bus>	<number></number><time></time><start></start><route></route><end>/end>	<busNu></busNu>	</bus>"+
						"<about>weektozhaohui</about>" +
						"<bus>	<number></number><time></time><start></start><route></route><end>/end>	<busNu></busNu>	</bus>";
			setBusList(content);
		}
		return busList.get(location);
	}
	
	public static void setBusList(String xml){
		SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
		List<Bus> bus1=new ArrayList<Bus>();
		List<Bus> bus2=new ArrayList<Bus>();
		List<Bus> bus3=new ArrayList<Bus>();
		List<Bus> bus4=new ArrayList<Bus>();
		XMLContentHandler handler=new XMLContentHandler(bus1,bus2,bus3,bus4);
		try{
		  SAXParser saxParser=saxParserFactory.newSAXParser();
		  XMLReader xmlReader=saxParser.getXMLReader();	  
		  xmlReader.setContentHandler(handler);
		  xmlReader.parse(new InputSource(new StringReader(xml)));

		}catch(Exception ex){
			ex.printStackTrace();
			//System.out.println("d--------->");
		}
		busList = new ArrayList<List<Bus>>();
		busList.add(bus1);
		busList.add(bus2);
		busList.add(bus3);
		busList.add(bus4);
		oldVersion = Double.valueOf(handler.getVersion())/10;
	}
	
	public static void setDownloadVersion(String version){
		downloadVersion = Double.valueOf(version);
	}
	
	public static boolean checkUpdate(){
		if(oldVersion < downloadVersion){
			//¸üÐÂ¾É°æ±¾ºÅ
			oldVersion = downloadVersion;
			return true;
		}
		return false;
	}
	
	public static Bus getBus(int index1, int postion){
		return busList.get(index1).get(postion);
	}
	
	public static int getBusInLoaction(int index){
		int i = 0;
		for(i=0; i<busList.get(index).size(); i++){
			if(busList.get(index).get(i).getState() == false)
				break;
		}
		return i;
	}
	
	public static boolean isBusGone(String time){
		Time t = new Time();
		t.setToNow();
		int minutes = t.hour*60 + t.minute;
		String[] s= time.split(":");
		int hour=Integer.parseInt(s[0]);
		int minute=Integer.parseInt(s[1]);
		int minutes2 = hour*60 + minute;
		
		if(minutes < minutes2){
			return false;
		}
		return true;
	}
}
