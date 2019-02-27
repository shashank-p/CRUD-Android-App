package com.shashank.crud;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText uid, name, address, phone;
    String Uid, Name, Address, Phone;
    Button button;
    Boolean valid = true;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uid = (EditText) findViewById(R.id.uid);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        progressDialog = new ProgressDialog(this);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uid = uid.getText().toString();
                Name = name.getText().toString();
                Address = address.getText().toString();
                Phone = phone.getText().toString();

                if(TextUtils.isEmpty(Uid)){
                    uid.setError("UID Cannot be Empty");
                    valid = false;
                }else {
                    valid = true;

                    if(TextUtils.isEmpty(Name)){
                        name.setError("Name Cannot be Empty");
                        valid = false;
                    }else {
                        valid = true;

                        if(TextUtils.isEmpty(Address)){
                            address.setError("Address Cannot be Empty");
                            valid = false;
                        }else {
                            valid = true;

                            if(TextUtils.isEmpty(Phone)){
                                phone.setError("Contact Number Cannot be Empty");
                                valid = false;
                            }else {
                                valid = true;
                            }
                        }

                    }
                }

                if(valid){
                    progressDialog.setMessage("Loading");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ADD, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                if(jsonObject.getString("message").equals("Data Added Successfully")){
                                    ListActivity.ma.refresh_list();
                                    finish();
                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(MainActivity.this, "Failed to Data",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        protected Map<String , String> getParams() throws AuthFailureError {
                            Map<String , String> params = new HashMap<>();
                            params.put("name", Name);
                            params.put("uid", Uid);
                            params.put("phone", Phone);
                            params.put("address",Address);
                            return params;
                        }
                    };
                    RequestHandler.getInstance(MainActivity.this).addToRequestQueue(stringRequest);

                }
            }
        });



    }


}
