package com.example.myteamproject_chanmin.Adapter;

import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.timerListStaticDTO;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDTO;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDedailNum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myteamproject_chanmin.CenterDetailActivity;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.TimerListDTO;
import com.example.myteamproject_chanmin.DTO.TrainerDTO;
import com.example.myteamproject_chanmin.MainActivity;
import com.example.myteamproject_chanmin.R;
import com.example.myteamproject_chanmin.TeacherFind;
import com.example.myteamproject_chanmin.Timer;
import com.example.myteamproject_chanmin.TimerSetting;
import com.example.myteamproject_chanmin.TrainerDetailFrag;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimerReadyAdapter extends
        RecyclerView.Adapter<TimerReadyAdapter.ViewHolder>{
    private static final String TAG = "main:SingerAdapter";

    TextView tvTitle , tvHour , tvMinuit , tvSecond;
    MainActivity mainActivity;
    CenterDetailActivity centerDetailActivity;

    Timer timerFarg;

    // 메인에게 넘겨받는것 -> 반드시 : Context, ArrayList<DTO>
    Context context;
    ArrayList<TimerListDTO> dtos;

    LayoutInflater inflater;

    // 생성자로 액티비티에서 넘겨받은것들을 연결
    public TimerReadyAdapter(Context context, ArrayList<TimerListDTO> dtos  ) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);

    }

    public TimerReadyAdapter(Context context, ArrayList<TimerListDTO> dtos  , MainActivity mainActivity) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);
        this.mainActivity = mainActivity;

    }


    public TimerReadyAdapter(Context context, ArrayList<TimerListDTO> dtos  , CenterDetailActivity centerDetailActivity) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);
        this.centerDetailActivity = centerDetailActivity;

    }

    ////////////////////////////////////////////////////
    // 매소드는 여기에 만든다
    // dtos에 dto를 추가하는 매소드
    public void addDto(TimerListDTO dto){
        dtos.add(dto);
    }

    // dtos의 특정 위치에 있는 dto를 삭제하는 매소드
    public void delDto(int position){
        dtos.remove(position);
    }

    ////////////////////////////////////////////////////

    // 화면을 인플레이트 시킨다 (붙인다)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.timerview,
                parent, false);

        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvHour = itemView.findViewById(R.id.tvHour);
        tvMinuit = itemView.findViewById(R.id.tvMinuit);
        tvSecond = itemView.findViewById(R.id.tvSecond);

        return new ViewHolder(itemView);
    }

    // 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        // dtos에 있는 데이터를 각각 불러온다
        TimerListDTO dto = dtos.get(position);
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를
        // 사용하여 데이터를 셋팅시킨다
        holder.setDto(dto);

        holder.parentLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerListDTO dto1 = dtos.get(position);

                timerFarg = new Timer();
                timerListStaticDTO = dto1;

                if ( activityNum == 1  ) {
                    timerFarg.changeAdapterToFrag(new TimerSetting(), mainActivity);
                }
                if ( activityNum == 2  ) {
                    timerFarg.changeAdapterToFrag2(new TimerSetting(), centerDetailActivity );
                }
            }
        });

        holder.timerTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimerListDTO dto1 = dtos.get(position);
                timerListStaticDTO = dto1;


                CommonMethod commonMethod = new CommonMethod();
                commonMethod.setParams("title" , dto1.getTitle());
                commonMethod.setParams("id" , dto1.getId());
                commonMethod.getData("timerDelete", new Callback<String>() {
                      @Override
                      public void onResponse(Call<String> call, Response<String> response) {

                      }

                      @Override
                      public void onFailure(Call<String> call, Throwable t) {

                      }
                });

                timerFarg = new Timer();


                if ( activityNum == 1  ) {
                    timerFarg.changeAdapterToFrag3(new Timer(), mainActivity);
                }
                if ( activityNum == 2  ) {
                    timerFarg.changeAdapterToFrag4(new Timer(), centerDetailActivity );
                }


            }
        });




    }

    // dtos에 저장된 dto 갯수
    @Override
    public int getItemCount() {
        return dtos.size();
    }

    // 3. xml 파일에서 사용된 모든 변수를 adapter에서 클래스로 선언한다
    public class ViewHolder extends RecyclerView.ViewHolder{
        // memberview.xml 에서 사용된 모든 위젯을 정의한다
        TextView  tvTitle , tvHour, tvMinuit , tvSecond;
        LinearLayout parentLayout3;
        ImageButton timerTrash;

        // singerview.xml에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout3 = itemView.findViewById(R.id.parentLayout3);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvHour = itemView.findViewById(R.id.tvHour);
            tvMinuit = itemView.findViewById(R.id.tvMinuit);
            tvSecond = itemView.findViewById(R.id.tvSecond);
            timerTrash = itemView.findViewById(R.id.timerTrash);

        }

        // singerview에 데이터를 연결시키는 매소드를 만든다
        public void setDto(TimerListDTO dto){
            tvTitle.setText(dto.getTitle());
            tvHour.setText(dto.getHour());
            tvMinuit.setText(dto.getMinuit());
            tvSecond.setText(dto.getSecond());

        }

    }






}
