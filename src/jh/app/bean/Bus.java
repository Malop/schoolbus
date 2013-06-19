package jh.app.bean;

import jh.app.utils.BusUtils;



public class Bus{

	private String id;
	private String time;
	private String busNum;
	private String start;
	private String end;
	private String route;
	private boolean state = false;
	
	public Bus(String id, String time, String busNum,
			String start, String end, String route) {
		super();
		this.id = id;
		this.time = time;
		this.busNum = busNum;
		this.start = start;
		this.end = end;
		this.route=route;
	}
	public Bus() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBusNum() {
		return busNum;
	}
	public void setBusNum(String busNum) {
		this.busNum = busNum;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}

	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public void setState(String time) {
		this.state = BusUtils.isBusGone(time);
	}
	@Override
	public String toString() {
		return "busInfor [id=" + id + ", time=" + time + ", busNum="
				+ busNum + ", start=" + start + ", end="
				+ end + ", route="+ route + "]";
	}
}

