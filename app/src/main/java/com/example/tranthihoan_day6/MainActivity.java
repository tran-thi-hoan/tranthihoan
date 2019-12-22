package com.example.tranthihoan_day6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Contact> contactList;
    RecyclerView recyclerView;
    ContactAdapterDemo contactAdapterDemo;
    View vAdd;
    int mPosition = -1;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvList);
        vAdd = findViewById(R.id.vAdd);
        contactList = new ArrayList<>();

        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = preferences.edit();


        contactList.add(new Contact("Cam ", "2", false));
        contactList.add(new Contact("Quýt", "3", true));
        contactList.add(new Contact("Mít", "4", false));
        contactList.add(new Contact("Dừa", "5", true));
        contactList.add(new Contact("Dưa", "6", false));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false);

        contactAdapterDemo = new ContactAdapterDemo(contactList, getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactAdapterDemo);

        contactAdapterDemo.setIonClickContact(new IonClickContact() {
            @Override
            public void onClickName(String name, int position) {
                mPosition = position;
                Intent intent = new Intent(getBaseContext(), AddNewContact.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, 113);
            }

            @Override
            public void onClickPhone(Contact contact) {

            }
        });

        vAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddNewContact.class);
                intent.putExtra("name", "");
                startActivityForResult(intent, 115);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnFull:
                editor.putBoolean("full_mode", true);
                editor.commit();
                contactAdapterDemo.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Menu Full", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnShort:
                editor.putBoolean("full_mode", false);
                editor.commit();
                contactAdapterDemo.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Menu Short", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_OK:
                String name = data.getStringExtra("nameAdd");

                if (requestCode == 113) {
                    contactList.set(mPosition, new Contact(name, "", false));
                    contactAdapterDemo.notifyDataSetChanged();

                } else if (requestCode == 115) {
                    contactList.add(new Contact(name, "", false));
                    contactAdapterDemo.notifyDataSetChanged();
                }

        }

    }
}

