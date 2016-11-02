package com.example.ansan.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        if(isStoragePermissionGranted()==false){
            Toast.makeText(getApplicationContext(), "SD Card 사용 불가",Toast.LENGTH_SHORT).show();

            return;

        }

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String folder = path + "/myLoverDir";
        String filename = folder + "/myfile.txt";

        File myfolder = new File(folder);

        switch (v.getId()){
            case R.id.button:   //폴더 생성
                myfolder.mkdir();
                Toast.makeText(getApplicationContext(), "폴더 생성",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:  //폴더 삭제
                myfolder.delete();
                Toast.makeText(getApplicationContext(), "폴더 삭제",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3: // 파일 생성
                try {
                    FileOutputStream fos = new FileOutputStream(filename);
                    String str = "Hello";

                    fos.write(str.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(), "파일 생성",Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.button4: // 파일 읽기


                Toast.makeText(getApplicationContext(), "파일 읽기",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:  //파일 목록 가져오기

                Toast.makeText(getApplicationContext(), "파일 목록 가져오기",Toast.LENGTH_SHORT).show();
                break;


        }
    }
    String TAG = "TEST";
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

}
