package com.coofond.myzxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.coofond.zxingdemo.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity {

    public final static String URL="url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }
    private void initView() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
        intentIntegrator
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                .setPrompt("将二维码/条码放入框内，即可自动扫描")//写那句提示的话
                .setOrientationLocked(false)//扫描方向固定
                .setCaptureActivity(CustomScanAct.class) // 设置自定义的activity是CustomActivity
                .initiateScan(); // 初始化扫描
    }

    private void initEvent() {
//        btnClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //假如你要用的是fragment进行界面的跳转
//                //IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(ShopFragment.this).setCaptureActivity(CustomScanAct.class);
//                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
//                intentIntegrator
//                        .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
//                        .setPrompt("将二维码/条码放入框内，即可自动扫描")//写那句提示的话
//                        .setOrientationLocked(false)//扫描方向固定
//                        .setCaptureActivity(CustomScanAct.class) // 设置自定义的activity是CustomActivity
//                        .initiateScan(); // 初始化扫描
//            }
//        });
    }

    //获取扫描的结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
            } else {
                // ScanResult 为获取到的字符串
                String ScanResult = intentResult.getContents();

                Log.d("result", ScanResult);


                if(ScanResult.contains("phone=")){
                    Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
                   return;
                }
                Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
//                tvResult.setText(ScanResult);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ScanResult));
                startActivity(intent);
                MainActivity.this.finish();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
