package com.example.tranthihoan_day6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewContact extends AppCompatActivity {
    String mName, mXuat;
    EditText etName, etXuat;
    Button btnAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        etName = findViewById(R.id.etName);
        etXuat = findViewById(R.id.etXuat);
        btnAction = findViewById(R.id.btnAction);

        final Intent intent = getIntent();

        mName = intent.getStringExtra("name");

        if (mName.equals("")) btnAction.setText("Add");
        else {
            btnAction.setText("Update");
            etName.setText(mName);
        }

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("nameAdd", etName.getText().toString());
                setResult(RESULT_OK, intent1);
                finish();
            }
        });


    }
}
