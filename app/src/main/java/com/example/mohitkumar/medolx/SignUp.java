package com.example.mohitkumar.medolx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {


    EditText name,username,password,confirmpass,address,pincode;

    ImageView imageView;
    Button button;

    String server_url = "http://139.59.20.254/medex/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.input_nam);
        address = (EditText)findViewById(R.id.Add_line_1);
        pincode = (EditText)findViewById(R.id.pin_code);
        username = (EditText)findViewById(R.id.user_name);
        password = (EditText)findViewById(R.id.input_pass);
        confirmpass = (EditText)findViewById(R.id.confirm_password);


        imageView = (ImageView)findViewById(R.id.image1);
        imageView.setImageResource(R.drawable.medexlogo);
        android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        Display display = getWindowManager().getDefaultDisplay();
        layoutParams.height = (int) (display.getHeight()*0.20);
        layoutParams.width = display.getWidth();
        imageView.setLayoutParams(layoutParams);
    }

    public void SignedUp(View view) {

        final String name1,add1,pin1,user1,pass1;

        name1 = name.getText().toString();
        add1 = address.getText().toString();
        pin1 = pincode.getText().toString();
        user1 = username.getText().toString();
        pass1 = password.getText().toString();

        if(name1.equals("")||add1.equals("")||pin1.equals("")||user1.equals("")||pass1.equals("")) {
            Toast.makeText(SignUp.this,"Enter all the Data",Toast.LENGTH_LONG).show();
        }

        if(!pass1.equals(confirmpass.getText().toString()))
            Toast.makeText(SignUp.this,"The Passwords don't match",Toast.LENGTH_LONG).show();
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Slow Internet !!!" + error.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    Log.d("In gere","In here");

                    params.put("FULLNAME",name1);
                    params.put("ADDR",add1);
                    params.put("PIN",pin1);
                    params.put("USERNAME",user1);
                    params.put("PASSWORD",pass1);
                    params.put("PASSWORDCONF",pass1);
                    return params;
                }
            };

            MySingleton.getInstance(SignUp.this).addToRequestQueue(stringRequest);

            Intent intent = new Intent(SignUp.this, Login.class);
            startActivity(intent);
        }
    }
}
