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
import com.example.myteamproject_chanmin.DTO.TimerAddDTO;
import com.example.myteamproject_chanmin.DTO.TrainerDTO;

import static com.example.myteamproject_chanmin.Common.CommonMethod.loginDto;
import static com.example.myteamproject_chanmin.Common.CommonMethod.loginNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.timerAddStaticDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportListAdd  extends Fragment {

    EditText etTitle , etHour , etMinuit , etSecond;
    String title , hour , minuit , second;

    Button saveBtn , cancelBtn;

    Timer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.sportlist_add,
                container, false);

        saveBtn = viewGroup.findViewById(R.id.saveBtn);
        cancelBtn = viewGroup.findViewById(R.id.cancleBtn);

        etTitle =  viewGroup.findViewById(R.id.etTitle);
        etHour = viewGroup.findViewById(R.id.hourET);
        etMinuit = viewGroup.findViewById(R.id.minuteET);
        etSecond = viewGroup.findViewById(R.id.secondET);


        timer = new Timer();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                title = etTitle.getText().toString();
                hour = etHour.getText().toString();
                minuit = etMinuit.getText().toString();
                second = etSecond.getText().toString();

                //DTO생성 후 스태틱으로 만든 DTO에 값넣어주기
                TimerAddDTO trainerDTO = new TimerAddDTO(title , hour , minuit , second );
                timerAddStaticDTO = trainerDTO;

                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.contain, timer
                ).commit();



                CommonMethod commonMethod = new CommonMethod();

                commonMethod.setParams("hour" , timerAddStaticDTO.getHour() );
                commonMethod.setParams("minuit" , timerAddStaticDTO.getMinuit() );
                commonMethod.setParams("second" , timerAddStaticDTO.getSecond() );
                commonMethod.setParams("title" , timerAddStaticDTO.getTitle() );
                commonMethod.setParams("id" , loginDto.getId());
                commonMethod.getData("timerSave", new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {



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
