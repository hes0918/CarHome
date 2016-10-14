package com.lanou3g.dllo.athm.controler.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.athm.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WelcomeActivity extends AbsBaseActivity {
    private TextView wlcmTv;
    private ImageView wlcmImg;
    TimeTask timeTask = new TimeTask();
    private  String theUrl = "http://a1.qpic.cn/psb?/30d800fa-f7cd-4ae7-9937-b40726bbec2c/OBm3vc2ttKLBwcBTzN986xu3ECwuSAqDcmudbz9IjCo!/b/dHwBAAAAAAAA&bo=gALiAwAAAAADB0E!&rf=viewer_4";

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        wlcmTv = (TextView) findViewById(R.id.welcom_tv);
        wlcmImg = (ImageView) findViewById(R.id.welcom_iv);
    }

    @Override
    protected void initDatas() {
        new ImageTask().execute(theUrl);

        timeTask.execute(5);
        initViewTime();
        addTime();
    }
    private void addTime() {
        wlcmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTask.cancel(false);
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViewTime() {


    }


    public class ImageTask extends AsyncTask<String,Void,Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {
            String imgUrl = params[0];
            HttpURLConnection connection = null;
            InputStream is = null;
            Bitmap bitmap = null;

            try {
                URL url = new URL(imgUrl);
                connection = (HttpURLConnection)url.openConnection();
                if (connection.getResponseCode() ==200){

                    is = connection.getInputStream();

                    bitmap = BitmapFactory.decodeStream(is);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (is !=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection !=null){
                    connection.disconnect();
                }
            }

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap!=null){
                wlcmImg.setImageBitmap(bitmap);
            }

        }
    }

    private  class  TimeTask extends AsyncTask<Integer,Integer,String>{


        @Override
        protected String doInBackground(Integer... params) {
            int all = params[0];
            int now = 1;
            while (now<all){
                publishProgress(now);
                now++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            wlcmTv.setText(values[0]+"s");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
