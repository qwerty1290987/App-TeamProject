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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myteamproject_chanmin.CenterDetailActivity;
import com.example.myteamproject_chanmin.CenterFind;
import com.example.myteamproject_chanmin.DTO.GymDTO;
import com.example.myteamproject_chanmin.JoinFrag;
import com.example.myteamproject_chanmin.LoginFrag;
import com.example.myteamproject_chanmin.MainActivity;
import com.example.myteamproject_chanmin.R;
import com.example.myteamproject_chanmin.TrainerDetailFrag;

import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;

import java.util.ArrayList;

public class GymAdapter  extends
        RecyclerView.Adapter<GymAdapter.ViewHolder>{
    private static final String TAG = "main:SingerAdapter";

    TextView name_text , telephone_text;

    MainActivity mainActivity;
    CenterDetailActivity centerDetailActivity;

    // 메인에게 넘겨받는것 -> 반드시 : Context, ArrayList<DTO>
    Context context;
    ArrayList<GymDTO> dtos;

    LayoutInflater inflater;

    // 생성자로 메인에서 넘겨받은것들을 연결
    public GymAdapter(Context context, ArrayList<GymDTO> dtos ) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);
        this.mainActivity = mainActivity;

    }


    ////////////////////////////////////////////////////
    // 매소드는 여기에 만든다
    // dtos에 dto를 추가하는 매소드
    public void addDto(GymDTO dto){
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
        View itemView = inflater.inflate(R.layout.gymview,
                parent, false);




        return new ViewHolder(itemView);
    }

    // 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        // dtos에 있는 데이터를 각각 불러온다
        GymDTO dto = dtos.get(position);
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를
        // 사용하여 데이터를 셋팅시킨다
        holder.setDto(dto);




        if (activityNum == 1 ) {
            // 리싸이클러뷰에 항목을 선택했을때 그 항목을 가져오는 리스너
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GymDTO dto1 = dtos.get(position);
                    Toast.makeText(context,
                            "이름 : " + dto1.getGym_name(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, CenterDetailActivity.class);
                    intent.putExtra("gym_id", dto1.getGym_id());
                    intent.putExtra("gym_name", dto1.getGym_name());
                    intent.putExtra("gym_address", dto1.getAddress());
                    intent.putExtra("telephone_number", dto1.getTelephone_number());
                    intent.putExtra("gym_price", dto1.getGym_price());
                    intent.putExtra("gym_picture", dto1.getGym_picture());
                    context.startActivity(intent);


                }
            });
        } else if ( activityNum == 2 ) {

            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GymDTO dto1 = dtos.get(position);
                    Toast.makeText(context,
                            "이름 : " + dto1.getGym_name(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, CenterDetailActivity.class);

                    intent.putExtra("gym_id", dto1.getGym_id());
                    intent.putExtra("gym_name", dto1.getGym_name());
                    intent.putExtra("gym_address", dto1.getAddress());
                    intent.putExtra("telephone_number", dto1.getTelephone_number());
                    intent.putExtra("gym_price", dto1.getGym_price());
                    intent.putExtra("gym_picture", dto1.getGym_picture());
                    context.startActivity(intent);
                }
            });

        }

    }

    // dtos에 저장된 dto 갯수
    @Override
    public int getItemCount() {
        return dtos.size();
    }

    // 3. xml 파일에서 사용된 모든 변수를 adapter에서 클래스로 선언한다
    public class ViewHolder extends RecyclerView.ViewHolder{
        // memberview.xml 에서 사용된 모든 위젯을 정의한다
        TextView tvCenterName, tvTelephoneNumber ,tvAddress;
        ImageView ivImage;
        LinearLayout parentLayout;


        // singerview.xml에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            parentLayout = itemView.findViewById(R.id.parentLayout);
            tvCenterName = itemView.findViewById(R.id.tvCenterName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvTelephoneNumber = itemView.findViewById(R.id.tvTelephoneNumber);
            ivImage = itemView.findViewById(R.id.ivImage);

        }

        // singerview에 데이터를 연결시키는 매소드를 만든다
        public void setDto(GymDTO dto){
            tvCenterName.setText(dto.getGym_name());
            tvAddress.setText(dto.getAddress());
            tvTelephoneNumber.setText(dto.getTelephone_number());
            Glide.with(itemView).load(dto.getGym_picture())

                    .placeholder(R.drawable.face)
                    .into(ivImage);

            //ivImage.setImageResource(dto.getProfile());
        }

    }






}
