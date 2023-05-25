package com.example.myteamproject_chanmin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDTO;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDedailNum;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myteamproject_chanmin.CenterDetailActivity;
import com.example.myteamproject_chanmin.DTO.GymDTO;
import com.example.myteamproject_chanmin.DTO.TrainerDTO;
import com.example.myteamproject_chanmin.LoginFrag;
import com.example.myteamproject_chanmin.MainActivity;
import com.example.myteamproject_chanmin.R;
import com.example.myteamproject_chanmin.TeacherFind;
import com.example.myteamproject_chanmin.TrainerDetailFrag;

import java.util.ArrayList;

public class TrainerAdapter extends
        RecyclerView.Adapter<TrainerAdapter.ViewHolder>{
    private static final String TAG = "main:SingerAdapter";

    TextView tvTrainerName , tvPhoneNumber;
    TeacherFind teacherFind;
    MainActivity mainActivity;
    CenterDetailActivity centerDetailActivity;

    // 메인에게 넘겨받는것 -> 반드시 : Context, ArrayList<DTO>
    Context context;
    ArrayList<TrainerDTO> dtos;

    LayoutInflater inflater;

    // 생성자로 액티비티에서 넘겨받은것들을 연결
    public TrainerAdapter(Context context, ArrayList<TrainerDTO> dtos  ) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);

    }

    public TrainerAdapter(Context context, ArrayList<TrainerDTO> dtos  , MainActivity mainActivity) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);
        this.mainActivity = mainActivity;

    }


    public TrainerAdapter(Context context, ArrayList<TrainerDTO> dtos  , CenterDetailActivity centerDetailActivity) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);
        this.centerDetailActivity = centerDetailActivity;

    }

    ////////////////////////////////////////////////////
    // 매소드는 여기에 만든다
    // dtos에 dto를 추가하는 매소드
    public void addDto(TrainerDTO dto){
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
        View itemView = inflater.inflate(R.layout.trainerview,
                parent, false);

        tvTrainerName = itemView.findViewById(R.id.tvTrainerName2);
        tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber2);

        return new ViewHolder(itemView);
    }

    // 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        // dtos에 있는 데이터를 각각 불러온다
        TrainerDTO dto = dtos.get(position);
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를
        // 사용하여 데이터를 셋팅시킨다
        holder.setDto(dto);


        // 리싸이클러뷰에 항목을 선택했을때 그 항목을 가져오는 리스너
        holder.parentLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainerDTO dto1 = dtos.get(position);

                teacherFind = new TeacherFind();
                trainerDTO = dto1;

               if ( activityNum == 1 && trainerDedailNum == 0 ) {
                   teacherFind.changeAdapterToFrag(new TrainerDetailFrag(), mainActivity);
              }
               if ( activityNum == 2  && trainerDedailNum == 0 ) {
                   teacherFind.changeAdapterToFrag2(new TrainerDetailFrag(), centerDetailActivity );
               }
               if ( activityNum == 2  && trainerDedailNum == 1 ) {
                       teacherFind.changeAdapterToFrag2(new TrainerDetailFrag(), centerDetailActivity );
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
        TextView  tvTrainerName , tvGymName, tvPhoneNumber;
        ImageView ivImage2;
        LinearLayout parentLayout2;

        // singerview.xml에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout2 = itemView.findViewById(R.id.parentLayout2);
            tvGymName = itemView.findViewById(R.id.tvGymName2);
            tvTrainerName = itemView.findViewById(R.id.tvTrainerName2);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber2);
            ivImage2 = itemView.findViewById(R.id.ivImage2);

        }

        // singerview에 데이터를 연결시키는 매소드를 만든다
        public void setDto(TrainerDTO dto){
            tvGymName.setText(dto.getGym_name());
            tvTrainerName.setText(dto.getTrainer_name());
            tvPhoneNumber.setText(dto.getPhone_number());
            Glide.with(itemView).load(dto.getTrainer_picture())

                    .placeholder(R.drawable.face)
                    .into(ivImage2);

            //ivImage.setImageResource(dto.getProfile());
        }

    }






}
