package com.example.myteamproject_chanmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.BoardListDTO;

import static com.example.myteamproject_chanmin.Common.CommonMethod.bottomVisibility;
import static com.example.myteamproject_chanmin.Common.CommonMethod.loginDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComunityWrite extends Fragment {


    EditText etTitle , etBody ;

    Button resistBtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.comunity_write , container , false);

        etTitle = viewGroup.findViewById(R.id.etBoradTitle);
        etBody = viewGroup.findViewById(R.id.etBoradBody);


        resistBtn = viewGroup.findViewById(R.id.resistBtn);

        resistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonMethod commonMethod = new CommonMethod();
                commonMethod.setParams("id" , loginDto.getId() );
                commonMethod.setParams("title" , etTitle.getText());
                commonMethod.setParams("body" ,  etBody.getText());
                commonMethod.getData("updateBoard", new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        bottomVisibility = 1;
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.board_container ,
                                new Comunity()).commit();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


            }
        });

        return viewGroup;
    }
}
