package jh.app.ui;

import java.util.ArrayList;
import java.util.List;

import jh.app.adapter.ListViewBusAdapter;
import jh.app.schoolbus.R;
import jh.app.service.DownLoadService;
import jh.app.service.DownVersionService;
import jh.app.utils.BusUtils;
import jh.app.utils.FileUtils;
import jh.app.utils.NetworkUtils;
import android.annotation.SuppressLint;
import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActivityGroup implements OnClickListener {

	public static final String XML_URL = "http://bustime.zjut.in/Demo/bus.xml";
	public static final String VERSION_URL = "http://bustime.zjut.in/Demo/version.html";
	public static final String FILE_NAME = "bus.xml";
	public static final int WORK_TO_PF_INDEX = 0;
	public static final int WORK_TO_ZH_INDEX = 1;
	public static final int WEEK_TO_PF_INDEX = 2;
	public static final int WEEK_TO_ZH_INDEX = 3;
	public String out = "system.out";

	private Intent serviceIntent;
	private Intent downloadIntent;
	
	private Button exit;
	private Button route_button;
	// VIewPaer控件
	private ViewPager vp;

	// VIewPager的数据源
	private MyPagerAdapter myAdapter;

	// 为VIewPager保存VIew界面的集合
	private List<View> vessel = new ArrayList<View>();

	// 4个listview装载的View
	private View view1, view2, view3, view4;
	
	private ListView lv1, lv2, lv3, lv4;

	// 主窗体的按钮
	private TextView tab1, tab2, tab3, tab4;

	// 动画图片
	private ImageView cursor;
	
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		exit = (Button) findViewById(R.id.main_back);
		exit.setOnClickListener(this);
		
		route_button = (Button) findViewById(R.id.route_button);
		route_button.setOnClickListener(this);
		
		initBusData();
		initImageView();
		init();
		
		
	}

	public void init() {
		view1 = getLayoutInflater().inflate(R.layout.listview_1, null);
		view2 = getLayoutInflater().inflate(R.layout.listview_2, null);
		view3 = getLayoutInflater().inflate(R.layout.listview_3, null);
		view4 = getLayoutInflater().inflate(R.layout.listview_4, null);
		
		vessel.add(view1);
		vessel.add(view2);
		vessel.add(view3);
		vessel.add(view4);
		
		vp = (ViewPager) findViewById(R.id.viewpagerLayout);
		myAdapter = new MyPagerAdapter();
		vp.setAdapter(myAdapter);
		vp.setCurrentItem(0);// 设置起始默认的值为0
		vp.setOnPageChangeListener(new MyOnPageChangeListener());
		cursor = (ImageView) findViewById(R.id.cursor);

		tab1 = (TextView) findViewById(R.id.tab1);
		tab2 = (TextView) findViewById(R.id.tab2);
		tab3 = (TextView) findViewById(R.id.tab3);
		tab4 = (TextView) findViewById(R.id.tab4);
		
		tab1.setOnClickListener(this);
		tab2.setOnClickListener(this);
		tab3.setOnClickListener(this);
		tab4.setOnClickListener(this);
		
		lv1 = (ListView) view1.findViewById(R.id.listivew_work_to_pf);
		lv2 = (ListView) view2.findViewById(R.id.listivew_work_to_zh);
		lv3 = (ListView) view3.findViewById(R.id.listivew_week_to_pf);
		lv4 = (ListView) view4.findViewById(R.id.listivew_week_to_zh);
		
		lv1.setOnItemClickListener(new OnItemClickListener() {  
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {  
                 
            	Intent i = new Intent(parent.getContext(), BusActivity.class);
            	Bundle mBundle = new Bundle();
            	mBundle.putInt("index1", WORK_TO_PF_INDEX);
            	mBundle.putInt("index2", position);
            	i.putExtras(mBundle);
            	startActivity(i);
            } 
        }); 
		lv2.setOnItemClickListener(new OnItemClickListener() {  
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {  
                 
            	Intent i = new Intent(parent.getContext(), BusActivity.class);
            	Bundle mBundle = new Bundle();
            	mBundle.putInt("index1", WORK_TO_ZH_INDEX);
            	mBundle.putInt("index2", position);
            	i.putExtras(mBundle);
            	startActivity(i);
            } 
        });
		lv3.setOnItemClickListener(new OnItemClickListener() {  
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {  
                 
            	Intent i = new Intent(parent.getContext(), BusActivity.class);
            	Bundle mBundle = new Bundle();
            	mBundle.putInt("index1", WEEK_TO_PF_INDEX);
            	mBundle.putInt("index2", position);
            	i.putExtras(mBundle);
            	startActivity(i);
            } 
        });
		lv4.setOnItemClickListener(new OnItemClickListener() {  
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {  
                 
            	Intent i = new Intent(parent.getContext(), BusActivity.class);
            	Bundle mBundle = new Bundle();
            	mBundle.putInt("index1", WEEK_TO_ZH_INDEX);
            	mBundle.putInt("index2", position);
            	i.putExtras(mBundle);
            	startActivity(i);
            } 
        });
		
		lv1.setTextFilterEnabled(true);
		lv2.setTextFilterEnabled(true);
		lv3.setTextFilterEnabled(true);
		lv4.setTextFilterEnabled(true);
		setListViewAdapter();
	}

	/*
	 * 初始化滑动条
	 */
	private void initImageView() {

		cursor = (ImageView) findViewById(R.id.cursor);

		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.short_line).getWidth();// 获取图片宽度

		DisplayMetrics dm = new DisplayMetrics();

		getWindowManager().getDefaultDisplay().getMetrics(dm);

		int screenW = dm.widthPixels;// 获取屏幕分辨率宽度
		offset = (screenW / 4 - bmpW) / 2 + 1;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);// 平移到(0,0)点
		cursor.setImageMatrix(matrix);// 设置动画初始位置

	}

	// 适配器
	class MyPagerAdapter extends PagerAdapter {
		
		public Object instantiateItem(View arg0, int arg1) {
			Log.i(out, "instantiate"+arg1);
			((ViewPager) arg0).addView(vessel.get(arg1), 0);
			return vessel.get(arg1);
		}
		
		public void destroyItem(View arg0, int arg1, Object arg2) {
			Log.i(out, "destory"+ arg1);
			((ViewPager) arg0).removeView(vessel.get(arg1));
		}
		
		public int getCount() {
			//Log.i(out, "count");
			return vessel.size();
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			//Log.i(out, "isviewFrom");
			return arg0 == (arg1);
		}

	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量

		@Override
		public void onPageSelected(int arg0) {
			/* arg0 ==1的时候表示正在滑动，arg0==2的时候表示滑动完毕了，arg0==0的时候表示什么都没做，就是停在那。 */
			Animation animation = new TranslateAnimation(one * currIndex, one * arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(100);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	/**
	 * | 点击事件
	 */
	@SuppressLint({ "ResourceAsColor", "ResourceAsColor" })
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tab1:
			vp.setCurrentItem(0);
			break;
		case R.id.tab2:
			vp.setCurrentItem(1);
			break;
		case R.id.tab3:
			vp.setCurrentItem(2);
			break;
		case R.id.tab4:
			vp.setCurrentItem(3);
			break;
		case R.id.route_button:
			Intent i = new Intent(MainActivity.this, RouteActivity.class);
			startActivity(i);
			break;
		case R.id.main_back:
			exitApp();
			break;
		}
	}

	public void initBusData() {
		// xml下载配置
		downloadIntent = new Intent(this, DownLoadService.class);
		downloadIntent.putExtra("URL", XML_URL);
		downloadIntent.putExtra("FILE", FILE_NAME);
		downloadIntent.putExtra("Messenger", new Messenger(fileHandler));
		// 版本下载配置
		serviceIntent = new Intent(this, DownVersionService.class);
		serviceIntent.putExtra("URL", VERSION_URL);
		serviceIntent.putExtra("Messenger", new Messenger(versionHandler));

		// xml文件是否存在
		if (FileUtils.isFileExist("bus/bus.xml")) {
			// 网络是否连接
			if (NetworkUtils.isNetWorkValiable(this)) {
				String content = FileUtils.readSD("bus/bus.xml");
				BusUtils.setBusList(content);
				// 下载版本号
				startService(serviceIntent);
			} else {
				String content = FileUtils.readSD("bus/bus.xml");
				BusUtils.setBusList(content);
			}
		} else {
			if (NetworkUtils.isNetWorkValiable(this)) {
				// 下载xml文件
				startService(downloadIntent);
			} else {
				Toast.makeText(this, "亲，网络未连接！", Toast.LENGTH_SHORT).show();
			}

		}
	}

	private Handler versionHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int flag = msg.arg1;
			if (flag == 1) {
				Dialog dialog = new AlertDialog.Builder(MainActivity.this)
						.setTitle("提醒")
						.setMessage("校车时刻表有更新！")
						.setPositiveButton("更新",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// 下载xml文件
										startService(downloadIntent);
										dialog.cancel();
									}
								}).setNegativeButton("取消", null).create();
				dialog.show();

			}
		}
	};

	private Handler fileHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			//更新listview信息
			setListViewAdapter();
			Toast.makeText(MainActivity.this, "更新完成！", Toast.LENGTH_SHORT).show();
		}
	};
	
	private void setListViewAdapter(){
		lv1.setAdapter(new ListViewBusAdapter(MainActivity.this, BusUtils.getBusList(WORK_TO_PF_INDEX), R.layout.list_item));
		lv2.setAdapter(new ListViewBusAdapter(MainActivity.this, BusUtils.getBusList(WORK_TO_ZH_INDEX), R.layout.list_item));
		lv3.setAdapter(new ListViewBusAdapter(MainActivity.this, BusUtils.getBusList(WEEK_TO_PF_INDEX), R.layout.list_item));
		lv4.setAdapter(new ListViewBusAdapter(MainActivity.this, BusUtils.getBusList(WEEK_TO_ZH_INDEX), R.layout.list_item));
		
		lv1.setSelection(BusUtils.getBusInLoaction(WORK_TO_PF_INDEX));
		lv2.setSelection(BusUtils.getBusInLoaction(WORK_TO_ZH_INDEX));
		lv3.setSelection(BusUtils.getBusInLoaction(WEEK_TO_PF_INDEX));
		lv4.setSelection(BusUtils.getBusInLoaction(WEEK_TO_ZH_INDEX));
	}

	private void exit(){
		try {
			finish();
			ActivityManager activityMgr= (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(getPackageName());
			System.exit(0);
		} catch (Exception e) {	}
	}
	
	public void exitApp(){
		new AlertDialog.Builder(MainActivity.this).setIcon(R.drawable.ic_dialog_info)
		.setTitle(R.string.app_menu_surelogout)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				exit();
			}
		}).setNegativeButton(R.string.cancel, null).show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean flag = true;
		if(keyCode == KeyEvent.KEYCODE_BACK){
			exitApp();
		}else{
			flag = super.onKeyDown(keyCode, event);
		}
		return flag;
	}
	
}
