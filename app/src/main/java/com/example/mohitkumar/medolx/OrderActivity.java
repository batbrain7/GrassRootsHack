package com.example.mohitkumar.medolx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class OrderActivity extends AppCompatActivity {

    EditText editText;
    String f,g,b,c,d,e,a,h;

    TextView textView1,textView2,textView3,textView4,textView5;
    String server_url = "http://139.59.20.254/medex/android/buybackend.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        editText = (EditText) findViewById(R.id.quant);

        textView1 = (TextView) findViewById(R.id.medname);
        textView2 = (TextView) findViewById(R.id.meddesc);
        textView3 = (TextView) findViewById(R.id.expirydate);
        textView4 = (TextView) findViewById(R.id.price);
        textView5 = (TextView)findViewById(R.id.final_text);

         a = getIntent().getStringExtra("Medicine");
         b = getIntent().getStringExtra("Desc");
        c = getIntent().getStringExtra("expdate");
         d = getIntent().getStringExtra("quantity");
         e = getIntent().getStringExtra("price");
        h = getIntent().getStringExtra("usrname");

        textView1.setText(a);
        textView2.setText(b);
        textView3.setText("Expiry date : "  +  c);
        textView4.setText("Price of the medicine per unit is  :" + e);

        SharedPreferences sharedPreferences = getSharedPreferences("UserName", Context.MODE_PRIVATE);

         f = sharedPreferences.getString("UserName", "");


        if(a!=null) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,server_url, (String) null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("TAG","");
                                g = response.getString("address");
                                //barcode = response.getString("Barcode");
                                //textname.setText(response.getString("Name"));
                                //textroll.setText(response.getString("Roll_No"));

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
            MySingleton.getInstance(OrderActivity.this).addToRequestQueue(jsonObjectRequest);

        }
    }


    public void BUY(View view) {
        Integer n = Integer.parseInt(d);

        String entered = editText.getText().toString();

        Integer n1 = Integer.parseInt(entered);

        if(n1>n||n1<=0) {
            Toast.makeText(getApplicationContext(),"Please enter a valid number",Toast.LENGTH_LONG).show();
        } else {
            textView1.setVisibility(View.GONE);
            textView2.setVisibility(View.GONE);
            textView3.setVisibility(View.GONE);
            textView4.setVisibility(View.GONE);
            editText.setVisibility(View.GONE);
            textView5.setText("Your order is placed please collect it from Nikhil Pandey at " + g);
            Toast.makeText(getApplicationContext(),"Your order is placed please collect it from  " + h + "  "+ " at " +  g,Toast.LENGTH_LONG).show();
        }

    }

}
