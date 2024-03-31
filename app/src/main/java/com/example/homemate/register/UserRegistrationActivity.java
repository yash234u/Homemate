package com.example.homemate.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.homemate.BottomNavigationActivity;
import com.example.homemate.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UserRegistrationActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextInputLayout fullname;
    private TextInputLayout Email;
    private TextInputLayout Contact;
    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout rpassword;
    private RadioGroup Gender;
    private RadioButton Genbutton;
    private Button registerforcustomer;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private Uri filePath;
    private StorageReference storageReference;
    private final int PICK_IMAGE_REQUEST = 22;
    private ProgressBar progressBar_register;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), BottomNavigationActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        fullname = findViewById(R.id.txtname);
        registerforcustomer = findViewById(R.id.btnregister);
        imageView = findViewById(R.id.profilepic);
        Gender = (RadioGroup) findViewById(R.id.GenderRadio);
        Email = findViewById(R.id.txtemail);
        Contact = findViewById(R.id.txtcontact);
        username = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtpass);
        rpassword = findViewById(R.id.txtpass);
        progressBar_register = findViewById(R.id.progressbar_register_otp);

        databaseReference=FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();

        int selectedgender = Gender.getCheckedRadioButtonId();
        Genbutton = findViewById(selectedgender);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });

        registerforcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateFullName() | !validateEmail() | !validateGender() | !validatePhoneNumber() | !validatePassword() | !validateRPassword()) {
                    Toast.makeText(UserRegistrationActivity.this, "Please Fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar_register.setVisibility(View.VISIBLE);
                    registerforcustomer.setVisibility(View.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + Contact.getEditText().getText().toString(),
                            60, TimeUnit.SECONDS,
                            UserRegistrationActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar_register.setVisibility(View.GONE);
                                    registerforcustomer.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar_register.setVisibility(View.GONE);
                                    registerforcustomer.setVisibility(View.VISIBLE);
                                    Toast.makeText(UserRegistrationActivity.this, "Code not Send", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    databaseReference.child("UserDetails").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.hasChild(Email.getEditText().getText().toString().replace(".",","))) {
                                                Toast.makeText(UserRegistrationActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                                            } else {
                                                uploadImage();
                                                Toast.makeText(UserRegistrationActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), otpverification_for_registerActivity.class);
                                                intent.putExtra("fullname_register", fullname.getEditText().getText().toString());
                                                intent.putExtra("username_register", username.getEditText().getText().toString());
                                                intent.putExtra("gender_register", Genbutton.getText().toString());
                                                intent.putExtra("Email_register", Email.getEditText().getText().toString().replace(".",","));
                                                intent.putExtra("mobile_register", Contact.getEditText().getText().toString());
                                                intent.putExtra("password_register", password.getEditText().getText().toString());
                                                intent.putExtra("profile_img_register",filePath);
                                                intent.putExtra("backendotp", s);
                                                clear();
                                                progressBar_register.setVisibility(View.GONE);
                                                registerforcustomer.setVisibility(View.VISIBLE);
                                                startActivity(intent);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(UserRegistrationActivity.this, "Error Connecting to Database", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                    );

                }
            }
        });
    }

    private void SelectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
          //  ProgressDialog progressDialog = new ProgressDialog(this);
          //  progressDialog.setTitle("Uploading...");
            //progressDialog.show();

            StorageReference ref = storageReference.child("UserProfilePictures/"+username.getEditText().getText().toString()+"/profile.jpg");

            // adding listeners on upload
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                    while (!uriTask.isComplete()) ;
                                    Uri url = uriTask.getResult();
                                    databaseReference.child("UserDetails").child(Email.getEditText().getText().toString().replace(".",",")).child("Profile").setValue(url.toString());

                                    //progressDialog.dismiss();
                                    Toast.makeText(UserRegistrationActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error in occurred ,Image not uploaded
                          //  progressDialog.dismiss();
                            Toast.makeText(UserRegistrationActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        // Progress Listener for loading
                        // percentage on the dialog box
                        @Override
                        public void onProgress(
                                UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                           // progressDialog.setMessage("Registering " + (int) progress + "%");
                        }
                    });
        }
    }

    private boolean validateFullName() {
        String val = fullname.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            fullname.setError("Please enter your full name");
            return false;
        } else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = Email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if (val.isEmpty()) {
            Email.setError("Please enter your email");
            return false;
        } else if (!val.matches(checkEmail) || !val.endsWith(".com")) {
            Email.setError("Invalid Email!");
            return false;
        } else {
            Email.setError(null);
            Email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhoneNumber() {
        String val = Contact.getEditText().getText().toString().trim();
        String MobilePattern = "[0-9]{10}";
        if (val.isEmpty()) {
            Contact.setError("Please enter your phone number");
            return false;
        } else if (!val.matches(MobilePattern)) {
            Contact.setError("Invalid Number!");
            return false;
        } else {
            Contact.setError(null);
            Contact.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateGender() {
        if (Gender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            int selectedgender = Gender.getCheckedRadioButtonId();
            Genbutton = findViewById(selectedgender);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[a-zA-Z])" +      //any letter
                ".{4,}";               //at least 4 characters;
        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password should contain 4 characters!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateRPassword() {
        String val = rpassword.getEditText().getText().toString().trim();
        String val1 = rpassword.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            rpassword.setError("Field can not be empty");
            return false;
        } else if (!val1.equals(val)) {
            rpassword.setError("Password does not match");
            return false;
        } else {
            rpassword.setError(null);
            rpassword.setErrorEnabled(false);
            return true;
        }
    }

    public void clear() {
        fullname.getEditText().setText("");
        Contact.getEditText().setText("");
        Gender.clearCheck();
        imageView.setImageResource(0);
        password.getEditText().setText("");
        rpassword.getEditText().setText("");
    }

}