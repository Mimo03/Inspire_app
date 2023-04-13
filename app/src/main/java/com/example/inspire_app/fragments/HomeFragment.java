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

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inspire_app.R;
import com.example.inspire_app.activites.LoginActivity;
import com.example.inspire_app.activites.MainActivity;
import com.example.inspire_app.activites.PostActivity;
import com.example.inspire_app.adapters.HorzRecyclerAdapter;
import com.example.inspire_app.adapters.PostRecyclerAdapter;
import com.example.inspire_app.interfaces.Commentonclickrecycler;
import com.example.inspire_app.interfaces.Horzonclickrecycler;
import com.example.inspire_app.interfaces.LikedOnclickrecycler;
import com.example.inspire_app.interfaces.Postonclickrecyclerview;
import com.example.inspire_app.models.CommentData;
import com.example.inspire_app.models.DetailsModel;
import com.example.inspire_app.models.MostLikedData;
import com.example.inspire_app.models.PostData;
import com.example.inspire_app.models.PostLikedData;
import com.example.inspire_app.responsemodels.CommentResponse;
import com.example.inspire_app.responsemodels.GetDetailsResponse;
import com.example.inspire_app.responsemodels.GetLikedResponse;
import com.example.inspire_app.responsemodels.LoginResponse;
import com.example.inspire_app.responsemodels.MostLikedResponse;
import com.example.inspire_app.responsemodels.PostLikedResponse;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.utils.LoginManager;
import com.example.inspire_app.viewmodels.CommentViewModel;
import com.example.inspire_app.viewmodels.GetDetailsViewModel;
import com.example.inspire_app.viewmodels.LikedPostViewModel;
import com.example.inspire_app.viewmodels.LoginViewModel;
import com.example.inspire_app.viewmodels.MostLikedViewModel;
import com.example.inspire_app.viewmodels.PostViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

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
    Boolean check = false;
    TextView nametext;
    MaterialCardView hotpickcard;
    MaterialCardView cardView;
    KonfettiView konfettiView;
    LoginManager loginManager;
    PostRecyclerAdapter recyclerAdapter;
    PostViewModel viewModel;
    List<PostData> data = new ArrayList<>();
    List<MostLikedData> mostLikedData = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<DetailsModel> detailsModels = new ArrayList<>();
    LikedPostViewModel likedPostViewModel;
    MostLikedViewModel mostLikedViewModel;
    TextView title,likes,content;
    MaterialButton category;
    LinearLayout linearLayout;
    ImageView hotpickimage;
    GetDetailsViewModel getDetailsViewModel;
    TextView textView;
    CommentViewModel commentViewModel;
    List<String> datalist = new ArrayList<>();


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
        stringList.add("All");
        stringList.add("Startup");
        stringList.add("Internship");
        stringList.add("Hackathon");
        stringList.add("Achievement");
        stringList.add("Event Winners");
        hotpickcard = view.findViewById(R.id.hotpickscard);

        textView = view.findViewById(R.id.comment_text);




        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hotpickcard.setVisibility(View.VISIBLE);


            }
        }, 10000);


        hotpickcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostLikedViewModel=new ViewModelProvider(HomeFragment.this).get(MostLikedViewModel.class);
                mostLikedViewModel.btnmostliked(getActivity().getApplication());
                mostLikedViewModel.getCreateUserLiveData().observe(getActivity(), new Observer<MostLikedResponse>() {
                    @Override
                    public void onChanged(MostLikedResponse mostLikedResponse) {
                        try {
                            if (mostLikedResponse.getData() == null) {
//
                                Toast.makeText(getContext(), "No Post Found", Toast.LENGTH_SHORT).show();

                            } else {
                                likes = view.findViewById(R.id.hotpick_likes);

                                mostLikedData = mostLikedResponse.getData();
                                likes.setText(mostLikedData.get(0).getCount() + " likes");

                                getDetailsViewModel = new ViewModelProvider(HomeFragment.this).get(GetDetailsViewModel.class);
                                getDetailsViewModel.btndetails(getActivity().getApplication(), mostLikedData.get(0).get_id());
                                getDetailsViewModel.getCreateUserLiveData().observe(getActivity(), new Observer<GetDetailsResponse>() {
                                    @Override
                                    public void onChanged(GetDetailsResponse getDetailsResponse) {
                                        title = view.findViewById(R.id.hotpick_title);
                                        content = view.findViewById(R.id.hotpick_content);
                                        category = view.findViewById(R.id.categorybtn);
                                        linearLayout = view.findViewById(R.id.hotpick_details);
                                        hotpickimage = view.findViewById(R.id.hotpick_img);

                                        detailsModels = getDetailsResponse.getData();
                                        title.setText(detailsModels.get(0).getOrganization());
                                        content.setText(detailsModels.get(0).getContent());
                                        category.setText("# " + detailsModels.get(0).getCategory());
                                        Picasso.with(getContext()).load("http://192.168.10.146:3000" + detailsModels.get(0).getImageurl()).into(hotpickimage);

                                        linearLayout.setVisibility(View.VISIBLE);
                                        hotpickcard.setClickable(false);

                                    }
                                });
                            }
                        }catch (Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                            }


                });
//                for(int i=0;i<mostLikedData.size();i++){
//                    datalist.add(mostLikedData.get(i).getMostpost().get(0).getCategory());
//
//                }

            }
        });


        nametext = view.findViewById(R.id.nametext);
        nametext.setText(loginManager.getname());
//        Toast.makeText(getContext(), datalist.get(0), Toast.LENGTH_SHORT).show();

        recyclerView = view.findViewById(R.id.horizontalscroll);
        adapter = new HorzRecyclerAdapter(getContext(), stringList, new Horzonclickrecycler() {
            @Override
            public void onclick(String category) {
                viewModel.btnnewpost(getActivity().getApplication(),category, loginManager.getCategory());
                viewModel.getCreateUserLiveData().observe(getActivity(), new Observer<PostResponse>() {
                    @Override
                    public void onChanged(PostResponse postResponse) {
                        try {
                            if (postResponse.getData() == null) {
//
                                Toast.makeText(getContext(), "No Post found", Toast.LENGTH_SHORT).show();

                            } else {
                                data = postResponse.getData();
                                postrecycler = view.findViewById(R.id.postrecyclerview);
                                recyclerAdapter = new PostRecyclerAdapter(getContext(), data, new Postonclickrecyclerview() {
                                    @Override
                                    public void onclick(String id) {
                                        Intent i = new Intent(getContext(), PostActivity.class);
                                        i.putExtra("id", id);
                                        startActivity(i);

                                    }
                                }, new LikedOnclickrecycler() {
                                    @Override
                                    public void onclick(String id, String category, String org, String image) {
                                        btnpostclicked(id, category, org, image);
//                        ImageButton imageButton = view.findViewById(R.id.likedbtn);
//                        imageButton.setClickable(false);
//                        imageButton.setBackgroundColor(Color.RED);

                                    }
                                }, new Commentonclickrecycler() {
                                    @Override
                                    public void onclick(String comment,String id) {
                                        btncomment(comment,id);

                                    }
                                });
                                adapter.notifyDataSetChanged();
                                postrecycler.setAdapter(recyclerAdapter);
                                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                                postrecycler.setLayoutManager(linearLayoutManager1);
                            }
                        }catch (Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        viewModel=new ViewModelProvider(HomeFragment.this).get(PostViewModel.class);
        viewModel.btnnewpost(getActivity().getApplication(),"All", loginManager.getCategory());
        viewModel.getCreateUserLiveData().observe(getActivity(), new Observer<PostResponse>() {
            @Override
            public void onChanged(PostResponse postResponse) {
                try {
                    if (postResponse.getData() == null) {
                        Toast.makeText(getContext(), "Some Error Occured", Toast.LENGTH_SHORT).show();

                    } else {

                        data = postResponse.getData();
                        postrecycler = view.findViewById(R.id.postrecyclerview);
                        recyclerAdapter = new PostRecyclerAdapter(getContext(), data, new Postonclickrecyclerview() {
                            @Override
                            public void onclick(String id) {
                                Intent i = new Intent(getContext(), PostActivity.class);
                                i.putExtra("id", id);
                                startActivity(i);

                            }
                        }, new LikedOnclickrecycler() {
                            @Override
                            public void onclick(String id, String category, String org, String image) {
                                Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG).show();
                                btnpostclicked(id, category, org, image);
//                        ImageButton imageButton = view.findViewById(R.id.likedbtn);
//                        imageButton.setClickable(false);
//                        imageButton.setBackgroundColor(Color.RED);

                            }
                        }, new Commentonclickrecycler() {
                            @Override
                            public void onclick(String comment,String id) {
                                btncomment(comment,id);
                            }
                        });
                        adapter.notifyDataSetChanged();
                        postrecycler.setAdapter(recyclerAdapter);
                        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                        postrecycler.setLayoutManager(linearLayoutManager1);
                    }
                }catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                    }
                });

                return view;
            }

            private void btnpostclicked(String id, String category, String org, String image) {

                PostLikedData data = new PostLikedData(loginManager.getid(), id);
                initViewModel();
                likedPostViewModel.btnpost(getActivity().getApplication(), data);
            }

            private void initViewModel() {
                likedPostViewModel = new ViewModelProvider(getActivity()).get(LikedPostViewModel.class);
                likedPostViewModel.getCreateUserLiveData2().observe(getActivity(), new Observer<PostLikedResponse>() {
                    @Override
                    public void onChanged(PostLikedResponse postLikedResponse) {
                        if (postLikedResponse == null) {
                            Toast.makeText(HomeFragment.this.getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                    error.setText("Please enter correct OTP");
                        } else {
                            Toast.makeText(HomeFragment.this.getContext(), postLikedResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
    private void btncomment(String comment,String id) {

        CommentData data = new CommentData(id,comment);
        initViewModel2();
        commentViewModel.btncomment(getActivity().getApplication(), data);
    }

    private void initViewModel2() {
        commentViewModel = new ViewModelProvider(getActivity()).get(CommentViewModel.class);
        commentViewModel.getCreateUserLiveData().observe(getActivity(), new Observer<CommentResponse>() {
            @Override
            public void onChanged(CommentResponse commentResponse) {
                if (commentResponse == null) {
                    Toast.makeText(HomeFragment.this.getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                    error.setText("Please enter correct OTP");
                } else {
                    Toast.makeText(HomeFragment.this.getContext(), commentResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    }
