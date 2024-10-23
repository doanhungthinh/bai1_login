package com.example.bai1_login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText edtUser, edtpass;
    CheckBox cbgn;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        sharedPreferences = getSharedPreferences("datalogin", MODE_PRIVATE);

        Anhxa();
        edtUser.setText(sharedPreferences.getString("taikhoan",""));
        edtUser.setText(sharedPreferences.getString("matkhau",""));
        cbgn.setChecked(sharedPreferences.getBoolean("check",false));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtUser.getText().toString().trim();
                String pass = edtpass.getText().toString().trim();
                if (name.equals("hungthinh") && pass.equals("123"))
                {
                    Toast.makeText(MainActivity.this,"dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                    if(cbgn.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan",name);
                        editor.putString("matkhau ",pass);
                        editor.putBoolean("check",true);
                        editor.commit();
                    }
                    else{
                        SharedPreferences.Editor editor =sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("check");
                        editor.commit();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"that bai",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Anhxa(){
        button = (Button) findViewById(R.id.button);
        edtUser = (EditText) findViewById(R.id.editTextText);
        edtpass = (EditText) findViewById(R.id.editTextText2);
        cbgn  = (CheckBox) findViewById(R.id.checkBox);
    };


}