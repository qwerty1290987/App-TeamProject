package com.example.myteamproject_chanmin;

import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.ipConfig;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDTO;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myteamproject_chanmin.Adapter.TrainerAdapter;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.TrainerDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherFind extends Fragment {

    private static final String TAG = "main:SubActivity";


    ArrayList<TrainerDTO> dtos;
    TrainerAdapter adapter;
    Button trainerFindBtn;
    RecyclerView recyclerView2;
    TrainerDetailFrag trainerDetailFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        trainerDetailFrag = new TrainerDetailFrag();

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.teacherfind,
                container, false);

        trainerFindBtn =  viewGroup.findViewById(R.id.teacherFindBtn);
        trainerFindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 반드시 생성해서 어댑터에 넘겨야 함
                dtos = new ArrayList<>();
                recyclerView2 = viewGroup.findViewById(R.id.recyclerView2);
                // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
                LinearLayoutManager layoutManager = new
                        LinearLayoutManager(
                        getActivity(), RecyclerView.VERTICAL, false);
                recyclerView2.setLayoutManager(layoutManager);

                // 서버에 member들 데이터를 요청
                CommonMethod commonMethod = new CommonMethod();
                commonMethod.getData("trainerList", new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Log.d(TAG, "onResponse: " + response.body());

                        // 서버에서 넘어온 데이터를 받는다
                        Gson gson = new Gson();
                        dtos = gson.fromJson(response.body(),
                                new TypeToken<ArrayList<TrainerDTO>>() {}.getType());

                        // 제대로 데이터가 입력됐는지 확인
                        for(TrainerDTO dto : dtos){
                            // profile 경로를 적어준다
                            dto.setTrainer_picture(ipConfig + "resources/" + dto.getTrainer_picture());
                            Log.d(TAG, "사진 경로" + dto.getTrainer_picture());
                        }

                        Log.d(TAG, "onResponse: dtos.size() => " + dtos.size());

                        if (activityNum == 1) {
                            MainActivity mainActivity = (MainActivity) getActivity();
                            adapter = new TrainerAdapter(getActivity(), dtos, mainActivity);
                            recyclerView2.setAdapter(adapter);
                        }

                        if( activityNum == 2 ) {
                            // 어댑터 객체 생성
                            CenterDetailActivity centerDetailActivity =(CenterDetailActivity) getActivity();
                            adapter = new TrainerAdapter(getActivity(), dtos , centerDetailActivity);
                            recyclerView2.setAdapter(adapter);
                            //adapter.notifyDataSetChanged();
                        }



                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });

        return viewGroup;
    }


    public void changeAdapterToFrag(TrainerDetailFrag fragment , MainActivity activity) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.contain , fragment).commit();

    }
    public void changeAdapterToFrag2(TrainerDetailFrag fragment , CenterDetailActivity activity ) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.contain2 , fragment).commit();


    }

}