package com.example.ivann.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.onClick;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imBackGround;
    private TextView tText;
    private EditText etLogin, etPassword;
    private Button bLogin;


    private ViewTreeObserver.OnPreDrawListener mPreDrawListener =
            new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    ViewTreeObserver observer =
                            tText.getViewTreeObserver();
                    if (observer != null) {
                        observer.removeOnPreDrawListener(this);
                    }
                    Drawable drawable = imBackGround.getDrawable();
                    if (drawable != null &&
                            drawable instanceof BitmapDrawable) {
                        Bitmap bitmap =
                                ((BitmapDrawable) drawable).getBitmap();
                        if (bitmap != null) {
                            blur(bitmap, tText, 25);
                        }
                    }
                    return true;
                }
            };

    private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    ViewTreeObserver observer =
                            tText.getViewTreeObserver();
                    if (observer != null) {
                        observer.addOnPreDrawListener(
                                mPreDrawListener);
                    }
                }
            };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);


        imBackGround = (ImageView) findViewById(R.id.imBackGround);
        tText = (TextView) findViewById(R.id.tText);
        if (imBackGround != null && tText != null) {
            ViewTreeObserver observer =
                    tText.getViewTreeObserver();
            if (observer != null) {
                observer.addOnGlobalLayoutListener(
                        mLayoutListener);
            }
        }
    }

    @Override
    public void onClick(View view) {

        String password = etPassword.getText().toString();
        String login = etLogin.getText().toString();
        boolean numbersOnly = true;
        if (password.length() == 0)
            Toast.makeText(getBaseContext(), "Enter password", Toast.LENGTH_LONG).show();

        if (login.length() == 0)
            Toast.makeText(getBaseContext(), "Enter login", Toast.LENGTH_LONG).show();


        if (password != "" && password != null) {
            for (int i = 0; i < password.length(); i++) {
                boolean currentNumber = false;
                if (password.charAt(i) == '1' || password.charAt(i) == '2' || password.charAt(i) == '2' || password.charAt(i) == '4' || password.charAt(i) == '5' || password.charAt(i) == '6' || password.charAt(i) == '7' || password.charAt(i) == '8' || password.charAt(i) == '9' || password.charAt(i) == '0') {
                    currentNumber = true;
                }
                if (!currentNumber) {
                    Toast.makeText(getBaseContext(), "Password must contain numbers only", Toast.LENGTH_LONG).show();
                    numbersOnly = false;
                    break;

                }
                if (numbersOnly && password.length() != 0 && login.length() != 0) {
                    Intent intent = new Intent(getApplicationContext(), callActivity.class);
                    startActivity(intent);
                }
            }

        }

    }

    private void blur(Bitmap bkg, View view, float radius) {
        Bitmap overlay = Bitmap.createBitmap(
                view.getMeasuredWidth(),
                view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(overlay);

        canvas.drawBitmap(bkg, -view.getLeft(),
                -view.getTop(), null);

        RenderScript rs = RenderScript.create(this);

        Allocation overlayAlloc = Allocation.createFromBitmap(
                rs, overlay);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
                rs, overlayAlloc.getElement());

        blur.setInput(overlayAlloc);

        blur.setRadius(radius);

        blur.forEach(overlayAlloc);

        overlayAlloc.copyTo(overlay);

        view.setBackground(new BitmapDrawable(
                getResources(), overlay));

        rs.destroy();
    }


}