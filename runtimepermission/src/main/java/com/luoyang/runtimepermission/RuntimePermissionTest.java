package com.luoyang.runtimepermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * 动态权限
 */
public class RuntimePermissionTest extends AppCompatActivity {

    private static final String TAG = "RuntimePermissionTest";
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVar();
        initView();
    }

    private void initVar() {
        mIntent = new Intent();
    }

    private void initView() {
        setContentView(R.layout.activity_runtime_permission_test);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                call();
                if (true) return;
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        Log.d(TAG, "onClick: 没有授权的时候整这里");
                    }
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    call();
                }
                break;
        }
    }

    private void call() {
        try {
            mIntent = new Intent(Intent.ACTION_CALL);
            mIntent.setData(Uri.parse("tel:10086"));
            startActivity(mIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "You denied the permissionn", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
