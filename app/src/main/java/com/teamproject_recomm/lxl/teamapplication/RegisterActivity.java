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
import com.google.firebase.auth.FirebaseUser;
import com.spark.submitbutton.SubmitButton;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText etUsername;
    private  EditText etPassword;
    private  EditText etpassword2;
    private SubmitButton btRegister;
    private TextView linkToLOgin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            System.out.println("You have signed in");
            Toast.makeText(this,"u need logout first",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }

        ProgressDialog progressDialog= new ProgressDialog(this);

        etUsername= (EditText) findViewById(R.id.etRUserName);
        etPassword= (EditText) findViewById(R.id.etPassword1);
        etpassword2= (EditText) findViewById(R.id.etPassword2);
        btRegister= (SubmitButton) findViewById(R.id.btRegister);
        linkToLOgin=(TextView) findViewById(R.id.ToLogin_textView);

        btRegister.setOnClickListener(this);
        linkToLOgin.setOnClickListener(this);


    }
    private void registerUser() {
        String email=etUsername.getText().toString().trim();
        String pswd1=etPassword.getText().toString().trim();
        String pswd2=etpassword2.getText().toString().trim();

        //Email addreess cannot be empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter the email address",Toast.LENGTH_SHORT).show();
            //stop the process from going further
            return;
        }
        //Email password1 cannot be empty
        if(TextUtils.isEmpty(pswd1)){
            Toast.makeText(this,"Please enter the password",Toast.LENGTH_SHORT).show();
            //stop the process from going further
            return;
        }
        //Email password2 cannot be empty
        if(TextUtils.isEmpty(pswd2)){
            Toast.makeText(this,"re_enter password canot be empty",Toast.LENGTH_SHORT).show();
            //stop the process from going further
            return;
        }
        //Email addreess cannot be empty
        if(!TextUtils.equals(pswd1,pswd2)){
            Toast.makeText(this,"different pass word input!",Toast.LENGTH_SHORT).show();
            //stop the process from going further
            return;
        }
//        progressDialog.setMessage("Register user.....Register user.....Register user.....");
//        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pswd1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    // to see the registratio is seccess or not
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // user is registered and loged in
                           // Toast.makeText(RegisterActivity.this, "zhu ce cheng gong le", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "zhu ce shi bai le", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(v==btRegister){
            registerUser();
        }
        if(v==linkToLOgin){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }


    }





}
