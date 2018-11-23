 /*
        GigaIotOAuth gigaIotOAuth = new GigaIotOAuth("hZswE6VAw5l1mUo1","5xSIACByyNzU8XMY");
        GiGaIotOAuthResponse response = gigaIotOAuth.loginWithPassword("sereng5436", "sss25867@");
        token = response.getAccessToken();
        System.out.println(token+"Access token 확인");

        DeviceApi deviceApi = new DeviceApi(token);
        DeviceApiResponse deviceApiResponse = deviceApi.getDeviceList(1,1);
        temp = deviceApiResponse.getResponseCode();
        System.out.println(temp+"디바이스 응답 코드");

        TagStrmApi tagStrmApi = new TagStrmApi(token);
        TagStrmApiResponse tagStrmApiResponse = tagStrmApi.getTagStrmList("Arduino_uno");
        temp = tagStrmApiResponse.getResponseCode();
        System.out.println(temp+ "태그스트림 응답 코드");

        ArrayList<TagStrm> tagstrmslist = tagStrmApiResponse.getTagStrms();
        ArrayList<Log> tagstrmsLog = tagStrmApiResponse.getLogs();
        }
        */
 package com.wenikids.knuprime110.wenikids;

 import android.os.AsyncTask;
 import android.content.Intent;
 import android.app.ProgressDialog;
 import android.os.Bundle;
 import android.support.annotation.NonNull;
 import android.support.design.widget.BottomNavigationView;
 import android.support.v7.app.AppCompatActivity;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.ImageButton;
 import android.widget.TextView;
 import android.widget.Toast;
 import java.util.ArrayList;
 import java.io.IOException;

 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.auth.FirebaseUser;
 import com.google.android.gms.gcm.GoogleCloudMessaging;

 public class MainActivity extends AppCompatActivity {
     //GCM 등록키,사용자 일련번호
     private String AregId,mbrSeq;
     //이메일 비밀번호 로그인 모듈 변수
     private FirebaseAuth mAuth;
     //현재 로그인 된 유저 정보를 담을 변수
     private FirebaseUser currentUser;

     private TextView mTextMessage;
     private GoogleCloudMessaging mGcm;

     private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
             = new BottomNavigationView.OnNavigationItemSelectedListener() {

         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {

             switch (item.getItemId()) {
                 case R.id.my_page:
                     Toast.makeText(MainActivity.this, currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                     return true;
                 case R.id.GPS:
                     startActivity(new Intent(MainActivity.this, GpsActivity.class));
                     return true;
                 case R.id.my_list:
                     startActivity(new Intent(MainActivity.this, StudentActivity.class));
                     return true;
             }
             return false;
         }

     };

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         mAuth = FirebaseAuth.getInstance();
         //mTextMessage = (TextView) findViewById(R.id.message);

         BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
         navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


         ImageButton logoutBtn = (ImageButton) findViewById(R.id.LogoutButton);
         logoutBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //로그아웃
                 signOut();
             }
         });

     }


     @Override
     public void onStart() {
         super.onStart();
         // Check if user is signed in (non-null) and update UI accordingly.
         currentUser = mAuth.getCurrentUser();
         if (currentUser == null) {
             startActivity(new Intent(MainActivity.this, LoginActivity.class));
             finish();
         }
     }

     public void signOut() {
         FirebaseAuth.getInstance().signOut();
         startActivity(new Intent(MainActivity.this, LoginActivity.class));
         finish();
     }

 }

