package com.example.myteamproject_chanmin;

import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.bottomVisibility;
import static com.example.myteamproject_chanmin.Common.CommonMethod.loginDto;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myteamproject_chanmin.Adapter.BoardAdapter;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.BoardListDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComunityMyList extends Fragment {


    private static final String TAG = "ComunityMyList main :";

    ArrayList<BoardListDTO> dtos;
    BoardAdapter bAdapter;
    RecyclerView bRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.comunitymylist , container , false);



        bRecyclerView = viewGroup.findViewById(R.id.bRecyclerView2);
        dtos = new ArrayList<>();

        LinearLayoutManager layoutManager = new
                LinearLayoutManager(
                getActivity(), RecyclerView.VERTICAL, false);
        bRecyclerView.setLayoutManager(layoutManager);


        CommonMethod commonMethod = new CommonMethod();
        commonMethod.setParams("id" , loginDto.getId());
        commonMethod.getData("boardMyList", new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // 서버에서 넘어온 데이터를 받는다

                Gson gson = new Gson();

                dtos = gson.fromJson(response.body(),
                        new TypeToken<ArrayList<BoardListDTO>>() {}.getType());


                Log.d(TAG, "onResponse: " + response.body());

                // 제대로 데이터가 입력됐는지 확인
                for(BoardListDTO dto : dtos){
                    // profile 경로를 적어준다;
                    Log.d(TAG, "사진 경로" + dto.getBoard_title());
                }


                // 어댑터 객체 생성
                if (activityNum == 1) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    bAdapter = new BoardAdapter(getActivity(), dtos, mainActivity);
                    bRecyclerView.setAdapter(bAdapter);
                }

                if( activityNum == 2 ) {
                    // 어댑터 객체 생성
                    CenterDetailActivity centerDetailActivity = (CenterDetailActivity) getActivity();
                    bAdapter = new BoardAdapter(getActivity(), dtos , centerDetailActivity);
                    bRecyclerView.setAdapter(bAdapter);
                    //adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });





        return viewGroup;
    }
}
