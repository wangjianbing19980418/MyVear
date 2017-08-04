package cn.rongcloud.im.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import cn.rongcloud.im.R;

public class MyView extends Activity {
    private static final String TAG = "MyView";
    private Button btnTV;
    private Button btnLive;
    private Button btnZhubo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
        initView();
    }
    private void initView() {
        btnTV = (Button) findViewById(R.id.btn_tv);
        btnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fakeLogin("张三", "123456", "rtmp://live.hkstv.hk.lxdns.com/live/hks");
//                fakeLogin("张三", "123456", "rtmp://push.facebac.com/live/Vear_Test");
                VideodoStartApplicationWithPackageName("");
            }
        });
        btnLive = (Button) findViewById(R.id.btn_live);
        btnZhubo = (Button) findViewById(R.id.btn_zhubo);
        btnLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fakeLogin("张三", "123456", "rtmp://vlive3.rtmp.cdn.ucloud.com.cn/ucloud/8971");
//                fakeLogin("张三", "123456", "rtmp://vlive3.rtmp.cdn.ucloud.com.cn/ucloud/8977");
                VideodoStartApplicationWithPackageName("");
            }
        });
        btnZhubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RtmpPushdoStartApplicationWithPackageName();
//                Intent mIntent=new Intent(MyView.this,rtmppush.hx.com.rtmppush.StartActivity.class);
//                startActivity(mIntent);
                RtmpPushdoStartApplicationWithPackageName();
            }
        });
    }


    private void VideodoStartApplicationWithPackageName(String pS) {
        Intent mIntent = new Intent();
        ComponentName comp = new ComponentName("vitamio.vitamiolibrary","vitamio.vitamiolibrary.MainActivity");
        mIntent.setComponent(comp);
        mIntent.setAction("android.intent.action.MAIN");
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mIntent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe){
            startActivity(mIntent);
            return ;
        }
        else{
            new AlertDialog.Builder(MyView.this)
                    .setMessage("您还没有安装VideoPlayer，请先安装")
                    .setTitle("安装提示")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("安装", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://118.89.22.167/javaclub/video-release.apk"));
                            startActivity(intent);
                        }
                    })
                    .show();
            return;
        }
    }
    private void RtmpPushdoStartApplicationWithPackageName() {
        Intent mIntent = new Intent();
        ComponentName comp = new ComponentName("rtmppush.hx.com.rtmppush","rtmppush.hx.com.rtmppush.StartActivity");
        mIntent.setComponent(comp);
        mIntent.setAction("android.intent.action.MAIN");
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mIntent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe){
            startActivity(mIntent);
            return ;
        }
        else{
            new AlertDialog.Builder(MyView.this)
                    .setMessage("您还没有安装Rtmp推流，请先安装")
                    .setTitle("安装提示")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("安装", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://118.89.22.167/javaclub/rtmp_push-release.apk"));
                            startActivity(intent);
                        }
                    })
                    .show();
            return;
        }
    }
}
