package com.example.chefapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    order[] d1 = new order[100];;
    LinearLayout[] linearLayouts = new LinearLayout[100];
    int linearLayouts_size = 0;
    String[] orders_list = new String[100];
    int orders_List_size = 0;

    int count = 0;
    int rmv = 0;
    Context context;


    public void add_order(String image_directory, String order_title,LinearLayout big){
        if (linearLayouts_size == 0){
            orders_list[0] = "$"+image_directory+"#"+order_title+"$";
            d1[0] = new order(context);
            linearLayouts[0] = d1[0].create_order("download","0000",context,big);
            big.addView(linearLayouts[0]);
            linearLayouts_size ++;
            orders_List_size ++;

        }else{
            orders_list[linearLayouts_size] = "$"+image_directory+"#"+order_title+"$";
            d1[linearLayouts_size] = new order(context);
            linearLayouts[linearLayouts_size] = d1[linearLayouts_size].create_order("download",""+(linearLayouts_size+1),context,big);
            big.addView(linearLayouts[linearLayouts_size]);
            linearLayouts_size ++;
            orders_List_size ++;
        }

    }
    /*public void delere_order(int item_placement,LinearLayout big){
        if (linearLayouts_size == 0){
            return;
        }
        big.removeView(linearLayouts[item_placement]);
        for (int i = item_placement;i<orders_List_size-1;i++){
            linearLayouts[i] = linearLayouts[i+1];
            orders_list[i] = orders_list[i+1];
        }
        linearLayouts_size --;
        orders_List_size --;

    }*/
    public void update_order_list() {
        for (int j = 0; j < linearLayouts_size; j++) {
            if (d1[j].is_deleted == 1) {
                for (int i = j; i < linearLayouts_size - 1; i++) {
                    linearLayouts[i] = linearLayouts[i + 1];
                    orders_list[i] = orders_list[i + 1];
                    d1[j] = d1[j+1];
                }
                linearLayouts_size--;
                orders_List_size--;
            }
        }
        try {
            Thread.sleep(100); // Sleep for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        update_order_list();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                update_order_list();
            }
        }).start();

        context = this;

        Button btn = findViewById(R.id.button);
        Button b = findViewById(R.id.button1);

        LinearLayout big_layout = findViewById(R.id.myLinearLayout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_order("download","sdadasd",big_layout);


            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //big_layout.removeView(linearLayouts[0]);
                Log.d("MyTag",""+linearLayouts_size);


            }
        });
    }
}
