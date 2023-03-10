package com.example.chefapp;



import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class order {

    LinearLayout linearLayout;
    Button order_text ;
    int is_deleted = 0;
    public order(Context context) {
        linearLayout = new LinearLayout(context);
    }

    public LinearLayout create_order(String image_directory, String order_title,Context context,LinearLayout bigone) {

        View separator = new View(context);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(context.getResources().getIdentifier(image_directory, "drawable", context.getPackageName()));
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                250,
                200
        ));

        order_text = new Button(context);
        order_text.setBackgroundResource(android.R.color.transparent);
        order_text.setBackgroundColor(Color.parseColor("#121212"));
        order_text.setText(order_title);
        order_text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        order_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity.delete_order();
                bigone.removeView(linearLayout);
                is_deleted = 1;
            }
        });

        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.addView(imageView);
        linearLayout1.addView(order_text);


        separator.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        separator.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        linearLayout.addView(linearLayout1);
        linearLayout.addView(separator);

        return linearLayout;
    }
}
