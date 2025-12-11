package com.fjnu.myapplication;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Exp3_T4_ActionModeMenu extends AppCompatActivity {

    private ListView listView;
    private Button deleteButton;
    private TextView actionModeTitle;
    private View menuTitle;
    private ArrayAdapter<String> adapter;
    private List<Integer> selectedPositions;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exp3_t4_actionmodemenu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu_title), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        // 初始化控件
        listView = findViewById(R.id.action_mode_list);
        deleteButton = findViewById(R.id.delete_button);
        actionModeTitle = findViewById(R.id.action_mode_title);
        menuTitle = findViewById(R.id.menu_title);
        selectedPositions = new ArrayList<>();

        // 创建按钮文本数组
        String[] buttonTexts = {"One", "Two", "Three", "Four", "Five"};

        // 创建自定义ArrayAdapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, buttonTexts) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Button button;
                if (convertView == null) {
                    // 直接创建Button
                    button = new Button(getContext());
                    button.setTextSize(18);
                    button.setTextColor(getContext().getResources().getColor(android.R.color.black));
                    button.setBackground(getContext().getResources().getDrawable(R.drawable.exp3_t4_button_selector));
                    button.setPadding(16, 16, 16, 16);
                    button.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.WRAP_CONTENT));
                } else {
                    // 重用已有的Button
                    button = (Button) convertView;
                }
                button.setText(getItem(position));

                // 设置选中状态
                if (selectedPositions.contains(position)) {
                    button.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    button.setTextColor(getResources().getColor(android.R.color.white));
                } else {
                    button.setBackground(getContext().getResources().getDrawable(R.drawable.exp3_t4_button_selector));
                    button.setTextColor(getContext().getResources().getColor(android.R.color.black));
                }

                // 设置长按监听
                final int pos = position;
                button.setOnLongClickListener(v -> {
                    if (actionMode == null) {
                        // 开始ActionMode
                        startActionMode();
                    }
                    // 切换选中状态
                    toggleSelection(pos, button);
                    return true;
                });

                return button;
            }
        };

        // 设置ListView的适配器
        listView.setAdapter(adapter);

        // 设置删除按钮点击事件
        deleteButton.setOnClickListener(v -> deleteSelectedItems());
    }

    // 开始ActionMode
    private void startActionMode() {
        // 更改标题栏颜色
        actionModeTitle.setBackgroundColor(getResources().getColor(R.color.exp3_t4_button_pressed));
        // 显示删除按钮
        deleteButton.setVisibility(View.VISIBLE);
    }

    // 结束ActionMode
    private void endActionMode() {
        // 恢复标题栏颜色
        menuTitle.setBackgroundColor(Color.TRANSPARENT);
        actionModeTitle.setBackgroundColor(getResources().getColor(R.color.exp3_t4_title_initial));
        // 隐藏删除按钮
        deleteButton.setVisibility(View.GONE);
        // 清空选中状态
        selectedPositions.clear();
        // 更新标题
        actionModeTitle.setText("ActionMode");
        // 刷新ListView
        adapter.notifyDataSetChanged();
    }

    // 切换选中状态
    private void toggleSelection(int position, Button button) {
        if (selectedPositions.contains(position)) {
            // 取消选中
            selectedPositions.remove(Integer.valueOf(position));
            button.setBackground(getResources().getDrawable(R.drawable.exp3_t4_button_selector));
            button.setTextColor(getResources().getColor(android.R.color.black));
        } else {
            // 选中
            selectedPositions.add(position);
            button.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            button.setTextColor(getResources().getColor(android.R.color.white));
        }

        // 更新标题
        if (selectedPositions.isEmpty()) {
            // 没有选中项，结束ActionMode
            endActionMode();
        } else {
            // 更新选中数量
            actionModeTitle.setText(selectedPositions.size() + " selected");
        }
    }

    // 删除选中项
    private void deleteSelectedItems() {
        // 实现删除逻辑（这里只是模拟，实际应用中需要从数据源中删除）
        // 清空选中状态
        endActionMode();
    }
}