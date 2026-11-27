package com.exercise.groupexpensemanagement.ui.member;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.data.model.Expense;
import com.exercise.groupexpensemanagement.data.model.Members;
import com.exercise.groupexpensemanagement.ui.transaction_detail.ExpenseItemAdapter;

import java.util.Calendar;
import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    private List<Members> membersList;


    private OnMemberClickListener listener;

    public interface OnMemberClickListener {
        void onClick(Members members);
    }

    public MemberAdapter(List<Members> list, OnMemberClickListener listener){
        this.membersList = list;
        this.listener = listener;
    }

    public void setStudentList(List<Members> membersList) {
        this.membersList = membersList;
    }

    @NonNull
    @Override
    public MemberAdapter.MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_members, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.MemberViewHolder holder, int position) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Members members  = membersList.get(position);
        holder.tvName_holder.setText(members.getName());
        holder.tvAddress_holder.setText(members.getAddress());
        holder.tvPhone_holder.setText(members.getPhone());
        if(members.getImage() != null){
            Glide.with(holder.itemView.getContext()).load(Uri.parse(members.getImage())).circleCrop().into(holder.imgAvatar_holder);
        } else{
            holder.imgAvatar_holder.setImageResource(R.drawable.default_avatar);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(members);
            }
        });
    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgAvatar_holder;
        public TextView tvName_holder;
        public TextView tvAddress_holder;
        public TextView tvPhone_holder;
        public MemberViewHolder(View itemView) {
            super(itemView);
            imgAvatar_holder = itemView.findViewById(R.id.imgItemAvatar);
            tvName_holder = itemView.findViewById(R.id.tvItemName);
            tvAddress_holder = itemView.findViewById(R.id.tvItemAddress);
            tvPhone_holder = itemView.findViewById(R.id.tvItemPhone);
            imgAvatar_holder = itemView.findViewById(R.id.imgItemAvatar);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Members members);
    }







}
