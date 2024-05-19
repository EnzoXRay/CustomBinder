package com.example.binderclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private IAppleManager mAppleManager;
    private TextView mTvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBind = findViewById(R.id.btn_bind);
        btnBind.setOnClickListener(this);

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        Button btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(this);

        Button btnUnbind = findViewById(R.id.btn_unbind);
        btnUnbind.setOnClickListener(this);

        mTvShow = findViewById(R.id.tv_show);
    }

    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("client", "onServiceConnected");
            mAppleManager = new AppleManagerImpl(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("client", "onServiceDisconnected");
            mAppleManager = null;
        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_bind){

            Intent intent = new Intent();
            intent.setAction("com.example.binderserver.aidl");
            intent.setPackage("com.example.binderserver");
            boolean result = bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();

        }else if (v.getId() == R.id.btn_add) {

            try {
                if(mAppleManager != null){
                    mAppleManager.addApple(new Apple("红星", "山东"));
                }else{
                    Toast.makeText(this, "服务端被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
                            .show();
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

        }else if(v.getId() == R.id.btn_show) {

            try {
                if(mAppleManager != null){
                    List<Apple> appleList = mAppleManager.getAppleList();
                    StringBuilder sb = new StringBuilder();
                    for(Apple apple : appleList){
                        sb.append(apple.toString());
                        sb.append("\n");
                    }
                    mTvShow.setText(sb.toString());
                }else{
                    Toast.makeText(this, "服务端被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
                            .show();
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

        }else if(v.getId() == R.id.btn_unbind){

            unbindService(mServiceConn);

        }
    }
}
