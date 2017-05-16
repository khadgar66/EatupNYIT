package com.teamproject_recomm.lxl.teamapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecommandInfoActivity extends AppCompatActivity {
    List<Users> usersList=new ArrayList<Users>();
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mfirebaseAuth;
    private String CurrentuserEmail;
    private TextView simiUserTextView;
    private TextView cnRateRecommTextView;
    private TextView cnFreRecommTextView;
    private TextView jpRateRecommTextView;
    private TextView jpFreRecommTextView;
    private TextView indRateRecommTextView;
    private TextView indFreRecommTextView;
    private TextView mexRateRecommTextView;
    private TextView mexFreRecommTextView;
    final static String MOST_lIKE="5";
    final static String LIKE="4";
    final static String NORMAL="3";
    final static String DISKLIKE="2";
    final static String HATE="1";


    private Button btRecomm;
    private Button btMap;
    private UserInfoManagment showSimi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand_info);

        simiUserTextView=(TextView)findViewById(R.id.SimiUser_textView);
        cnRateRecommTextView=(TextView) findViewById(R.id.RecommCNRate_textView);
        cnFreRecommTextView=(TextView) findViewById(R.id.RecommCN_frequency_textView);
        jpRateRecommTextView=(TextView) findViewById(R.id.RecommJPRate_textView);
        jpFreRecommTextView=(TextView) findViewById(R.id.RecommJP_frequency_textView);
        indRateRecommTextView=(TextView) findViewById(R.id.RecommINRate_textView);
        indFreRecommTextView=(TextView)findViewById(R.id.RecommIN_frequency_textView);
        mexRateRecommTextView=(TextView)findViewById(R.id.RecommMXRate_textView);
        mexFreRecommTextView=(TextView)findViewById(R.id.RecommMX_frequency_textView);

        btRecomm=(Button)findViewById(R.id.recommand_Bt);
        btMap=(Button)findViewById(R.id.btMap);


        mfirebaseAuth=FirebaseAuth.getInstance();
        CurrentuserEmail=mfirebaseAuth.getCurrentUser().getEmail();


        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        databaseReference.child("usersRates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                for(DataSnapshot child:children){
                    Users user=child.getValue(Users.class);
                    System.out.println(user.getEmail());
                    System.out.println(user.getUserId());
                    //jia da list li mian
                    usersList.add(user);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btRecomm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String temEmail=OnshowSimilarUser();
                Users temUser=showSimi.getRecommandUserInfor(usersList,temEmail);
                String cnRate=temUser.getCnRate();
                String jpRate=temUser.getJpRate();
                String indRate=temUser.getIndRate();
                String mexRate=temUser.getMexRate();
                String uEmail=temUser.getEmail();
                String cnFre=temUser.getCNFrequency();
                String jpFre=temUser.getJPFrequency();
                String indFre=temUser.getINDFrequency();
                String mexFre=temUser.getMEXFrequency();

                simiUserTextView.setText("Emial="+uEmail);
                cnRateRecommTextView.setText(Switchor(cnRate)+"  Chinese food");
                Keyword.SEARCH_KEY_WORD="";
                if(Double.parseDouble(cnRate)>2){

                    Keyword.SEARCH_KEY_WORD="&Chinese";
                }
                cnFreRecommTextView.setText("CN_Frequency="+cnFre+" times last month");
                jpRateRecommTextView.setText(Switchor(jpRate)+" Japanese food");
                if(Double.parseDouble(jpRate)>2){

                    Keyword.SEARCH_KEY_WORD= Keyword.SEARCH_KEY_WORD+"&Japanese";
                }
                jpFreRecommTextView.setText("JP_Frequency="+jpFre+" times last month");
                indRateRecommTextView.setText(Switchor(indRate)+" Indian food");
                if(Double.parseDouble(indRate)>2){

                    Keyword.SEARCH_KEY_WORD= Keyword.SEARCH_KEY_WORD+"&Indian";
                }
                indFreRecommTextView.setText("IND_Frequency="+indFre+" times last month");
                mexRateRecommTextView.setText(Switchor(mexRate)+" Mexico food");
                if(Double.parseDouble(mexRate)>2){

                    Keyword.SEARCH_KEY_WORD= Keyword.SEARCH_KEY_WORD+"&Mexican";
                }
                mexFreRecommTextView.setText("MEX_Frequency="+mexFre+" times last month");



            }
        });
        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });

    }
    public String OnshowSimilarUser(){
        showSimi=new UserInfoManagment(CurrentuserEmail,usersList);
        showSimi.newListWithoutCurrentUser();
        showSimi.CosimMap();
        return showSimi.simiUser();

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
