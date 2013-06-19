package jh.app.service;

import jh.app.utils.BusUtils;
import jh.app.utils.FileUtils;
import jh.app.utils.HttpDownloader;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class DownLoadService extends IntentService {

	public DownLoadService() {
		super("DownloadService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String url = intent.getStringExtra("URL");
		String fileName = intent.getStringExtra("FILE");
		// ����xml�ļ�
		HttpDownloader httpDownLoad = new HttpDownloader();
		String content = httpDownLoad.download(url);
		// ����busList��̬����

		BusUtils.setBusList(content);
		// xml����д��SD�ļ���
		FileUtils.write2SD(content, fileName);

		Bundle extras = intent.getExtras();
		if (extras != null) {
			Messenger mesenger = (Messenger) extras.get("Messenger");
			// ʹ��Message.obtain()���һ���յ�Message����
			Message msg = Message.obtain();
			// ���message����Ϣ��
			// msg.arg1 = result;
			// ͨ��Messenger��ʹ����Ϣ���ͳ�ȥ��
			try {
				mesenger.send(msg);
			} catch (Exception e) {
				Log.w(getClass().getName(),
						"Exception Message: " + e.toString());
			}
		}
	}

}
