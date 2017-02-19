package com.example.mohitkumar.medolx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
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

public class SellMedicine extends AppCompatActivity {


    String server_url = "http://139.59.20.254/medex/android/sellbackend.php";
    EditText name,descript,quantity,expiry,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_medicine);

        name = (EditText)findViewById(R.id.drug_name);
        descript = (EditText)findViewById(R.id.drug_desc);
        quantity = (EditText)findViewById(R.id.drug_quantity);
        expiry = (EditText)findViewById(R.id.drug_expiry);
        price = (EditText)findViewById(R.id.drug_price);


        ImageView imageView = (ImageView)findViewById(R.id.image1);
        imageView.setImageResource(R.drawable.medexlogo);
        android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        Display display = getWindowManager().getDefaultDisplay();
        layoutParams.height = (int) (display.getHeight()*0.20);
        layoutParams.width = display.getWidth();
        imageView.setLayoutParams(layoutParams);
    }

    public void SubmitDet(View view){

        final String dname,desc,quant,expire,pric;

        dname = name.getText().toString();
        desc = descript.getText().toString();
        quant = quantity.getText().toString();
        expire = expiry.getText().toString();
        pric = price.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("UserName", Context.MODE_PRIVATE);

        final String a = sharedPreferences.getString("UserName","");

        if(dname.equals("")||desc.equals("")||quant.equals("")||expire.equals("")||pric.equals("")) {
            Toast.makeText(SellMedicine.this,"Enter all the details",Toast.LENGTH_LONG).show();
        }
        else {
            ProgressDialog progressDialog = new ProgressDialog(SellMedicine.this);
            progressDialog.setTitle("Wait..");
            progressDialog.setMessage("Adding you to our network");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,server_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    Log.d("In gere","In here");

                    params.put("MEDNAME",dname);
                    params.put("MEDDESC",desc);
                    params.put("EXPDATE",expire);
                    params.put("QUANTITY",quant);
                    params.put("PRICE",pric);
                    params.put("userkey",a);
                    return params;
                }
            };

            MySingleton.getInstance(SellMedicine.this).addToRequestQueue(stringRequest);

            Intent intent = new Intent(SellMedicine.this,PersonalActivity.class);

        }

    }
}
