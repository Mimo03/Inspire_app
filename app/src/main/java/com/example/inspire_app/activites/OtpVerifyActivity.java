package com.example.inspire_app.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inspire_app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OtpVerifyActivity extends AppCompatActivity {

    EditText otpvalue;
    TextInputLayout otplayout;
    Button verify;
    TextView resend;
    String enteredOtp, resendOtp = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);

        otpvalue = findViewById(R.id.otpvalue);
        otplayout = findViewById(R.id.text_input_layout_otp);
        verify = findViewById(R.id.verifyButton);
        resend = findViewById(R.id.resendotp);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredOtp = otpvalue.getText().toString();

                if(enteredOtp.isEmpty()){
                    otplayout.setError("Enter OTP");
                } else{
                    Intent intent = getIntent();
                    if(resendOtp.equals("")) {
                        resendOtp = intent.getStringExtra("OTP");
                    }
                    if(enteredOtp.equals(resendOtp)){
                        Toast.makeText(OtpVerifyActivity.this, "Verified", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(OtpVerifyActivity.this, ResetPasswordActivity.class);
                        startActivity(intent1);
                    }else{
                        otplayout.setError("Invalid OTP");
                    }
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String email = intent.getStringExtra("email");
                String otp = btnSendEmail(email);
                resendOtp = otp;
                Toast.makeText(OtpVerifyActivity.this, "OTP resent", Toast.LENGTH_LONG).show();
            }
        });

    }

    public String btnSendEmail(String email){

        Random n = new Random();
        String otp = Integer.toString(100000 + n.nextInt(900000));

        try {
            String sender = "mitalimohite03@gmail.com";
            String receiver = email;
            String senderPassword = "oalzgqlzztsmzxqk";

            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", 465);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, senderPassword);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);

            mimeMessage.setSubject("Inspire App Verification Code");
            mimeMessage.setText(otp);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            thread.start();

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        } catch (AddressException e){
            e.printStackTrace();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return otp;
    }
}
