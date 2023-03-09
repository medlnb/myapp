package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText ageEditText;
    private EditText addressEditText;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        emailEditText = findViewById(R.id.email); passwordEditText = findViewById(R.id.password); ageEditText = findViewById(R.id.age); addressEditText = findViewById(R.id.address); signUpButton = findViewById(R.id.signup_button);

        signUpButton.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) { signUp();
        }
        });
    }
    private void signUp() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String address = addressEditText.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.74.162/signup.php", new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {
            if(response.trim().equals("success")){
                Toast.makeText(SignUpActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
        }
        }
        },
                new Response.ErrorListener() { @Override
                public void onErrorResponse(VolleyError error) { Toast.makeText(SignUpActivity.this, "Error: " + error.toString(), Toast
                        .LENGTH_SHORT).show();
                }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { Map<String, String> params = new HashMap<>(); params.put("email", email);
                params.put("password", password); params.put("age", age); params.put("address", address); return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this); requestQueue.add(stringRequest);


    }}

