package com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hacunamatata.rikkeisoft_network_parser.R;
import com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.entity.User;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;
    private OnItemClickListener mClickListener;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    public void setClickListener(OnItemClickListener onItemClickListener) {
        this.mClickListener = onItemClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView, mClickListener);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvUserID.setText(String.valueOf(user.getUserId()));
        holder.tvTitle.setText(user.getTitle());
    }

    @Override
    public int getItemCount() {
        return (users != null ? users.size() : 0);
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvUserID;
        private TextView tvTitle;
        private OnItemClickListener onItemClickListner;

        public UserViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.tvUserID = itemView.findViewById(R.id.tv_userid);
            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.onItemClickListner = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListner.onItemClickListener(getAdapterPosition());
        }
    }
}
