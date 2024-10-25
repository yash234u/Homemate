package com.example.homemate.Booking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homemate.Booking.Booking_Confirmed_Activity;
import com.example.homemate.HomeFragment;
import com.example.homemate.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentActivity extends AppCompatActivity {
    private EditText cardNumber, expiryDate, cvv;
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardNumber = findViewById(R.id.card_number);
        expiryDate = findViewById(R.id.expiry_date);
        cvv = findViewById(R.id.cvv);
        payButton = findViewById(R.id.pay_button);

        // Add TextWatcher for card number formatting
        cardNumber.addTextChangedListener(new TextWatcher() {
            private static final int TOTAL_DIGITS = 16;
            private static final int DIVIDER_MODULO = 5; // Position to add space after every 4 digits
            private static final char DIVIDER = ' ';

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Remove previous formatting
                String cardNum = s.toString().replace(String.valueOf(DIVIDER), "");

                // Ensure card number is not more than 16 digits
                if (cardNum.length() > TOTAL_DIGITS) {
                    cardNum = cardNum.substring(0, TOTAL_DIGITS);
                }

                // Reformat card number with spaces after every 4 digits
                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < cardNum.length(); i++) {
                    formatted.append(cardNum.charAt(i));
                    if ((i + 1) % (DIVIDER_MODULO - 1) == 0 && i + 1 < cardNum.length()) {
                        formatted.append(DIVIDER);
                    }
                }

                cardNumber.removeTextChangedListener(this); // Avoid infinite loop
                cardNumber.setText(formatted.toString());
                cardNumber.setSelection(formatted.length());
                cardNumber.addTextChangedListener(this);
            }
        });

        // Expiry date formatting
        expiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 2 && !s.toString().contains("/")) {
                    expiryDate.setText(s + "/");
                    expiryDate.setSelection(s.length() + 1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Payment Button Click
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPayment();
            }
        });
    }

    private void processPayment() {
        String cardNum = cardNumber.getText().toString().replace(" ", "");
        String expiry = expiryDate.getText().toString();
        String cvvCode = cvv.getText().toString();

        // Dummy Validation
        if (validateCardDetails(cardNum, expiry, cvvCode)) {
            showOtpDialog();
        } else {
            Toast.makeText(this, "Invalid card details", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateCardDetails(String cardNum, String expiry, String cvv) {
        // Check if cardNum is 16 digits
        return cardNum.equals("4111111111111111") && expiry.equals("12/25") && cvv.equals("123");
    }

    private void showOtpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter OTP");

        final EditText otpInput = new EditText(this);
        otpInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        builder.setView(otpInput);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String otp = otpInput.getText().toString();
                if (otp.equals("123456")) {
                    placeOrder();
                } else {
                    Toast.makeText(PaymentActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void placeOrder() {
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("data_email");
        String serviceProvider = intent.getStringExtra("serviceprovider");
        String serviceProviderContact = intent.getStringExtra("serviceprovidercontact");
        String serviceName = intent.getStringExtra("servicename");
        String servicePrice = intent.getStringExtra("serviceprice");
        String serviceTime = intent.getStringExtra("servicetime");
        String serviceDate = intent.getStringExtra("servicedate");
        String userAddress = intent.getStringExtra("user_address");
        String instructions = intent.getStringExtra("instructions");


        // Set your order details here
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BookingDetails").child(userEmail.replace(".", ","));
        String uniqueOrderId = databaseReference.push().getKey(); // Generate a unique ID
        BookingDetails bookingDetails = new BookingDetails(userEmail, uniqueOrderId, serviceDate, instructions, servicePrice, serviceName, serviceProviderContact, serviceProvider, serviceTime, userAddress);

        databaseReference.child(uniqueOrderId).setValue(bookingDetails).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(PaymentActivity.this, "Payment successful", Toast.LENGTH_SHORT).show();
                Intent bookingIntent = new Intent(PaymentActivity.this, Booking_Confirmed_Activity.class);
                startActivity(bookingIntent);
                finish();
            } else {
                Toast.makeText(PaymentActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();
                // Redirect to HomeFragment on failure
                startActivity(new Intent(PaymentActivity.this, HomeFragment.class));
            }
        });
    }
}
