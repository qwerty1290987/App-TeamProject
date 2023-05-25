package com.example.myteamproject_chanmin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.loginDto;
import static com.example.myteamproject_chanmin.Common.CommonMethod.timerListReadyStaticDTO;
import static com.example.myteamproject_chanmin.Common.CommonMethod.timerListStaticDTO;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myteamproject_chanmin.Adapter.TimerReadyAdapter;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.TimerListDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimerSetting extends Fragment {

    LinearLayout timeCountSettingLV, timeCountLV;
    TextView hourET, minuteET, secondET;
    TextView hourTV, minuteTV, secondTV, finishTV ,title;
    Button startBtn, stopBtn;
    int hour, minute, second;

    com.example.myteamproject_chanmin.Timer timerFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.timerset , container , false);

        title = viewGroup.findViewById(R.id.titleText);
        title.setText(timerListReadyStaticDTO.getTitle());

        timerFrag = new com.example.myteamproject_chanmin.Timer();

        Button comButton = viewGroup.findViewById(R.id.completeBtn);
        comButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonMethod commonMethod = new CommonMethod();
                commonMethod.setParams("title" ,  timerListReadyStaticDTO.getTitle());
                commonMethod.setParams("id" , loginDto.getId());
                commonMethod.getData("timerUpdate", new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

                if (activityNum == 1) {

                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.contain , new com.example.myteamproject_chanmin.Timer() ).commit();


                }else if ( activityNum == 2 ) {

                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.contain2 , new com.example.myteamproject_chanmin.Timer() ).commit();

                }

            }
        });


        timeCountSettingLV = viewGroup.findViewById(R.id.timeCountSettingLV);
        timeCountLV = viewGroup.findViewById(R.id.timeCountLV);

        hourET = viewGroup.findViewById(R.id.hourET);
        minuteET =viewGroup.findViewById(R.id.minuteET);
        secondET = viewGroup.findViewById(R.id.secondET);

        hourTV = viewGroup.findViewById(R.id.hourTV);
        minuteTV = viewGroup.findViewById(R.id.minuteTV);
        secondTV = viewGroup.findViewById(R.id.secondTV);
        finishTV = viewGroup.findViewById(R.id.finishTV);

        hourET.setText(timerListReadyStaticDTO.getHour());
        minuteET.setText(timerListReadyStaticDTO.getMinuit());
        secondET.setText(timerListReadyStaticDTO.getSecond());

        startBtn = viewGroup.findViewById(R.id.startBtn);
        stopBtn = viewGroup.findViewById(R.id.stopBtn);

        //정지버튼 이벤트 처리
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();
            }
        });

        // 시작버튼 이벤트 1처리
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeCountSettingLV.setVisibility(View.GONE);
                timeCountLV.setVisibility(View.VISIBLE);

                hourTV.setText(timerListReadyStaticDTO.getHour());
                minuteTV.setText(timerListReadyStaticDTO.getMinuit());
                secondTV.setText(timerListReadyStaticDTO.getSecond());

                if (hourET.getText().toString().equals("")) {
                    hourET.setText("0");
                }
                if (minuteET.getText().toString().equals("")) {
                    minuteET.setText("0");
                }
                if (secondET.getText().toString().equals("")) {
                    secondET.setText("0");
                }

                hour = Integer.parseInt(timerListReadyStaticDTO.getHour());
                minute = Integer.parseInt(timerListReadyStaticDTO.getMinuit());
                second = Integer.parseInt(timerListReadyStaticDTO.getSecond());

                java.util.Timer timer = new Timer();
              /*  TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        // 반복실행할 구문

                        // 0초 이상이면
                        if (second != 0) {
                            //1초씩 감소
                            second--;

                            // 0분 이상이면
                        } else if (minute != 0) {
                            // 1분 = 60초
                            second = 60;
                            second--;
                            minute--;

                            // 0시간 이상이면
                        } else if (hour != 0) {
                            // 1시간 = 60분
                            second = 60;
                            minute = 60;
                            second--;
                            minute--;
                            hour--;
                        }

                        //시, 분, 초가 10이하(한자리수) 라면
                        // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                        if (second <= 9) {
                            secondTV.setText("0" + second);
                        } else {
                            secondTV.setText(Integer.toString(second));
                        }

                        if (minute <= 9) {
                            minuteTV.setText("0" + minute);
                        } else {
                            minuteTV.setText(Integer.toString(minute));
                        }

                        if (hour <= 9) {
                            hourTV.setText("0" + hour);
                        } else {
                            hourTV.setText(Integer.toString(hour));
                        }

                        // 시분초가 다 0이라면 toast를 띄우고 타이머를 종료한다..
                        if (hour == 0 && minute == 0 && second == 0) {
                            timer.cancel();//타이머 종료
                            finishTV.setText("타이머가 종료되었습니다.");
                        }
                    }
                };*/


                final Handler handler = new Handler(){
                    public void handleMessage(Message msg){
                        // 반복실행할 구문

                        // 0초 이상이면
                        if (second != 0) {
                            //1초씩 감소
                            second--;

                            // 0분 이상이면
                        } else if (minute != 0) {
                            // 1분 = 60초
                            second = 60;
                            second--;
                            minute--;

                            // 0시간 이상이면
                        } else if (hour != 0) {
                            // 1시간 = 60분
                            second = 60;
                            minute = 60;
                            second--;
                            minute--;
                            hour--;
                        }

                        //시, 분, 초가 10이하(한자리수) 라면
                        // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                        if (second <= 9) {
                            secondTV.setText("0" + second);
                        } else {
                            secondTV.setText(Integer.toString(second));
                        }

                        if (minute <= 9) {
                            minuteTV.setText("0" + minute);
                        } else {
                            minuteTV.setText(Integer.toString(minute));
                        }

                        if (hour <= 9) {
                            hourTV.setText("0" + hour);
                        } else {
                            hourTV.setText(Integer.toString(hour));
                        }

                        // 시분초가 다 0이라면 toast를 띄우고 타이머를 종료한다..
                        if (hour == 0 && minute == 0 && second == 0) {
                            timer.cancel();//타이머 종료
                            finishTV.setText("타이머가 종료되었습니다.");
                        }

                    }
                };

     //           Timer timer = new Timer(true);
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                    }

                    @Override
                    public boolean cancel() {
                        return super.cancel();
                    }
                };


                timer.schedule(timerTask, 0, 1000);




               }//onclick

            });
                return viewGroup;


    }//onCreacte()
}//class

