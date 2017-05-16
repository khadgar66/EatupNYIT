package com.teamproject_recomm.lxl.teamapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spark.submitbutton.SubmitButton;

public class FavType extends AppCompatActivity implements View.OnClickListener{
    private Spinner Cnspinner;
    private Spinner Jpspinner;
    private Spinner Indspinner;
    private Spinner Mexspinner;
    private SubmitButton btSubmit;
    private TextView texTypes;
    private TextView CnTextView;
    private TextView JpTextView;
    private TextView IndTextView;
    private TextView MexTextView;
    private EditText cnFrequency;
    private EditText jpFrequency;
    private EditText indFrequency;
    private EditText mexFrequency;
    private DatabaseReference mDataBaseReference;
    private FirebaseDatabase mdatabase;
    private FirebaseAuth mfirebaseAuth;
    private String Email;
    private String UID;
    final static String MOST_lIKE="5";
    final static String LIKE="4";
    final static String NORMAL="3";
    final static String DISKLIKE="2";
    final static String HATE="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_type);
        //get the current user Email add
//        mfirebaseAuth=FirebaseAuth.getInstance();
//        String email=mfirebaseAuth.getCurrentUser().getEmail();

        //get the reference of the userRates
        mfirebaseAuth=FirebaseAuth.getInstance();
        Email=mfirebaseAuth.getCurrentUser().getEmail();
        UID=mfirebaseAuth.getCurrentUser().getUid();

        mdatabase=FirebaseDatabase.getInstance();
        mDataBaseReference= mdatabase.getReference("usersRates");

        //get views
        texTypes= (TextView) findViewById(R.id.FavType_text);
        CnTextView=(TextView) findViewById(R.id.Chinese_type_textView);
        JpTextView=(TextView) findViewById(R.id.Japanese_type_Textview);
        IndTextView=(TextView) findViewById(R.id.India_type_textView);
        MexTextView=(TextView) findViewById(R.id.Mexico_type_textView);
        Cnspinner=(Spinner) findViewById(R.id.ChineseTypeSpin);
        Jpspinner=(Spinner) findViewById(R.id.JapaneseTypeSpin);
        Indspinner=(Spinner) findViewById(R.id.IndiaTypeSpin);
        Mexspinner=(Spinner) findViewById(R.id.MexicoTypeSpin);
        cnFrequency=(EditText) findViewById(R.id.CN_Frequency_EditTextView);
        jpFrequency=(EditText) findViewById(R.id.JP_Frequency_EditTextView);
        indFrequency=(EditText) findViewById(R.id.IND_Frequency_EditTextView);
        mexFrequency=(EditText) findViewById(R.id.MX_Frequency_EditTextView);
        btSubmit=(SubmitButton) findViewById(R.id.favType_submit_bt);

        btSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==btSubmit){
            submit_user_rates();
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

    private void submit_user_rates() {
//        String userEmail=mfirebaseAuth.getCurrentUser().getEmail();
//        String userId=mfirebaseAuth.getCurrentUser().getUid();
        String cnRate= Cnspinner.getSelectedItem().toString().trim();
        String jpRate=Jpspinner.getSelectedItem().toString().trim();
        String indRate=Indspinner.getSelectedItem().toString().trim();
        String mexRate=Mexspinner.getSelectedItem().toString().trim();
        String cnFre=cnFrequency.getText().toString().trim();
        String jpFre=jpFrequency.getText().toString().trim();
        String indFre=indFrequency.getText().toString().trim();
        String mexFre=mexFrequency.getText().toString().trim();
        Users user=new Users(Email,UID,Switchor(cnRate),Switchor(jpRate),Switchor(indRate)
                ,Switchor(mexRate),cnFre,jpFre,indFre,mexFre);
        mDataBaseReference.child(UID).setValue(user)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(FavType.this,"your rate have been uploaded",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(FavType.this,"fail to upload your rate, please try again later",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }

                    }
                });

    }
    public static String Switchor(String str) {
        String output="";
        switch(str){
            case "Neither Like Nor Dislike": output=HATE;
                break;
            case "Slightly Like": output=DISKLIKE;
                break;
            case "Moderately Like": output=NORMAL;
                break;
            case "Very Much Like": output=LIKE;
                break;
            case "Extremely Like": output=MOST_lIKE;
                break;
            case "1":output="Neither Like Nor Dislike";
                break;
            case "2":output="Slightly Like";
                break;
            case "3":output="Moderately Like";
                break;
            case "4":output="Very Much Like";
                break;
            case "5":output="Extremely Like";
                break;
        }
        return output;
    }

}

