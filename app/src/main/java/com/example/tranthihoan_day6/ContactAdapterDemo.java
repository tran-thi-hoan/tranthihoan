package com.example.tranthihoan_day6;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapterDemo extends RecyclerView.Adapter<ContactAdapterDemo.Viewhoder> {

    List<Contact> contacts;
    IonClickContact ionClickContact;
    Context context;
    SharedPreferences sharedPreferences;

    public void setIonClickContact(IonClickContact ionClickContact) {
        this.ionClickContact = ionClickContact;
    }

    public ContactAdapterDemo(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contact, parent, false);

        Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, final int position) {

        final Contact contact = contacts.get(position);

        holder.tvXuat.setText(contact.xuat);
        holder.tvName.setText(contact.getName());

        boolean fullMode =sharedPreferences.getBoolean("full_mode",true);

        if (fullMode) {
            holder.tvXuat.setVisibility(View.VISIBLE);
            holder.imgIcon.setVisibility(View.VISIBLE);
        }else {
            holder.tvXuat.setVisibility(View.GONE);
            holder.imgIcon.setVisibility(View.GONE);
        }

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickName(contact.getName(),position);
            }
        });


        holder.tvXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickPhone(contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvName, tvXuat;
        ImageView imgIcon;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvXuat = itemView.findViewById(R.id.tvXuat);
            imgIcon = itemView.findViewById(R.id.imgIcon);
        }
    }
}
