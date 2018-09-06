package com.droidrank.sample;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    private Button previous, next;
    private ImageView imageView;
    private static int imageOffset = 0;
    private int width;
    private int height;
    private boolean isNotFirstTime;
    private boolean isNextClicked;
    private ArrayList<String> imageUrlList;
    private String jsonUrls;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        imageUrlList = new ArrayList<String>();
        Utility.execute(new LoadPaginatedUrlsAsyncTask(), 0);
    }

    private void initView() {
        Display mDisplay = getWindowManager().getDefaultDisplay();
        width = mDisplay.getWidth();
        height = mDisplay.getHeight();

        imageView = (ImageView) findViewById(R.id.imageview);
        previous = (Button) findViewById(R.id.previous);
        //onclick of previous button should navigate the user to previous image
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // r&d by commenting below
/*                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {*/
                        isNextClicked = false;
                        imageOffset--;
                        if (imageOffset > 0) {
                            Utility.execute(new MyAsyncTask(), imageOffset);
                        } else if (imageOffset < 0) {
                            imageOffset = imageUrlList.size() - 1;
                            Utility.execute(new MyAsyncTask(), imageOffset);
                        }

                        if (imageOffset >= imageUrlList.size() - 5) {
                            Utility.execute(new LoadPaginatedUrlsAsyncTask(), imageUrlList.size());
                        }
/*                    }
                });*/
            }
        });
        next = (Button) findViewById(R.id.next);
        //onclick of next button should navigate the user to next image
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNextClicked = true;
                imageOffset++;
                if (imageOffset > 0) {
                    Utility.execute(new MyAsyncTask(), imageOffset);
                } else if (imageOffset == imageUrlList.size() - 1) {
                    imageOffset = 0;
                    Utility.execute(new MyAsyncTask(), imageOffset);
                }

                if (imageOffset >= imageUrlList.size() - 5) {
                    Utility.execute(new LoadPaginatedUrlsAsyncTask(), imageUrlList.size());
                }
            }
        });
    }

    class LoadPaginatedUrlsAsyncTask extends AsyncTask<Integer, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Integer... params) {
            jsonUrls = Utility.getResponse("http://52e4a06a.ngrok.io/fetch.php?offset=" + params[0]);

            try {
                JSONObject jsonRootObject = new JSONObject(jsonUrls);
                JSONArray jsonArray = jsonRootObject.optJSONArray("images");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String imageUrl = jsonObject.optString("imageUrl").toString();
                    imageUrlList.add(imageUrl);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return imageUrlList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            if (!isNotFirstTime) {
                Utility.execute(new MyAsyncTask(), imageOffset);
                isNotFirstTime = true;
            }
        }
    }

    class MyAsyncTask extends AsyncTask<Integer, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {

            Bitmap bitmap = Utility.getBitmapFromURL(imageUrlList.get(imageOffset));
            if (bitmap == null) {
                return bitmap;
            } else {
                return Utility.getResizedBitmap(bitmap, height, width);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                if (isNextClicked) {
                    next.performClick();
                } else {
                    previous.performClick();
                }
            }
        }
    }
}
