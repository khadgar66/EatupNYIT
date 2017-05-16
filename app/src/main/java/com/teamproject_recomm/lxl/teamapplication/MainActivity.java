package com.teamproject_recomm.lxl.teamapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btSignIn;
    private Button btLogOut;

    private Button btmProf;

    private TextView welComeUser;
    private Button btShowInfo;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();



        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

        }
        FirebaseUser user=firebaseAuth.getCurrentUser();
        btSignIn=(Button) findViewById(R.id.btSign);
        btLogOut=(Button) findViewById(R.id.btlogOut);

        btmProf=(Button)findViewById(R.id.btProfile);
        welComeUser=(TextView) findViewById(R.id.edtWelcom);

        btShowInfo=(Button) findViewById(R.id.btshowUserInf);



        welComeUser.setText("Wellcome"+" "+user.getEmail());
        btSignIn.setOnClickListener(this);
        btLogOut.setOnClickListener(this);

        btmProf.setOnClickListener(this);
        btShowInfo.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v==btLogOut){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(v==btSignIn){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        //if  Map
        if(v==btmProf){
            finish();
            startActivity(new Intent(this,FavType.class));
        }
        if(v==btShowInfo){
            finish();
            startActivity(new Intent(this,RecommandInfoActivity.class));
        }

    }
}
