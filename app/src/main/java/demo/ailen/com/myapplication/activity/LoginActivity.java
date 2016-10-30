package demo.ailen.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;

import demo.ailen.com.myapplication.Constant;
import demo.ailen.com.myapplication.R;

/**
 * @Module :
 * @Comments :登录
 * @Author : liuwen
 * @CreateDate : 2016/9/26
 * @ModifiedBy : liuwen
 * @ModifiedDate: 2016/9/26
 * @Modified :
 */
 public class LoginActivity extends AppCompatActivity{

    private EditText edit_user;
    private EditText edit_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
     edit_user = (EditText) findViewById(R.id.edit_user);
     edit_pwd = (EditText) findViewById(R.id.edit_pwd);
     edit_user.setText("Ailen");
     edit_pwd.setText("123456");

        findViewById(R.id.tv_toRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActiivty.class));
            }
        });
 }

    public void toLogin(View view){

        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        final String user = edit_user.getText().toString().trim();
        String pwd = edit_pwd.getText().toString().trim();

        // After logout，the DemoDB may still be accessed due to async callback, so the DemoDB will be re-opened again.
        // close it before login to make sure DemoDB not overlap
        //DemoDBManager.getInstance().closeDB();

        // reset current user name before login
        //DemoHelper.getInstance().setCurrentUserName(currentUsername);

        // call login method
        EMClient.getInstance().login(user, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
           // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                // get user's info (this should be get from App's server or 3rd party service)
                final String username = EMClient.getInstance().getCurrentUser();

                Intent intent = new Intent(LoginActivity.this,
                        ChatActivity.class);
                intent.putExtra(Constant.EXTRA_CHAT_TYPE,Constant.CHATTYPE_SINGLE);
                intent.putExtra(Constant.EXTRA_USER_ID,username);
                startActivity(intent);
                finish();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "登录成功",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "登录失败",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}