package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    //creating variables
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    ImageView ImgUserPhoto;
    static int PReqCode = 1;
    static int REQUESCODE =1;
    Uri pickedImgUri ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName  = findViewById(R.id.FullName);
        mEmail     = findViewById(R.id.Email);
        mPassword  = findViewById(R.id.password);
        mPhone     = findViewById(R.id.PhoneNumber);
        mRegisterBtn = findViewById(R.id.Register);
        mLoginBtn    = findViewById(R.id.createText);

        //inu views
        ImgUserPhoto = findViewById(R.id.regUserPhoto);

        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 22) {
                    checkAndRequestForPermission();
                }
                else {
                    openGallery();
                }

            }
        });


        //getting the current instance of the database from Firebase so that we can perform the various operations on the database
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //To check of the user is already logged in or not.Since there is no point of displaying login to a user that is already logged in
        //We will get the current user object

        if (fAuth.getCurrentUser() != null ){
            //then we send them to the mainActivity
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        //now I write the code that is going to register the user into the Firebase
        //first for the Register button
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                //getText converts from an object and toString changes it to string
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email))  {
                    mEmail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6 ) {
                    mPassword.setError("Password must be >= 6 Characters");
                    return;
                }

                    progressBar.setVisibility(View.VISIBLE);

                    //Now I register the user in Firebase . For that we need to use Firebase object instance fAuth and createUserwithEmail and Password method. Then the EventListener to know whether the registration is successful or not

                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //displaying success message to a user
                                Toast.makeText( Register.this,"User Created.",Toast.LENGTH_SHORT) .show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                            }else {
                                //if user is not registered there will be an error message displayed to the user
                                //check here
                              Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }

    private void openGallery() {

        //TODO: open Gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }


    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE))  {

                Toast.makeText(Register.this, "Please accept for Required permission",Toast.LENGTH_SHORT).show();


            }
            else {
                        ActivityCompat.requestPermissions(Register.this,
                                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                       PReqCode );
            }
        }
        else  openGallery();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK  && requestCode == REQUESCODE && data != null ) {

            //The user has successfully picked an image
            //we need to save its reference to a Uri variable

            pickedImgUri = data.getData();
            ImgUserPhoto.setImageURI(pickedImgUri);



        }


    }
}