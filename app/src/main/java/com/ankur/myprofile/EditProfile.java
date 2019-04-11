package com.ankur.myprofile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

public class EditProfile extends AppCompatActivity {

    private ImageView pic;
    private ImageView icon;
    private TextView email;
    private int i;
    private boolean varified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        pic =findViewById(R.id.propic);
        icon =findViewById(R.id.iconIV);
        email = findViewById(R.id.emailET);


        //checking if email verified
        varified=checkEmail();

        if (varified){
            email.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.verified), null);
        }
        else{
            email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.unverified, 0);

            //clicking on drawableRight when unverified

            email.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_LEFT = 0;
                    final int DRAWABLE_TOP = 1;
                    final int DRAWABLE_RIGHT = 2;
                    final int DRAWABLE_BOTTOM = 3;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (email.getRight() - email.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            //pop up window

                            AlertDialog.Builder MB = new AlertDialog.Builder(EditProfile.this);
                            View popupView = getLayoutInflater().inflate(R.layout.email_popup, null);
                            final EditText vmail = popupView.findViewById(R.id.vmail);
                            Button bmail = popupView.findViewById(R.id.bmail);

                            MB.setView(popupView);
                            final AlertDialog dialog = MB.create();
                            dialog.show();

                            bmail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //checking if field is empty or not
                                    if(!vmail.getText().toString().isEmpty()){
                                        Toast.makeText(EditProfile.this,
                                                "Verification Link sent in email",
                                                Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }else{
                                        Toast.makeText(EditProfile.this,
                                                "Please enter email address to verify",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                            return true;
                        }
                    }
                    return true;
                }
            });
        }









        //loading pro pic
        GlideApp
                .with(EditProfile.this)
                .load(i)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .apply(RequestOptions.circleCropTransform())
                .into(pic);


       //loading pro pic change icon
        GlideApp
                .with(EditProfile.this)
                .load(R.drawable.icon_edit)
                .error(R.drawable.icon_edit)
                .fallback(R.drawable.icon_edit)
                .apply(RequestOptions.circleCropTransform())
                .into(icon);




       //selecting image
        icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeImage();
            }
        });



    }


    //changing pro pic
    public void changeImage(){
        GlideApp
                .with(EditProfile.this)
                .load(i)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade(2000))
                .apply(RequestOptions.circleCropTransform())
                .into(pic);

    }


    //email verification function
    public boolean checkEmail(){
        String s = getString (R.string.user_email);
        if(s.equals("ankurn590@gmail.com")){
            return true;

        }
        return false;

    }







}
