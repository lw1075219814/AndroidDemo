package demo.ailen.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hyphenate.easeui.ui.EaseChatFragment;

import demo.ailen.com.myapplication.Constant;
import demo.ailen.com.myapplication.R;

/**
 * @Module :
 * @Comments : 聊天
 * @Author : liuwen
 * @CreateDate : 2016/9/27
 * @ModifiedBy : liuwen
 * @ModifiedDate: 2016/9/27
 * @Modified :
 */
 public class ChatActivity extends AppCompatActivity{

 @Override
 protected void onCreate(@Nullable Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_chat);
  initView();
 }

 private void initView() {
  Intent intent = getIntent();
  String chat_type = intent.getStringExtra(Constant.EXTRA_CHAT_TYPE);
  String user_id = intent.getStringExtra(Constant.EXTRA_USER_ID);

  //EaseChatFragment
 }
}