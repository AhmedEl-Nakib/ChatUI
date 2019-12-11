package com.eminent.a2019.chatui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eminent.a2019.chatui.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding ;
    RecyclerViewAdapter recyclerViewAdapter ;
    HorizontalRecyclerViewAdapter recyclerViewAdapterHorizontal;
    ArrayList<MessageModel> arrayList,arrayListHorizontal;
    String currentTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        arrayList = new ArrayList<>();
        arrayListHorizontal = new ArrayList<>();
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        Glide.with(this).load("https://avatars0.githubusercontent.com/u/33866122?s=400&u=958a1cb82426b24f47dc872cdf096ac58f3e56fe&v=4").into(binding.userImage);

        arrayListHorizontal.add(new MessageModel("Hello driver","1",currentTime));
        arrayListHorizontal.add(new MessageModel("Where are you ?","1",currentTime));
        arrayListHorizontal.add(new MessageModel("I am in current place now ","1",currentTime));
        arrayListHorizontal.add(new MessageModel("are you arrived ?","1",currentTime));



        arrayList.add(new MessageModel("Welcome Ahmed","2",new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date())));
        arrayList.add(new MessageModel("How Are you ?","2",new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date())));

        recyclerViewAdapterHorizontal = new HorizontalRecyclerViewAdapter(arrayListHorizontal,MainActivity.this);
        recyclerViewAdapter = new RecyclerViewAdapter(arrayList);

        binding.recyclerViewId.setAdapter(recyclerViewAdapter);
        binding.recyclerViewHorizontal.setAdapter(recyclerViewAdapterHorizontal);

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(binding.message.getText().toString(),new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
            }
        });

    }

    public void makeScroll()
    {
        binding.recyclerViewId.getLayoutManager().smoothScrollToPosition(binding.recyclerViewId, null, recyclerViewAdapter.getItemCount() - 1);
    }

    public void sendMessage(String message , String time)
    {
        if(message.isEmpty())
            Toast.makeText(MainActivity.this, "Write Message please !", Toast.LENGTH_SHORT).show();
        else {
            arrayList.add(new MessageModel(message, "1",time));
            recyclerViewAdapter.notifyDataSetChanged();
            binding.message.setText("");
            makeScroll();
            Log.i("Send Time",currentTime);
        }
    }



//    @BindingAdapter("arrayData")
//    public static void setPaddingLeft(RecyclerView view, ArrayList<MessageModel> messageModelList) {
//        ((RecyclerViewAdapter) Objects.requireNonNull(view.getAdapter())).setList(messageModelList);
//    }
}
