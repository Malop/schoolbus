package jh.app.XMLParser;

import java.util.List;

import jh.app.bean.Bus;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLContentHandler extends DefaultHandler
{
    private List<Bus> Infor1=null;
    private List<Bus> Infor2=null;
    private List<Bus> Infor3=null;
    private List<Bus> Infor4=null;
    private String Version=null;
    private String tagName=null;
    private String tag=null;
    private Bus BusInfor=null;
	
	
	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public XMLContentHandler(List<Bus> infor1,List<Bus> infor2,List<Bus> infor3,List<Bus> infor4) {
		super();
		Infor1 = infor1;
		Infor2 = infor2;
		Infor3 = infor3;
		Infor4 = infor4;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		tag="";
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		tagName = localName;
		
		if(tagName.equals("bus"))
		{
			BusInfor=new Bus();			
		}
		
	}
	@Override
	public void characters(char[] ch, int start, int length)throws SAXException 
	{
		String str=new String(ch, start, length);
		if(tagName.equals("version")){
			setVersion(str);
		}
		else if(tagName.equals("about")){
			tag=str;
		}
		else if(tagName.equals("number")){
			BusInfor.setId(str);
		}
	    else if(tagName.equals("time")){
	    	BusInfor.setState(str);
			BusInfor.setTime(str);	
		}
		else if(tagName.equals("busNu")){
			BusInfor.setBusNum(str);
		}
		else if(tagName.equals("start")){
			BusInfor.setStart(str);		
		}
		else if(tagName.equals("end")){
			BusInfor.setEnd(str);			
		}
		else if(tagName.equals("route")){
			BusInfor.setRoute(str);
			
		}
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(tag.equals("worktopingfeng")&&localName.equals("bus")){
			Infor1.add(BusInfor);						
		}
		else if(tag.equals("worktozhaohui")&&localName.equals("bus")){
			Infor2.add(BusInfor);						
		}
		else if(tag.equals("weektopingfeng")&&localName.equals("bus")){
			Infor3.add(BusInfor);			
		}
		else if(tag.equals("weektozhaohui")&&localName.equals("bus")){
			Infor4.add(BusInfor);			
		}

		tagName="";
	}
	

}
