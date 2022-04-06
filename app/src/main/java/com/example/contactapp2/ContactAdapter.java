package com.example.contactapp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>
       implements Filterable{
    private ArrayList<Contact> contacts;
    private ArrayList<Contact> ContactsOld;
    private Context context;
    private ItemClickListener clickListener;

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

        byte[] byteArray = contacts.get(position).getImgAvatar();

        if(byteArray != null){
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            holder.ivAvatar.setImageBitmap(bmp);
        }

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

    public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }



    //Giu data cua 1 hang
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtName;
        public  ImageView ivAvatar;
        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
                clickListener.onClick(view, getAdapterPosition());
            }
        }
    }
}

