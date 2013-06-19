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
		//����version
		HttpDownloader httpDownLoad=new HttpDownloader();
		String version = httpDownLoad.download(url);
		//����busList��̬����
		BusUtils.setDownloadVersion(version);
		int flag = 0;
		if(BusUtils.checkUpdate()){
			flag = 1;
		}

		Bundle extras = intent.getExtras(); 
        if(extras != null){ 
            Messenger mesenger = (Messenger)extras.get("Messenger"); 
           //ʹ��Message.obtain()���һ���յ�Message����
            Message msg = Message.obtain( );  
           //���message����Ϣ��  
            msg.arg1 = flag; 
          //ͨ��Messenger��ʹ����Ϣ���ͳ�ȥ��  
            try{ 
                mesenger.send(msg);  
            }catch(Exception e){ 
                Log.w(getClass().getName(),"Exception Message: " + e.toString());
            }
        }
	}

}
