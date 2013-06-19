 package jh.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils { 
	  
    /** 
     * �ж������Ƿ���� 
     *  
     * @return  -1�����粻����      0���ƶ�����    1��wifi����    2��δ֪���� 
     */
    public static int isNetworkEnabled(Context context) { 
        int status = -1; 
        ConnectivityManager connectivityManager = (ConnectivityManager) context 
                .getSystemService(Context.CONNECTIVITY_SERVICE); 
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo(); 
          
        if(networkInfo != null && networkInfo.isConnected()) { 
            switch(networkInfo.getType()){ 
                case ConnectivityManager.TYPE_MOBILE:{     //�ƶ����� 
                    status = 0; 
                    break; 
                }    
                case ConnectivityManager.TYPE_WIFI:{     //wifi���� 
                    status = 1; 
                    break; 
                } 
                default :{ 
                    status = 2; 
                } 
            } 
        } 
          
        return status; 
    } 
    
    public static boolean isNetWorkValiable(Context context){
    	// ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ���

        ConnectivityManager connectivity = (ConnectivityManager) context.
        		getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null){
            // ��ȡ�������ӹ���Ķ���
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null&& info.isConnected()){
                // �жϵ�ǰ�����Ƿ��Ѿ�����
                if (info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }  
             
            }
    	} 

        return false;
    }
  
}