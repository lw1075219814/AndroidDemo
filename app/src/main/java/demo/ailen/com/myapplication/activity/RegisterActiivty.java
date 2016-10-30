package demo.ailen.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import demo.ailen.com.myapplication.PreferenceManager;
import demo.ailen.com.myapplication.R;

/**
 * @Module :
 * @Comments :注册
 * @Author : liuwen
 * @CreateDate : 2016/9/26
 * @ModifiedBy : liuwen
 * @ModifiedDate: 2016/9/26
 * @Modified :
 */
 public class RegisterActiivty extends AppCompatActivity{

 @Override
 protected void onCreate(@Nullable Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_register);
  initView();
 }

 private void initView() {
  final EditText edit_user = (EditText) findViewById(R.id.edit_user);
  final EditText edit_pwd = (EditText) findViewById(R.id.edit_pwd);
  edit_user.setText("Ailen");
  edit_pwd.setText("123456");

  findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    final String user = edit_user.getText().toString().trim();
    final String pwd = edit_pwd.getText().toString().trim();

    new Thread(new Runnable() {
     @Override
     public void run() {
      // call method in SDK
      try {
       EMClient.getInstance().createAccount(user, pwd);
       runOnUiThread(new Runnable() {
        @Override
        public void run() {
      // save current user
         PreferenceManager.init(RegisterActiivty.this);//setCurrentUserName before to init
         PreferenceManager.getInstance().setCurrentUserName(user);
         startActivity(new Intent(RegisterActiivty.this,LoginActivity.class));
         finish();
         Toast.makeText(RegisterActiivty.this,"注册成功",Toast.LENGTH_SHORT).show();
        }
       });
      } catch (final HyphenateException e) {
       runOnUiThread(new Runnable() {
        @Override
        public void run() {
         switch (e.getErrorCode()){
          case EMError.NETWORK_ERROR:
           Toast.makeText(RegisterActiivty.this,"Network is not available, please check the network!",Toast.LENGTH_SHORT).show();
           break;
          case EMError.USER_ALREADY_EXIST:
             Toast.makeText(RegisterActiivty.this,"用户已存在",Toast.LENGTH_SHORT).show();
           break;
          case EMError.USER_AUTHENTICATION_FAILED:
             Toast.makeText(RegisterActiivty.this,"用户id或密码错误",Toast.LENGTH_SHORT).show();
           break;
          case EMError.USER_ILLEGAL_ARGUMENT:
           Toast.makeText(RegisterActiivty.this,"Illegal user name",Toast.LENGTH_SHORT).show();
           break;
          default:
           Toast.makeText(RegisterActiivty.this,"Registration failed",Toast.LENGTH_SHORT).show();
           break;
         }
        }
       });
      }

     }
    }).start();

   }
  });
 }
}