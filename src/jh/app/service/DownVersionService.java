package jh.app.service;

import jh.app.utils.BusUtils;
import jh.app.utils.HttpDownloader;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class DownVersionService extends IntentService{

	public DownVersionService() {
		super("DownVersionService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String url = intent.getStringExtra("URL");
		//下载version
		HttpDownloader httpDownLoad=new HttpDownloader();
		String version = httpDownLoad.download(url);
		//设置busList静态变量
		BusUtils.setDownloadVersion(version);
		int flag = 0;
		if(BusUtils.checkUpdate()){
			flag = 1;
		}

		Bundle extras = intent.getExtras(); 
        if(extras != null){ 
            Messenger mesenger = (Messenger)extras.get("Messenger"); 
           //使用Message.obtain()获得一个空的Message对象
            Message msg = Message.obtain( );  
           //填充message的信息。  
            msg.arg1 = flag; 
          //通过Messenger信使将消息发送出去。  
            try{ 
                mesenger.send(msg);  
            }catch(Exception e){ 
                Log.w(getClass().getName(),"Exception Message: " + e.toString());
            }
        }
	}

}
