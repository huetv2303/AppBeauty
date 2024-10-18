package com.example.appbeauty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddressActivity extends AppCompatActivity {
    private LinearLayout btn_address;
    private ImageView btnback;
    private ListView lv_address;

    public void initializeUI() {
        btn_address = findViewById(R.id.btn_address);
        lv_address = findViewById(R.id.lv_address);
        btnback = findViewById(R.id.btnback);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        initializeUI();
        InsertAddress();
        back();
    }

    public void back(){
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void InsertAddress() {
        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
                builder.setTitle("Thêm địa chỉ mới");// Tiêu đề


                LinearLayout layout = new LinearLayout(AddressActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(16, 16, 16, 16);


                // Thiết lập LayoutParams cho các EditText
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 30, 30); // Thay đổi khoảng cách ở đây (top, left, bottom, right)


                // Tạo EditText cho tên người nhận
                final EditText name = new EditText(AddressActivity.this);
                name.setHint("Nhập tên người nhận");
                name.setLayoutParams(params);
                layout.addView(name);

                // Tạo EditText cho số điện thoại
                final EditText phone = new EditText(AddressActivity.this);
                phone.setHint("Nhập số điện thoại");
                phone.setInputType(InputType.TYPE_CLASS_PHONE);// Chỉ cho phép nhập số điện thoại
                phone.setLayoutParams(params);
                layout.addView(phone);



                // Tạo EditText cho địa chỉ
                final EditText building = new EditText(AddressActivity.this);
                building.setHint("Tòa nhà, số tầng...");
                building.setLayoutParams(params);
                layout.addView(building);

                // Tạo EditText cho cổng
                final EditText gate = new EditText(AddressActivity.this);
                gate.setHint("Cổng(không bắt buộc)");
                building.setLayoutParams(params);
                layout.addView(gate);

                final RadioGroup radioGroup = new RadioGroup(AddressActivity.this);
                radioGroup.setLayoutParams(params);
                layout.addView(radioGroup); // Thêm RadioGroup vào layout

                // Tạo CheckBox cho Home
                final RadioButton rdHome = new RadioButton(AddressActivity.this);
                rdHome.setText("Home");
                radioGroup.addView(rdHome);

                // Tạo RadioButton cho Work
                final RadioButton rdWork = new RadioButton(AddressActivity.this);
                rdWork.setText("Work");
                radioGroup.addView(rdWork);


                final EditText note = new EditText(AddressActivity.this);
                note.setHint("Ghi chú (nếu có)");
                note.setLayoutParams(params);
                layout.addView(note);


                // Thiết lập layout cho dialog
                builder.setView(layout);

                builder.setPositiveButton("Ok", (dialog, which) -> {
                    String recipient = name.getText().toString();
                    String numberphone = phone.getText().toString();
                    String address = building.getText().toString();
                    String gates  = gate.getText().toString();
                    int typeAddress = radioGroup.getCheckedRadioButtonId();
                    String notes = note.getText().toString();
                    String type = "";

                    //radio
                    if(typeAddress == rdHome.getId()){
                        type = "Home";
                    }else type = "Work";



                });

                 builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());


                // Tạo và hiển thị dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
