 package jh.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils { 
	  
    /** 
     * 判断网络是否可用 
     *  
     * @return  -1：网络不可用      0：移动网络    1：wifi网络    2：未知网络 
     */
    public static int isNetworkEnabled(Context context) { 
        int status = -1; 
        ConnectivityManager connectivityManager = (ConnectivityManager) context 
                .getSystemService(Context.CONNECTIVITY_SERVICE); 
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo(); 
          
        if(networkInfo != null && networkInfo.isConnected()) { 
            switch(networkInfo.getType()){ 
                case ConnectivityManager.TYPE_MOBILE:{     //移动网络 
                    status = 0; 
                    break; 
                }    
                case ConnectivityManager.TYPE_WIFI:{     //wifi网络 
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
    	// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）

        ConnectivityManager connectivity = (ConnectivityManager) context.
        		getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null){
            // 获取网络连接管理的对象
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null&& info.isConnected()){
                // 判断当前网络是否已经连接
                if (info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }  
             
            }
    	} 

        return false;
    }
  
}