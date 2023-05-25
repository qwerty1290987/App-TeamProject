package com.example.myteamproject_chanmin;


import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.ipConfig;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDTO;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDedailNum;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myteamproject_chanmin.Adapter.GymAdapter;
import com.example.myteamproject_chanmin.Adapter.TrainerAdapter;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.GymDTO;
import com.example.myteamproject_chanmin.DTO.MemberDTO;
import com.example.myteamproject_chanmin.DTO.TrainerDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerDetailFrag extends Fragment {

    private static final String TAG = "main:LoginFrag";

    ImageView trainer_image;
    TextView trainer_name , price , phoneNumber;

    RecyclerView gRecyclerView;
    ArrayList<GymDTO> dtos;
    GymAdapter gymAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup  = (ViewGroup) inflater.inflate(R.layout.trainerfind_detail,
                container ,false);


            trainer_image = viewGroup.findViewById(R.id.trainer_picture_detail);
            trainer_name = viewGroup.findViewById(R.id.trainrName_detail);
            price = viewGroup.findViewById(R.id.price_detail);
            phoneNumber = viewGroup.findViewById(R.id.trainer_phonerNumber_detail);


            Glide.with(this).load(trainerDTO.getTrainer_picture())
                    .placeholder(R.drawable.face)
                    .into(trainer_image);
            trainer_name.setText(trainerDTO.getTrainer_name());
            price.setText("PT 이용료 : " + trainerDTO.getPrice() + "원 (1회 기준)");
            phoneNumber.setText("트레이너 연락처 : " + trainerDTO.getPhone_number());


            trainerDedailNum = 0;

        dtos = new ArrayList<>();
        gRecyclerView = viewGroup.findViewById(R.id.gRecylerView);
        // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(
                getActivity(), RecyclerView.VERTICAL, false);
        gRecyclerView.setLayoutManager(layoutManager);

        // 서버에 member들 데이터를 요청
        CommonMethod commonMethod = new CommonMethod();
        commonMethod.setParams("gym" , "아아아아");
        commonMethod.setParams("gym_name" , trainerDTO.getGym_name());
        commonMethod.getData("trainersgym", new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.d(TAG, "onResponse: " + response.body());

                // 서버에서 넘어온 데이터를 받는다
                Gson gson = new Gson();
                dtos = gson.fromJson(response.body(),
                        new TypeToken<ArrayList<GymDTO>>() {}.getType());

                // 제대로 데이터가 입력됐는지 확인
                for(GymDTO dto : dtos){
                    // profile 경로를 적어준다
                    dto.setGym_picture(ipConfig + "resources/" + dto.getGym_picture());
                    Log.d(TAG, "사진 경로" + dto.getGym_picture());
                }

                Log.d(TAG, "onResponse: dtos.size() => " + dtos.size());

                // 어댑터 객체 생성
                gymAdapter = new GymAdapter(getActivity(), dtos  );
                gRecyclerView.setAdapter(gymAdapter);
                //adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }



        });




        return viewGroup;

    }
}