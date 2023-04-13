package com.example.inspire_app.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SendMailActivity extends AppCompatActivity {

    EditText moodleId;
    TextInputLayout moodlelayout;
    Button btnsend;
    String moodlevalue, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmail);

        moodleId = findViewById(R.id.moodleId);
        moodlelayout = findViewById(R.id.text_input_layout_moodleId);
        btnsend = findViewById(R.id.sendButton);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moodlevalue = moodleId.getText().toString();
                email = moodlevalue + "@apsit.edu.in";

                if(moodlevalue.isEmpty()){
                    moodlelayout.setError("Enter Moodle Id");
                }else{
                   String otp = btnSendEmail(email);
                   Intent intent = new Intent(getApplicationContext(), OtpVerifyActivity.class);
                   intent.putExtra("OTP", otp);
                   intent.putExtra("email", email);
                   intent.putExtra("moodleID", moodlevalue);
                   Toast.makeText(SendMailActivity.this, "OTP sent", Toast.LENGTH_LONG).show();
                   startActivity(intent);
                }
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
