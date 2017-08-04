package cn.rongcloud.im.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MyScanActivity extends Activity {
    public final static String URL="url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.coofond.zxingdemo.R.layout.activity_main);

        initView();
    }
    private void initView() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(MyScanActivity.this);
        intentIntegrator
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                .setPrompt("将二维码/条码放入框内，即可自动扫描")//写那句提示的话
                .setOrientationLocked(false)//扫描方向固定
                .setCaptureActivity(CustomScanAct.class) // 设置自定义的activity是CustomActivity
                .initiateScan(); // 初始化扫描
    }

    //获取扫描的结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                finish();
            } else {
                // ScanResult 为获取到的字符串
                String ScanResult = intentResult.getContents();
                Log.d("result", ScanResult);
//                tvResult.setText(ScanResult);
                if(ScanResult.startsWith("1")){
                    Intent mIntent = new Intent(MyScanActivity.this, MySearchFriendActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("friend_phone", ScanResult);
                    mIntent.putExtras(mBundle);
                    startActivity(mIntent);
                    MyScanActivity.this.finish();
                }
                else{
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(ScanResult));
                    startActivity(intent);
                    MyScanActivity.this.finish();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
