package com.eminent.a2019.chatui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eminent.a2019.chatui.databinding.ChatItemHorizontalBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.horizontalRecyclerViewAdapter> {

    private ArrayList<MessageModel> messageModelList;
    private MainActivity activity ;
    String currentTime ;

    HorizontalRecyclerViewAdapter(ArrayList<MessageModel> messageModelList, MainActivity activity) {
        this.messageModelList = messageModelList;
        this.activity = activity;
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    }

    @NonNull
    @Override
    public horizontalRecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatItemHorizontalBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.chat_item_horizontal,parent,false);
        return new HorizontalRecyclerViewAdapter.horizontalRecyclerViewAdapter(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRecyclerViewAdapter.horizontalRecyclerViewAdapter holder, int position) {
        final MessageModel messageModel =  messageModelList.get(position);
        Log.i("onBindViewHolder","true");
        holder.binding.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick","Ok");
                activity.sendMessage(messageModel.getMessage(),new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
            }
        });
        holder.bind(messageModel);
    }

    @Override
    public int getItemCount() {
        if (messageModelList != null) {
            return messageModelList.size();
        } else {
            return 0;
        }
    }

    class horizontalRecyclerViewAdapter extends RecyclerView.ViewHolder {
        ChatItemHorizontalBinding binding;
        horizontalRecyclerViewAdapter(@NonNull ChatItemHorizontalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Object obj) {
            binding.setVariable(BR.Model, obj);
            binding.executePendingBindings();
        }
    }
}
