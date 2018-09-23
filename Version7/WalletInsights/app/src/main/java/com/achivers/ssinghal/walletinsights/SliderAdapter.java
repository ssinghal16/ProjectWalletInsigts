package com.achivers.ssinghal.walletinsights;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SliderAdapter extends PagerAdapter {

    private   Context context;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private FirebaseAuth firebaseAuth;
    public SliderAdapter(Context context){
        this.context=context;
    }
    //Arrays
    FirebaseUser user=firebaseAuth.getInstance().getCurrentUser();

    public int[] slide_image={
            R.drawable.background_image1,
            R.drawable.background_image3,
            R.drawable.background_image1
    };

    public String [] slide_heading={
            "Screen 1",
            "Screen 2",
            "Screen 3"
    };
    public String [] slide_dec={
            "Welcome to WalletInsigts,"+" "+user.getEmail()+"  "+"WalletInsigts Keep track of balances between friends and family.",
            "Add Expences or Settle up"+" :"+"you can split expences with family or with individuals",
            "Let's get started!"+"Tap anywhere to begin."
    };
    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view== (RelativeLayout) object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slideImageView= (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading= (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDesc= (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setText(slide_heading[position]);
        slideDesc.setText(slide_dec[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }


}
