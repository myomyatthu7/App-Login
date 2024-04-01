package android.mmtdev.applogin;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyApi extends AppCompatActivity {

    private ScrollView scrollView;
    private Handler handler;
    private int scrollDelay = 40; // Scroll delay in milliseconds (2000 milliseconds = 2 seconds)
    private int scrollSpeed = 2; // Scroll speed in pixels
    boolean auto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myapi);

        scrollView = findViewById(R.id.scrollView);
        handler = new Handler();

        Button speedUpButton = findViewById(R.id.speedUpButton);
        Button speedDownButton = findViewById(R.id.speedDownButton);
        Button delayUpButton = findViewById(R.id.delayUpButton);
        Button delayDownButton = findViewById(R.id.delayDownButton);
        Button btnAuto = findViewById(R.id.btnAutoOff);

        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto = !auto;
                if (auto) {
                    // Start auto-scrolling
                    startAutoScroll();
                } else {
                    handler.removeCallbacksAndMessages(null);
                }
            }
        });

        speedUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollSpeed += 1; // Increase scroll speed by 5 pixels
                ToastMsg.toastMsgShort(MyApi.this,String.valueOf(scrollSpeed));
            }
        });

        speedDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollSpeed -= 1; // Decrease scroll speed by 5 pixels
                if (scrollSpeed < 0) {
                    scrollSpeed = 0;
                }
                ToastMsg.toastMsgShort(MyApi.this,String.valueOf(scrollSpeed));
            }
        });

        delayUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollDelay < 6) {
                    scrollDelay = 5;
                    ToastMsg.toastMsgShort(MyApi.this,"Limit");
                } else scrollDelay -= 5; // Decrease scroll delay by 500 milliseconds
                ToastMsg.toastMsgShort(MyApi.this,String.valueOf(scrollDelay));
            }
        });

        delayDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollDelay += 5; // Increase scroll delay by 500 milliseconds
                ToastMsg.toastMsgShort(MyApi.this,String.valueOf(scrollDelay));
            }
        });
        //scrollView.performClick();
    }

    private void startAutoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Calculate the new scroll position
                int newY = scrollView.getScrollY() + scrollSpeed;
                // Scroll smoothly to the new position
                scrollView.smoothScrollTo(0, newY);
                // Schedule next scroll after scrollDelay
                handler.postDelayed(this, scrollDelay);
            }
        }, scrollDelay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any remaining callbacks to prevent memory leaks
        handler.removeCallbacksAndMessages(null);
    }
}


//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.ScrollView;
//
//
//public class MyApi extends AppCompatActivity implements View.OnClickListener {
//    FloatingActionButton fbtnAdd;
//    private ScrollView scrollView;
//    private Handler handler;
//    private int scrollDelay = 50; // Scroll delay in milliseconds (2000 milliseconds = 2 seconds)
//    private int scrollSpeed = 2; // Scroll speed in pixels
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.myapi);
//        fbtnAdd = findViewById(R.id.fbtnAdd);
//        fbtnAdd.setOnClickListener(this);
//
//
//        scrollView = findViewById(R.id.scrollView);
//        handler = new Handler();
//
//        // Start auto-scrolling
//        startAutoScroll();
//    }
//
//    private void startAutoScroll() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Calculate the new scroll position
//                int newY = scrollView.getScrollY() + scrollSpeed;
//                // Scroll smoothly to the new position
//                scrollView.smoothScrollTo(0, newY);
//                // Schedule next scroll after scrollDelay
//                handler.postDelayed(this, scrollDelay);
//            }
//        }, scrollDelay);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Remove any remaining callbacks to prevent memory leaks
//        handler.removeCallbacksAndMessages(null);
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        //ToastMsg.toastMsgShort(MyApi.this,"CLick");
//        startActivity(new Intent(MyApi.this,AddData.class));
//
//    }
//}
