package com.eminent.a2019.chatui;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.eminent.a2019.chatui.databinding.ChatItemSelfBinding;
import com.eminent.a2019.chatui.databinding.ChatItemWatsonBinding;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    ArrayList<MessageModel> messageModelList;

    public RecyclerViewAdapter( ArrayList<MessageModel> messageModelList) {
        this.messageModelList = messageModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // view type is to identify where to render the chat message
        // left or right
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            // self message
            ChatItemSelfBinding binding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.chat_item_self,parent,false);

            return new SentMessageHolder(binding);

        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            // WatBot message
            ChatItemWatsonBinding binding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.chat_item_watson,parent,false);

            return new ReceivedMessageHolder(binding);

        }


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel =  messageModelList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(messageModel);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(messageModel);
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        MessageModel message = messageModelList.get(position);
        if (message.getId() != null && message.getId().equals("1")) {
            return VIEW_TYPE_MESSAGE_SENT;
        }
        else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }

    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {

        ChatItemSelfBinding binding;
        SentMessageHolder(ChatItemSelfBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Object obj) {
            binding.setVariable(BR.Model, obj);
            binding.executePendingBindings();
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        ChatItemWatsonBinding binding;
        ReceivedMessageHolder(ChatItemWatsonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Object obj) {
            binding.setVariable(BR.Model, obj);
            binding.executePendingBindings();
        }
    }
}
