package com.teamproject_recomm.lxl.teamapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.spark.submitbutton.SubmitButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email;
    private EditText password;
    private SubmitButton btLogin;
    private TextView rsgLink;
    private ProgressDialog progressDialog2;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            System.out.println("You have signed in");
            Toast.makeText(this,"Plase logout the current Accout first",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }

        email= (EditText) findViewById(R.id.etLUserName);
        password= (EditText) findViewById(R.id.etPassword);
        btLogin= (SubmitButton) findViewById(R.id.btLogin);
        rsgLink=(TextView)findViewById(R.id.toRegister);

        progressDialog2=new ProgressDialog(this);

        btLogin.setOnClickListener(this);
        rsgLink.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==btLogin){
            userLogin();

        }
        if(v==rsgLink){
            finish();
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        }

    }

    private void userLogin() {
        System.out.println(email);
        String emials=email.getText().toString().trim();
        String psw=password.getText().toString().trim();
        //Email addreess cannot be empty
        if(TextUtils.isEmpty(emials)){
            Toast.makeText(this,"Please enter the email address",Toast.LENGTH_SHORT).show();
            //stop the process from going further
            return;
        }
        //Email password1 cannot be empty
        if(TextUtils.isEmpty(psw)){
            Toast.makeText(this,"Please enter the password",Toast.LENGTH_SHORT).show();
            //stop the process from going further
            return;
        }
        System.out.println("before progress dialog");
//        progressDialog2.setMessage("wo meng zheng za shi fen nu li de jia zai ");
//        progressDialog2.show();

        firebaseAuth.signInWithEmailAndPassword(emials,psw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            System.out.println("you have singed in successfully");

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        progressDialog2.dismiss();

                    }
                });


    }

}
