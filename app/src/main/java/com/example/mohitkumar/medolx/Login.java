package com.example.mohitkumar.medolx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {


    EditText username,password;
    TextView textView;
    String us1,pass1,id;
    ImageView imageView;

    String server_url= "http://139.59.20.254/medex/android/authorize.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.identer);
        password = (EditText) findViewById(R.id.passwordenter);
        textView = (TextView)findViewById(R.id.sign_up);

        imageView = (ImageView)findViewById(R.id.image1);
        imageView.setImageResource(R.drawable.medexlogo);
        android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        Display display = getWindowManager().getDefaultDisplay();
        layoutParams.height = (int) (display.getHeight()*0.20);
        layoutParams.width = display.getWidth();
        imageView.setLayoutParams(layoutParams);
    }

    public void LoginIn(View view) {

        final String us = username.getText().toString();
        final String pass = password.getText().toString();

        if(us.equals("")||pass.equals("")) {
            Toast.makeText(getApplicationContext(),"The UserName is not registered,Please register first",Toast.LENGTH_LONG).show();
        }

        ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Wait..");
        progressDialog.setMessage("Adding you to our network");
        progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,server_url, (String) null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Log.d("TAG","");
                                        id = response.getString("confirm");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d("Error",error.getMessage());
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(Login.this).addToRequestQueue(jsonObjectRequest);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Slow Internet !!!",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    Log.d("In gere","In here");

                    params.put("USERNAME",us);
                    params.put("PASSWORD",pass);
                    params.put("PASSWORDCONF",pass1);
                    return params;
                }
            };

            MySingleton.getInstance(Login.this).addToRequestQueue(stringRequest);

        SharedPreferences sharedPreferences = getSharedPreferences("UserName",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("NAME",us);
        editor.commit();

//        if(id.equals("yes")) {

        Intent intent = new Intent(Login.this,PersonalActivity.class);
        startActivity(intent);
      //  } else {
           // Toast.makeText(getApplicationContext(),"Enter the correct text",Toast.LENGTH_LONG).show();
      //  }
    }

    public void SignUphere(View view){
        Intent intent = new Intent(Login.this,SignUp.class);
        startActivity(intent);
    }
}
