package com.example.mohitkumar.medolx;

import android.content.Intent;
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
    String us1,pass1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.identer);
        password = (EditText) findViewById(R.id.passwordenter);
        textView = (TextView)findViewById(R.id.sign_up);

        imageView = (ImageView)findViewById(R.id.image1);
        android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        Display display = getWindowManager().getDefaultDisplay();
        layoutParams.height = (int) (display.getHeight()*0.35);
        layoutParams.width = display.getWidth();
        imageView.setLayoutParams(layoutParams);
    }

    public void LoginIn(View view) {

        final String us = username.getText().toString();
        final String pass = password.getText().toString();

        if(us.equals("")||pass.equals("")) {
            Toast.makeText(getApplicationContext(),"The UserName is not registered,Please register first",Toast.LENGTH_LONG).show();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "", (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("TAG","");
                            us1 = response.getString("user");
                            pass1 = response.getString("password");

                            if(!(us1.equals(us)||pass1.equals(pass))){
                                Toast.makeText(Login.this,"Username or password incorrect!",Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
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


        Intent intent = new Intent(Login.this,PersonalActivity.class);
        startActivity(intent);
    }

    public void SignUphere(View view){
        Intent intent = new Intent(Login.this,SignUp.class);
        startActivity(intent);
    }
}
