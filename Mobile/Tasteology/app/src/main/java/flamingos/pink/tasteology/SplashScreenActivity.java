package flamingos.pink.tasteology;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                test();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void test() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String id = sp.getString("userId","");
        Log.i("MainActivity", "Got " + id);
        if( id.equals("") ){
            Intent i = new Intent(getBaseContext(),LoginActivity.class);
            startActivity(i);
            finish();
        } else {
            ReloginTask rt = new ReloginTask();
            rt.execute(id);
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    @Override
    protected void onResume(){
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public class ReloginTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient okhc = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("id",params[0])
                    .build();
            Request request = new Request.Builder()
                    .url("http://" + MainActivity.url + ":8080/Tasteology/reloginMobile")
                    .post(body)
                    .build();
            try {
                Response response = okhc.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String aString) {
            super.onPostExecute(aString);
            Log.i("MainActivity","Returned " + aString);
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor spe = sp.edit();
            spe.putString("userId", aString);
            spe.commit();
            Intent mainIntent = null;
            if( aString.length() > 0 ) {
                mainIntent = new Intent(getBaseContext(), MainActivity.class);
            } else {
                mainIntent = new Intent(getBaseContext(), LoginActivity.class);
            }
            startActivity(mainIntent);
            finish();
        }
    }
}
