package com.example.inspire_app.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inspire_app.R;
import com.example.inspire_app.activites.LoginActivity;
import com.example.inspire_app.activites.MainActivity;
import com.example.inspire_app.activites.PostActivity;
import com.example.inspire_app.activites.SplashActivity;
import com.example.inspire_app.models.CommentData;
import com.example.inspire_app.models.ProfilePicData;
import com.example.inspire_app.responsemodels.CommentResponse;
import com.example.inspire_app.responsemodels.ProfilePicResponse;
import com.example.inspire_app.utils.LoginManager;
import com.example.inspire_app.viewmodels.CommentViewModel;
import com.example.inspire_app.viewmodels.ProfilePicViewModel;
import com.google.android.material.button.MaterialButton;


public class ProfileFragment extends Fragment {
    MaterialButton logoutbtn,share,editbtn;
    LoginManager loginManager;
    TextView name,moodleid;
    ImageView profilepic;
    ProfilePicViewModel viewModel;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;



    public ProfileFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.setname);
        moodleid = view.findViewById(R.id.moodleid);
        loginManager = new LoginManager(getContext());
        profilepic = view.findViewById(R.id.profile_pic);

        editbtn = view.findViewById(R.id.editbtn);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });


        name.setText(loginManager.getname());
        moodleid.setText(loginManager.getMoodleid());

        share = view.findViewById(R.id.sharebtn);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
                startActivity(shareIntent);
            }
        });

        logoutbtn = view.findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginManager.SetLoginStatus(false);
                loginManager.removeSharedPreference();

                startActivity(new Intent(getContext(), SplashActivity.class));
            }
        });
        return view;
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
//            imageUri = data.getData();
//            profilepic.setImageResource(imageUri.);
                Uri selectedImageUri = data.getData( );
                String picturePath = getPath( getActivity( ).getApplicationContext( ), selectedImageUri );
//                btncomment(picturePath);
                Log.d("Picture Path", picturePath);
        }
    }
    public static String getPath(Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }
    private void btncomment(String image) {

        ProfilePicData data = new ProfilePicData(image,loginManager.getid());
        initViewModel2();
        viewModel.btnpostprofile(getActivity().getApplication(), data);
    }

    private void initViewModel2() {
        viewModel = new ViewModelProvider(this).get(ProfilePicViewModel.class);
        viewModel.getCreateUserLiveData().observe(this, new Observer<ProfilePicResponse>() {
            @Override
            public void onChanged(ProfilePicResponse commentResponse) {
                if (commentResponse == null) {
                    Toast.makeText(ProfileFragment.this.getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                    error.setText("Please enter correct OTP");
                } else {
                    Toast.makeText(ProfileFragment.this.getContext(), commentResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}