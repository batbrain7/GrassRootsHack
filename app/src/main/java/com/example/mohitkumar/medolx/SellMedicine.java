package com.example.mohitkumar.medolx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SellMedicine extends AppCompatActivity {


    EditText descript,dose,quantity,expiry,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_medicine);

        descript = (EditText)findViewById(R.id.drug_desc);
        dose = (EditText)findViewById(R.id.drug_dosage);
        quantity = (EditText)findViewById(R.id.drug_quantity);
        expiry = (EditText)findViewById(R.id.drug_expiry);
        price = (EditText)findViewById(R.id.drug_price);
    }

    public void SubmitDet(View view){

        String desc,dos,quant,expire,pric;

        desc = descript.getText().toString();
        dos = dose.getText().toString();
        quant = quantity.getText().toString();
        expire = expiry.getText().toString();
        pric = price.getText().toString();

        if(desc.equals("")||dos.equals("")||quant.equals("")||expire.equals("")||pric.equals("")) {
            Toast.makeText(SellMedicine.this,"Enter all the details",Toast.LENGTH_LONG).show();
        }
        else {
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(),"Slow Internet !!!",Toast.LENGTH_LONG).show();
//                }
//            }){
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String,String> params = new HashMap<String, String>();
//                    Log.d("In gere","In here");
//
//                    params.put("queryno",s);
//                    params.put("firno",firnum);
//                    params.put("district",dist);
//                    params.put("station",stations);
//                    params.put("officer",officer);
//                    params.put("mobile",Long.toString(mob1));
//                    params.put("query",qiuery);
//                    params.put("date",datee.toString());
//                    params.put("time",time);
//                    params.put("cname",name1);
//                    params.put("cmob",mob);
//                    params.put("querystat","No");
//                    params.put("escio","IO123");
//                    params.put("escsho","SHO123");
//                    params.put("escdcp","DCP123");
//                    params.put("escacp","ACP123");
//                    return params;
//                }
//            };
//
//            MySingleton.getInstance(QueryActivity.this).addToRequestQueue(stringRequest);

        }

    }
}
