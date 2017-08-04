package rtmppush.hx.com.rtmppush;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity {
    public static final String RTMPURL_MESSAGE = "rtmppush.hx.com.rtmppush.rtmpurl";

    private static final int MY_PERMISSIONS_REQUEST_RECODER_PHONE = 1;
    private static final int MY_PERMISSIONS_CAMERA_RECODER_PHONE = 2;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE_RECODER_PHONE = 3;
    private static final int MY_PERMISSIONS_WAKE_LOCK_RECODER_PHONE = 4;
    private static final int MY_PERMISSIONS_READ_PHONE_STATE_RECODER_PHONE = 5;
    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE_RECODER_PHONE = 6;
    private static final int MY_PERMISSIONS_hardware_camera_ = 7;
    private static final int MY_PERMISSIONS_hardware_camera_autofocus = 8;

    private Button _startRtmpPushButton = null;
    private Button _testRtmpPushButton = null;
    private EditText ip;
    private EditText port;

    private EditText room;
    private EditText pindao;

//    private String test_url = "rtmp://118.89.22.167:1935/hls/test";
//    private String test_url = "rtmp://192.168.1.102:1935/live/12345";
    private String test_url = "rtmp://203.207.99.19:1935/live/CCTV5";
    private String url = "";

    private View.OnClickListener _startRtmpPushOnClickedEvent = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Intent i = new Intent(StartActivity.this, MainActivity.class);
            String ip_t= ip.getText().toString();
            String port_t= port.getText().toString();
            String room_t= room.getText().toString();
            String pindao_t= pindao.getText().toString();

            if(TextUtils.isEmpty(ip_t)||TextUtils.isEmpty(port_t)||TextUtils.isEmpty(room_t)||TextUtils.isEmpty(pindao_t)){
                Toast.makeText(StartActivity.this, "输入推流地址不能够为空", Toast.LENGTH_SHORT).show();
                return;
            }
//            Toast.makeText(StartActivity.this, ip_t+"\n"+port_t+"\n"+room_t+"\n"+pindao_t, Toast.LENGTH_LONG).show();
            String url="rtmp://"+ip_t+":"+port_t+"/"+room_t+"/"+pindao_t;
            Log.d("url", url);
//            Toast.makeText(StartActivity.this, url, Toast.LENGTH_LONG).show();
            i.putExtra(StartActivity.RTMPURL_MESSAGE, url);
            StartActivity.this.startActivity(i);
        }
    };

    private void InitUI(){

        ip = (EditText) findViewById(R.id.ip_et);
        port= (EditText) findViewById(R.id.port_et);
        room = (EditText) findViewById(R.id.room_et);
        pindao= (EditText) findViewById(R.id.pindao_et);
        _startRtmpPushButton = (Button)findViewById(R.id.startRtmpButton);
        _startRtmpPushButton.setOnClickListener(_startRtmpPushOnClickedEvent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        InitUI();

        askPermission(Manifest.permission.RECORD_AUDIO,MY_PERMISSIONS_REQUEST_RECODER_PHONE);
        askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE_RECODER_PHONE);
        askPermission(Manifest.permission.CAMERA,MY_PERMISSIONS_CAMERA_RECODER_PHONE);
        askPermission(Manifest.permission.WAKE_LOCK,MY_PERMISSIONS_WAKE_LOCK_RECODER_PHONE);
        askPermission(Manifest.permission.READ_PHONE_STATE,MY_PERMISSIONS_READ_PHONE_STATE_RECODER_PHONE);
        askPermission(Manifest.permission.READ_EXTERNAL_STORAGE,MY_PERMISSIONS_READ_EXTERNAL_STORAGE_RECODER_PHONE);
//        askPermission(Manifest.permission_group.CAMERA,MY_PERMISSIONS_hardware_camera_);


        _testRtmpPushButton = (Button) findViewById(R.id.test_btn);
        _testRtmpPushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip.setText("203.207.99.19");
                port.setText("1935");
                room.setText("live");
                pindao.setText("CCTV5");
            }
        });
    }

    private void askPermission(String string,int time) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(StartActivity.this,string)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(StartActivity.this,
                    string)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(StartActivity.this,
                        new String[]{string},
                        time);
            }
        }
    }



}
