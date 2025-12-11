package com.fjnu.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exp3_T3_XMLMenu extends AppCompatActivity {

    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exp3_t3_xmlmenu);
        testText = findViewById(R.id.test_text);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu_title), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AppCompatButton menuButton = findViewById(R.id.menu_title_button);
        menuButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(Exp3_T3_XMLMenu.this, v);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.exp3_t3_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.font_size_small) {
                    testText.setTextSize(10);
                    return true;
                } else if (itemId == R.id.font_size_medium) {
                    testText.setTextSize(16);
                    return true;
                } else if (itemId == R.id.font_size_large) {
                    testText.setTextSize(20);
                    return true;
                }
                else if (itemId == R.id.normal_item) {
                    Toast.makeText(Exp3_T3_XMLMenu.this, "这是一个Toast提示", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (itemId == R.id.font_color_red) {
                    testText.setTextColor(Color.RED);
                    return true;
                } else if (itemId == R.id.font_color_black) {
                    testText.setTextColor(Color.BLACK);
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });
    }
}