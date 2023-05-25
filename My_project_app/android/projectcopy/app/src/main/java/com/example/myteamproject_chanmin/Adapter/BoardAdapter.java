package com.example.myteamproject_chanmin.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myteamproject_chanmin.CenterDetailActivity;
import com.example.myteamproject_chanmin.Comunity;
import com.example.myteamproject_chanmin.ComunityRead;
import com.example.myteamproject_chanmin.DTO.BoardListDTO;
import com.example.myteamproject_chanmin.MainActivity;
import com.example.myteamproject_chanmin.R;


import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.boardStaticDTO;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDedailNum;

import java.util.ArrayList;

public class BoardAdapter extends
        RecyclerView.Adapter<BoardAdapter.ViewHolder>{
    private static final String TAG = "main:SingerAdapter";



    MainActivity mainActivity;
    CenterDetailActivity centerDetailActivity;

    Comunity comunity;

    TextView tvBoardTitle;

    // 메인에게 넘겨받는것 -> 반드시 : Context, ArrayList<DTO>
    Context context;
    ArrayList<BoardListDTO> dtos;

    LayoutInflater inflater;

    // 생성자로 액티비티에서 넘겨받은것들을 연결
    public BoardAdapter(Context context, ArrayList<BoardListDTO> dtos  ) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);

    }

    public BoardAdapter(Context context, ArrayList<BoardListDTO> dtos  , MainActivity mainActivity) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);
        this.mainActivity = mainActivity;

    }


    public BoardAdapter(Context context, ArrayList<BoardListDTO> dtos  , CenterDetailActivity centerDetailActivity) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);
        this.centerDetailActivity = centerDetailActivity;

    }

    ////////////////////////////////////////////////////
    // 매소드는 여기에 만든다
    // dtos에 dto를 추가하는 매소드
    public void addDto(BoardListDTO dto){
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
        View itemView = inflater.inflate(R.layout.boardview,
                parent, false);


        return new ViewHolder(itemView);
    }

    // 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        // dtos에 있는 데이터를 각각 불러온다
        BoardListDTO dto = dtos.get(position);
        Log.d(TAG, "onBindViewHolder: title: " + dto.getBoard_title() );
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를
        // 사용하여 데이터를 셋팅시킨다
        holder.setDto(dto);


        // 리싸이클러뷰에 항목을 선택했을때 그 항목을 가져오는 리스너
        holder.bPparentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardListDTO dto1 = dtos.get(position);


                comunity = new Comunity();
                boardStaticDTO = dto1;


                if ( activityNum == 1 ) {
                   comunity.boardChangeFrag(new ComunityRead() , mainActivity);
              }
               if ( activityNum == 2  ) {
                   comunity.boardChangeFrag(new ComunityRead() , centerDetailActivity);
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

        LinearLayout bPparentLayout;
        TextView tvBoardTitle;

        // singerview.xml에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bPparentLayout = itemView.findViewById(R.id.bParentLayout);
            tvBoardTitle = itemView.findViewById(R.id.tvBoardTitle);

        }

        // singerview에 데이터를 연결시키는 매소드를 만든다
        public void setDto(BoardListDTO dto){
            tvBoardTitle.setText(dto.getBoard_title());


            //ivImage.setImageResource(dto.getProfile());
        }

    }






}
