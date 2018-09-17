package com.achivers.ssinghal.walletinsights;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity {

    private ViewPager mslideviewPager;
    private LinearLayout mdotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mdots;
    //private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        mslideviewPager= (ViewPager) findViewById(R.id.viewPager1);
        mdotLayout= (LinearLayout) findViewById(R.id.mdot);
      //  relativeLayout= (RelativeLayout) findViewById(R.id.r1);
        sliderAdapter= new SliderAdapter(this);
        mslideviewPager.setAdapter(sliderAdapter);
        mslideviewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        addDotIndicator(0);
    }

    public void addDotIndicator(int position){
        mdots =new TextView[sliderAdapter.getCount()];
        mdotLayout.removeAllViews();


        for(int i=0; i<mdots.length;i++){
            mdots[i]=new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));


            mdotLayout.addView(mdots[i]);
        }

        if(mdots.length>0){
            mdots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }



    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onPageSelected(int i) {
            addDotIndicator(i);
            if(i==sliderAdapter.getCount()-1){

                mslideviewPager.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            Intent i1 =new Intent(WelcomeScreen.this,Home.class);
                            startActivity(i1);
                            return true;
                        }

                    });
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
