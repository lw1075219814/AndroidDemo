package demo.ailen.com.myapplication;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

/**
 * @Module :
 * @Comments : 
 * @Author : liuwen
 * @CreateDate : 2016/9/26
 * @ModifiedBy : liuwen
 * @ModifiedDate: 2016/9/26
 * @Modified :
 */
 public class BaseApp extends Application{

 @Override
 public void onCreate() {
  super.onCreate();
  EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
  options.setAcceptInvitationAlways(false);
//初始化
  EMClient.getInstance().init(getApplicationContext(), options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
  EMClient.getInstance().setDebugMode(true);
 }
}