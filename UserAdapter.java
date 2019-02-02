package com.example.deepbeast.androidtest;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deepbeast.androidtest.database.model.UserItem;

import java.io.File;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<UserItem> userItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPic;
        public TextView tvName;
        public TextView tvPassword;

        public MyViewHolder(View view) {
            super(view);
            ivPic = view.findViewById(R.id.ivPic);
            tvName = view.findViewById(R.id.tvName);
            tvPassword = view.findViewById(R.id.tvPassword);
        }
    }


    public UserAdapter(Context context, List<UserItem> userItems) {
        this.context = context;
        this.userItemList = userItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserItem userItem = userItemList.get(position);

        holder.tvName.setText(userItem.getUserName());
        holder.tvPassword.setText(userItem.getPasscode()+"");
        File file = new File(userItem.getProfile_pic());
        Uri imageUri = Uri.fromFile(file);
        Glide.with(context).load(imageUri).into(holder.ivPic);

    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }

}