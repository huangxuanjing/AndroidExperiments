package com.fjnu.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exp3_T1_ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exp3_t1_listview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("exp3_t1","exp3-t1通知", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        ConstraintLayout lionButton=findViewById(R.id.lion);
        lionButton.setOnLongClickListener(v -> {
            Toast.makeText(Exp3_T1_ListView.this,"Lion",Toast.LENGTH_SHORT).show();
            return true;
        });
        lionButton.setOnClickListener(v -> sendNotification("Lion"));
        
        ConstraintLayout tigerButton=findViewById(R.id.tiger);
        tigerButton.setOnLongClickListener(v -> {
            Toast.makeText(Exp3_T1_ListView.this,"Tiger",Toast.LENGTH_SHORT).show();
            return true;
        });
        tigerButton.setOnClickListener(v -> sendNotification("Tiger"));
        
        ConstraintLayout monkeyButton=findViewById(R.id.monkey);
        monkeyButton.setOnLongClickListener(v -> {
            Toast.makeText(Exp3_T1_ListView.this,"Monkey",Toast.LENGTH_SHORT).show();
            return true;
        });
        monkeyButton.setOnClickListener(v -> sendNotification("Monkey"));
        
        ConstraintLayout dogButton=findViewById(R.id.dog);
        dogButton.setOnLongClickListener(v -> {
            Toast.makeText(Exp3_T1_ListView.this,"Dog",Toast.LENGTH_SHORT).show();
            return true;
        });
        dogButton.setOnClickListener(v -> sendNotification("Dog"));
        
        ConstraintLayout catButton=findViewById(R.id.cat);
        catButton.setOnLongClickListener(v -> {
            Toast.makeText(Exp3_T1_ListView.this,"Cat",Toast.LENGTH_SHORT).show();
            return true;
        });
        catButton.setOnClickListener(v -> sendNotification("Cat"));
        
        ConstraintLayout elephantButton=findViewById(R.id.elephant);
        elephantButton.setOnLongClickListener(v -> {
            Toast.makeText(Exp3_T1_ListView.this,"Elephant",Toast.LENGTH_SHORT).show();
            return true;
        });
        elephantButton.setOnClickListener(v -> sendNotification("Elephant"));
    }

    private void sendNotification(String title){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"exp3_t1").setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setContentText("你点击了"+title+"按钮！");
        NotificationManager notificationManager=getSystemService(NotificationManager.class);
        notificationManager.notify(1,builder.build());
    }
}