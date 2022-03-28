package com.example.contactapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>
       implements Filterable {
    private ArrayList<Contact> contacts;
    private ArrayList<Contact> ContactsOld;
    private Context context;

    public ContactAdapter(ArrayList<Contact> ContactsOld) {
        this.contacts = ContactsOld;
        this.ContactsOld = ContactsOld;
    }

    public ContactAdapter(Context context){
        this.context = context;
    }

//    public void setContactList(List<Contact> contactList){
//        this.contacts = (ArrayList<Contact>) contactList;
//        this.ContactsOld = (ArrayList<Contact>) contactList;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
//        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(contacts.get(position).getName());
//        holder.txtPhone.setText(this.ContactsOld.get(position).getPhone());
//        holder.txtEmail.setText(this.ContactsOld.get(position).getEmail());
    }

    @Override
    public int getItemCount() {

        return contacts.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();
                if(search.isEmpty()){
                    contacts = ContactsOld;
                }else {
                    ArrayList<Contact> list = new ArrayList<>();
                    for (Contact contact : ContactsOld) {
                        if(contact.getName().toLowerCase().contains(search.toLowerCase())){
                            list.add(contact);
                        }
                    }
                    contacts = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contacts;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contacts = (ArrayList<Contact>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    //Giu data cua 1 hang
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public  ImageView ivAvatar;
        public ViewHolder(View view) {
            super(view);

            txtName = view.findViewById(R.id.txt_name);
            ivAvatar = view.findViewById(R.id.iv_avatar);
        }
    }
}

