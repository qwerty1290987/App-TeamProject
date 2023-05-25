package com.example.myteamproject_chanmin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.ipConfig;
import static com.example.myteamproject_chanmin.Common.CommonMethod.loginDto;
import static com.example.myteamproject_chanmin.Common.CommonMethod.timerAddStaticDTO;
import static com.example.myteamproject_chanmin.Common.CommonMethod.timerComDTOS;
import static com.example.myteamproject_chanmin.Common.CommonMethod.timerReadyDTOS;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDTO;

import com.example.myteamproject_chanmin.Adapter.TimerReady2Adapter;
import com.example.myteamproject_chanmin.Adapter.TimerReady3Adapter;
import com.example.myteamproject_chanmin.Adapter.TimerReadyAdapter;
import com.example.myteamproject_chanmin.Adapter.TrainerAdapter;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.TimerAddDTO;
import com.example.myteamproject_chanmin.DTO.TimerListDTO;
import com.example.myteamproject_chanmin.DTO.TimerListDTO2;
import com.example.myteamproject_chanmin.DTO.TrainerDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Timer extends Fragment {

    private static final String TAG = "Timer main:";

    Button plusBtn;

    SportListAdd sportListAdd;

    ArrayList<TimerListDTO> dtos;
    RecyclerView recyclerView;
    TimerReadyAdapter adapter;

    ArrayList<TimerListDTO2> dtos2;
    RecyclerView recyclerView2;
    TimerReady2Adapter adapter2;

    RecyclerView recyclerView3;
    TimerReady3Adapter adapter3;

    ImageButton imageButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.sportlist , container , false);

        sportListAdd = new SportListAdd();

        plusBtn = viewgroup.findViewById(R.id.btnPlus);


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contain , sportListAdd ).commit();


            }
        });


        // 반드시 생성해서 어댑터에 넘겨야 함
        dtos = new ArrayList<>();
        recyclerView = viewgroup.findViewById(R.id.timerRecyclerView);
        // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(
                getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView2 = viewgroup.findViewById(R.id.timerYetrecyclerView);
        // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager2 = new
                LinearLayoutManager(
                getActivity(), RecyclerView.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);

        recyclerView3 = viewgroup.findViewById(R.id.timerComrecyclerView);
        // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager3 = new
                LinearLayoutManager(
                getActivity(), RecyclerView.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);



        CommonMethod commonMethod = new CommonMethod();


        // 서버에 member들 데이터를 요청
        commonMethod.setParams("id" , loginDto.getId());
        commonMethod.getData("timerList", new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                // 서버에서 넘어온 데이터를 받는다
                Gson gson = new Gson();
                dtos = gson.fromJson(response.body(),
                        new TypeToken<ArrayList<TimerListDTO>>() {}.getType());

                Log.d(TAG, "onResponse: " + response.body());


                if (activityNum == 1) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    adapter = new TimerReadyAdapter(getActivity(), dtos, mainActivity);
                    recyclerView.setAdapter(adapter);
                }

                if( activityNum == 2 ) {
                    // 어댑터 객체 생성
                    CenterDetailActivity centerDetailActivity =(CenterDetailActivity) getActivity();
                    adapter = new TimerReadyAdapter(getActivity(), dtos , centerDetailActivity);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                }






            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        commonMethod.getData("timerComReadeList", new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                // 서버에서 넘어온 데이터를 받는다
                Gson gson = new Gson();

                dtos2 = gson.fromJson(response.body(),
                        new TypeToken<ArrayList<TimerListDTO2>>() {}.getType());
                timerReadyDTOS = new ArrayList<>();
                for ( int i = 0; i < dtos2.size(); i++  ) {
                    if ( dtos2.get(i).getReference() == 0 ) {
                        timerReadyDTOS.add(dtos2.get(i));
                    }

                }
                timerComDTOS = new ArrayList<>();
                for ( int i = 0 ; i < dtos2.size(); i++) {
                    if( dtos2.get(i).getReference() == 1 ) {
                        timerComDTOS.add(dtos2.get(i));
                    }
                }



                Log.d(TAG, "onResponse: " + response.body());


                if (activityNum == 1) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    adapter2 = new TimerReady2Adapter(getActivity(), timerReadyDTOS, mainActivity);
                    recyclerView2.setAdapter(adapter2);
                }

                if( activityNum == 2 ) {
                    // 어댑터 객체 생성
                    CenterDetailActivity centerDetailActivity =(CenterDetailActivity) getActivity();
                    adapter2 = new TimerReady2Adapter(getActivity(), timerReadyDTOS , centerDetailActivity);
                    recyclerView2.setAdapter(adapter2);
                    //adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        commonMethod.getData("timerComReadeList", new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                // 서버에서 넘어온 데이터를 받는다
                Gson gson = new Gson();

                dtos2 = gson.fromJson(response.body(),
                        new TypeToken<ArrayList<TimerListDTO2>>() {}.getType());

                timerComDTOS = new ArrayList<>();
                for ( int i = 0 ; i < dtos2.size(); i++) {
                    if( dtos2.get(i).getReference() == 1 ) {
                        timerComDTOS.add(dtos2.get(i));
                    }
                }


                Log.d(TAG, "onResponse: " + response.body());


                if (activityNum == 1) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    adapter3 = new TimerReady3Adapter(getActivity(), timerComDTOS, mainActivity);
                    recyclerView3.setAdapter(adapter3);
                }

                if( activityNum == 2 ) {
                    // 어댑터 객체 생성
                    CenterDetailActivity centerDetailActivity =(CenterDetailActivity) getActivity();
                    adapter3 = new TimerReady3Adapter(getActivity(), timerComDTOS , centerDetailActivity);
                    recyclerView3.setAdapter(adapter3);
                    //adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



        return viewgroup;
    }

    public void changeAdapterToFrag(TimerSetting timerSetting, MainActivity mainActivity) {
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.contain , timerSetting).commit();


    }public void changeAdapterToFrag2(TimerSetting timerSetting, CenterDetailActivity centerDetailActivity) {
        centerDetailActivity.getSupportFragmentManager().beginTransaction().replace(R.id.contain2 , timerSetting).commit();



    }

    public void changeAdapterToFrag3(Timer timer, MainActivity mainActivity) {
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.contain , timer).commit();


    }public void changeAdapterToFrag4(Timer timer, CenterDetailActivity centerDetailActivity) {
        centerDetailActivity.getSupportFragmentManager().beginTransaction().replace(R.id.contain2 , timer).commit();



    }

}
