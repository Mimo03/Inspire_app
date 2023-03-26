package com.example.inspire_app.fragments;

import static androidx.compose.ui.geometry.SizeKt.Size;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inspire_app.R;
import com.example.inspire_app.activites.LoginActivity;
import com.example.inspire_app.activites.MainActivity;
import com.example.inspire_app.activites.PostActivity;
import com.example.inspire_app.adapters.HorzRecyclerAdapter;
import com.example.inspire_app.adapters.PostRecyclerAdapter;
import com.example.inspire_app.interfaces.LikedOnclickrecycler;
import com.example.inspire_app.interfaces.Postonclickrecyclerview;
import com.example.inspire_app.models.PostData;
import com.example.inspire_app.models.PostLikedData;
import com.example.inspire_app.responsemodels.GetLikedResponse;
import com.example.inspire_app.responsemodels.LoginResponse;
import com.example.inspire_app.responsemodels.PostLikedResponse;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.utils.LoginManager;
import com.example.inspire_app.viewmodels.LikedPostViewModel;
import com.example.inspire_app.viewmodels.LoginViewModel;
import com.example.inspire_app.viewmodels.PostViewModel;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HorzRecyclerAdapter adapter;
    RecyclerView postrecycler;
    Party party;
    String name;
    TextView nametext;
    MaterialCardView cardView;
    KonfettiView konfettiView;
    LoginManager loginManager;
    PostRecyclerAdapter recyclerAdapter;
    PostViewModel viewModel;
    List<PostData> data = new ArrayList<>();
    LikedPostViewModel likedPostViewModel;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        cardView = view.findViewById(R.id.hotpickscard);
//        konfettiView = view.findViewById(R.id.konfettiView);
//        Shape.DrawableShape drawableshapes= new Shape.DrawableShape(this.getContext().getDrawable(R.drawable.ic_baseline_android_24),true);
//
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"clicked",Toast.LENGTH_LONG).show();
//                EmitterConfig emitterConfig = new Emitter(300,TimeUnit.MILLISECONDS).max(300);
//                konfettiView.start(
//                        new PartyFactory(emitterConfig)
//                                .shapes(Shape.Circle.INSTANCE,Shape.Square.INSTANCE)
//                                .spread(360)
//                                .position(0.5,0.25,1,1)
//                                .sizes(new Size(8,50,10))
//                                .timeToLive(10000)
//                                .fadeOutEnabled(true)
//                                .build()
//                );
//            }
//        });
        loginManager = new LoginManager(getContext());
//        Bundle bundle = getArguments();
//        name = bundle.getString("name");

        nametext = view.findViewById(R.id.nametext);
        nametext.setText(loginManager.getname());

        viewModel=new ViewModelProvider(HomeFragment.this).get(PostViewModel.class);
        viewModel.btnnewpost(getActivity().getApplication());
        viewModel.getCreateUserLiveData().observe(getActivity(), new Observer<PostResponse>() {
            @Override
            public void onChanged(PostResponse postResponse) {
                data = postResponse.getData();
                postrecycler = view.findViewById(R.id.postrecyclerview);
                recyclerAdapter = new PostRecyclerAdapter(getContext(), data, new Postonclickrecyclerview() {
                    @Override
                    public void onclick(String id) {
                        Intent i = new Intent(getContext(),PostActivity.class);
                        i.putExtra("id",id);
                        startActivity(i);

                    }
                }, new LikedOnclickrecycler() {
                    @Override
                    public void onclick(String id, String category, String org, String image) {
                        Toast.makeText(getContext(),"clicked",Toast.LENGTH_LONG).show();
                        btnpostclicked(id,category,org,image);
//                        ImageButton imageButton = view.findViewById(R.id.likedbtn);
//                        imageButton.setClickable(false);
//                        imageButton.setBackgroundColor(Color.RED);

                    }
                });
                        postrecycler.setAdapter(recyclerAdapter);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                postrecycler.setLayoutManager(linearLayoutManager1);
            }
        });

        recyclerView = view.findViewById(R.id.horizontalscroll);
        adapter = new HorzRecyclerAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
    private void btnpostclicked (String id,String category,String org,String image){

        PostLikedData data = new PostLikedData(loginManager.getid(), id);
        initViewModel();
        likedPostViewModel.btnpost(this.getActivity().getApplication(),data);
    }

    private void initViewModel () {
        likedPostViewModel = new ViewModelProvider(this).get(LikedPostViewModel.class);
        likedPostViewModel.getCreateUserLiveData2().observe(this, new Observer<PostLikedResponse>() {
            @Override
            public void onChanged(PostLikedResponse postLikedResponse) {
                if (postLikedResponse == null) {
                    Toast.makeText(HomeFragment.this.getContext(),"Failed",Toast.LENGTH_SHORT).show();
//                    error.setText("Please enter correct OTP");
                } else {
                    Toast.makeText(HomeFragment.this.getContext(), postLikedResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }

                }
        });
        }
    }
