package jh.app.ui;

import jh.app.bean.Bus;
import jh.app.schoolbus.R;
import jh.app.utils.BusUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BusActivity extends Activity{

	private Button back;
	private TextView start;
	private TextView end;
	private TextView number;
	private TextView time;
	private TextView route;
	private TextView num;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus_detail);
		start = (TextView) findViewById(R.id.bus_start);
		end = (TextView) findViewById(R.id.bus_end);
		number = (TextView) findViewById(R.id.bus_number);
		time = (TextView) findViewById(R.id.bus_time);
		route = (TextView) findViewById(R.id.bus_route);
		num = (TextView) findViewById(R.id.bus_num);
		back = (Button) findViewById(R.id.bus_detail_back);
		Bundle bundle = getIntent().getExtras();   
		int index1 = bundle.getInt("index1");
		int index2 = bundle.getInt("index2");
		Bus bus = BusUtils.getBus(index1, index2);
		
		start.setText(" 出发地：" + bus.getStart());
		end.setText(" 目的地：" + bus.getEnd());
		number.setText("车辆班次：" + bus.getId());
		time.setText("出发时间：" + bus.getTime());
		route.setText("运行路线：" + bus.getRoute());
		num.setText("车辆情况：" + bus.getBusNum());
		
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	
}
