package com.example.myteamproject_chanmin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.myteamproject_chanmin.Common.CommonMethod.boardStaticDTO;

public class ComunityRead extends Fragment {



    TextView tvTitle , tvBody;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.comunity_read , container , false);

        tvTitle = viewGroup.findViewById(R.id.tvBoardReadTitle);
        tvBody = viewGroup.findViewById(R.id.tvBoardReadBody);

        tvTitle.setText(boardStaticDTO.getBoard_title());
        tvBody.setText(boardStaticDTO.getBoard_body());


        return viewGroup;
    }
}
