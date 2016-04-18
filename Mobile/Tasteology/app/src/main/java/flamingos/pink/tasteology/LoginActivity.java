package flamingos.pink.tasteology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.button_login);
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : check username and password then login
                LoginTask lt = new LoginTask();
                lt.execute(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public class LoginTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient okhc = new OkHttpClient.Builder()
                                    .connectTimeout(100,TimeUnit.SECONDS)
                                    .build();
            Log.i("LoginTask",params[0]);
            Log.i("LoginTask",params[1]);
            RequestBody body = new FormBody.Builder()
                    .add("username",params[0])
                    .add("password",params[1])
                    .build();
            Request request = new Request.Builder()
                    .url("http://" + MainActivity.url + ":8080/Tasteology/loginMobile")
                    .post(body)
                    .build();
            try {
                Response response = okhc.newCall(request).execute();
                String responsebody = response.body().string();
                Log.i("MainActivity","Returning " + responsebody);
                return responsebody;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("LoginTask", s);
            switch(s) {
                case "wrongPass":
                    (new DialogFragment(){
                        @Override
                        public Dialog onCreateDialog(Bundle savedInstanceState) {
                            return (new AlertDialog.Builder(getActivity())
                                    .setTitle("Wrong Password")
                                    .setMessage("You entered an incorrect password")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dismiss();
                                        }
                                    })).create();
                        }
                    }).show(getFragmentManager(),"");
                    break;
                case "noUser":
                    (new DialogFragment(){
                        @Override
                        public Dialog onCreateDialog(Bundle savedInstanceState) {
                            return (new AlertDialog.Builder(getActivity())
                                    .setTitle("No Such User")
                                    .setMessage("There is no user with the username you provided")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dismiss();
                                        }
                                    })).create();
                        }
                    }).show(getFragmentManager(),"");
                    break;
                default:
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor spe = sp.edit();
                    spe.putString("userId", s);
                    Log.i("LoginTask", "Putting " + s);
                    spe.commit();
                    Log.i("LoginTask", "Got " + sp.getString("userId",""));
                    Intent i = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);
                    finish();
            }
        }
    }
}
