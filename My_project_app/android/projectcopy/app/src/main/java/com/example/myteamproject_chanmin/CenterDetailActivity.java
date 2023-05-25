package com.example.myteamproject_chanmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.myteamproject_chanmin.Common.CommonMethod.activityNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.ipConfig;
import static com.example.myteamproject_chanmin.Common.CommonMethod.loginNum;
import static com.example.myteamproject_chanmin.Common.CommonMethod.trainerDedailNum;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myteamproject_chanmin.Adapter.GymAdapter;
import com.example.myteamproject_chanmin.Adapter.TrainerAdapter;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.GymDTO;
import com.example.myteamproject_chanmin.DTO.TrainerDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CenterDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    LoginFrag loginFrag;
    JoinFrag joinFrag;

    WelcomePage welcomePage;

    CenterFind centerFind;
    TeacherFind teacherFind;
    ToolsFind toolsFind;
    Timer timer;
    Comunity comunity;

    Toolbar toolbar;

    Button loginBtn , joinBtn , logoutBtn , myPageBtn;



    MainActivity mActivity;

    FloatingActionButton fab;
    NavigationView nav_view;
    DrawerLayout draw_layout;


    ImageView gym_picture;
    TextView gym_name;
    TextView gym_price;
    TextView gym_address;

    RecyclerView tRecyclerView;
    ArrayList<TrainerDTO> dtos;
    TrainerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_detail);





        // 프래그먼트 객체를 생성하고 프래임레이아웃에 초기화를 시킨다
        welcomePage = new WelcomePage();

        centerFind = new CenterFind();
        teacherFind = new TeacherFind();
        toolsFind = new ToolsFind();
        timer = new Timer();
        comunity = new Comunity();
        joinFrag = new JoinFrag();
        loginFrag = new LoginFrag();

        nav_view = findViewById(R.id.nav_view);
        // implement Listener 할때는 반드시 아래와 같이 정의한다
        nav_view.setNavigationItemSelectedListener(this);

        //HeaderView 에 접근 메소드
        View headerView = nav_view.getHeaderView(0);

        loginBtn = headerView.findViewById(R.id.loginBtn);
        joinBtn = headerView.findViewById(R.id.joinBtn);


        if (loginNum == 1) {
            //로그인 성공시 네이게이션 헤더 메뉴의 버튼 세팅 변경
            headerView.findViewById(R.id.myPageBtn).setVisibility(View.VISIBLE);
            headerView.findViewById(R.id.logoutBtn).setVisibility(View.VISIBLE);
            headerView.findViewById(R.id.loginBtn).setVisibility(View.GONE);
            headerView.findViewById(R.id.joinBtn).setVisibility(View.GONE);
        }


        mActivity = new MainActivity();


        logoutBtn = headerView.findViewById(R.id.logoutBtn);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 성공시 네이게이션 헤더 메뉴의 버튼 세팅 변경
                headerView.findViewById(R.id.myPageBtn).setVisibility(View.GONE);
                headerView.findViewById(R.id.logoutBtn).setVisibility(View.GONE);
                headerView.findViewById(R.id.loginBtn).setVisibility(View.VISIBLE);
                headerView.findViewById(R.id.joinBtn).setVisibility(View.VISIBLE);
                loginNum = 0;
            }
        });


        //headerView에 로그인버튼클릭시
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contain2 , loginFrag).commit();
                // 메뉴 선택후 드로어가 사라지게 한다
                draw_layout.closeDrawer(GravityCompat.START);


            }
        });
        //headerView에 회원가입버튼클릭시
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contain2 , joinFrag).commit();
                // 메뉴 선택후 드로어가 사라지게 한다
                draw_layout.closeDrawer(GravityCompat.START);
            }
        });

        // toolbar 적용 : res->theme->NoActionBar로 변경
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // drawerLayout을 찾아서 토클 리스너를 붙인다
        draw_layout = findViewById(R.id.draw_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, draw_layout, toolbar,
                R.string.draw_open, R.string.draw_close );
        draw_layout.addDrawerListener(toggle);
        toggle.syncState();









             Intent intent = getIntent(); //전달할 데이터를 받을 Intent

             gym_picture = findViewById(R.id.gym_picture);
             gym_name = findViewById(R.id.gym_name);
             gym_price = findViewById(R.id.gym_price);
             gym_address = findViewById(R.id.gym_address);


             Glide.with(this).load(intent.getStringExtra("gym_picture"))
                     .placeholder(R.drawable.face)
                     .into(gym_picture);

             // gym_picture.setipConfig + "resources/" + intent.getStringExtra("gym_picture";
             gym_name.setText(intent.getStringExtra("gym_name"));
             gym_price.setText("센터 이용료 : " + intent.getStringExtra("gym_price") + "원 (한달)");
             gym_address.setText("센터 주소 : " + intent.getStringExtra("gym_address"));

             activityNum = 2;



        dtos = new ArrayList<>();
        tRecyclerView = findViewById(R.id.tRecyclerView);
        // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(
                this, RecyclerView.VERTICAL, false);
        tRecyclerView.setLayoutManager(layoutManager);

        // 서버에 member들 데이터를 요청
        CommonMethod commonMethod = new CommonMethod();
        commonMethod.setParams("gym_id" , intent.getStringExtra("gym_id"));
        commonMethod.getData("trainerofgym", new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                // 서버에서 넘어온 데이터를 받는다
                Gson gson = new Gson();
                dtos = gson.fromJson(response.body(),
                        new TypeToken<ArrayList<TrainerDTO>>() {}.getType());

                // 제대로 데이터가 입력됐는지 확인
                for(TrainerDTO dto : dtos){
                    // profile 경로를 적어준다
                    dto.setTrainer_picture(ipConfig + "resources/" + dto.getTrainer_picture());

                }

                trainerDedailNum = 1;

                // 어댑터 객체 생성

                adapter = new TrainerAdapter(CenterDetailActivity.this ,dtos , CenterDetailActivity.this  );
                tRecyclerView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }



        });




    }




        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item ) {
            // 클릭한 아이템의 id를 얻는
            // 클릭한 아이템의 id를 얻는다

            int id = item.getItemId();
            switch (id){
                case R.id.nav_home:
                    Intent intent = new Intent(this , MainActivity.class);
                    startActivity(intent);

                    break;
                case R.id.nav_centerfind:
                    onFragmentSelected(" ", centerFind);
                    break;
                case R.id.nav_teacherfind:
                    onFragmentSelected(" ", teacherFind);
                    break;
                case R.id.nav_timer:
                    if (loginNum == 1 ) {
                        onFragmentSelected(" ", timer);
                    }else {
                    Toast.makeText(this, "로그인 시 이용 가능합니다.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.nav_cumunity:
                    onFragmentSelected(" ", comunity);
                    break;

            }

            // 메뉴 선택후 드로어가 사라지게 한다
            draw_layout.closeDrawer(GravityCompat.START);

            return true;
        }
        //                                  화면 순서,  내가 선택한 프래그먼트
        public void onFragmentSelected(String screen, Fragment selFragment){
            // 타이틀 이름 바꿈
            toolbar.setTitle(screen);
            // 선택한 프래그먼트 화면으로 교체
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contain2, selFragment).commit();

        }

        // 뒤로가기를 눌렀을때 만약 드로어 창이 열려있으면 드로어 창을 닫고
        // 아니면 그냥 뒤로가기 원래 작업을 한다(여기서는 앱 종료)
        @Override
        public void onBackPressed() {
            if(draw_layout.isDrawerOpen(GravityCompat.START)){
                draw_layout.closeDrawer(GravityCompat.START);
            }else {
                super.onBackPressed();
            }

        }

        // 위험권한 : 실행시 허용여부를 다시 물어봄
        private void checkDangerousPermissions() {
            String[] permissions = {
                    // 위험권한 내용 : 메니페스트에 권한을 여기에 적음
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.ACCESS_MEDIA_LOCATION,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            int permissionCheck = PackageManager.PERMISSION_GRANTED;
            for (int i = 0; i < permissions.length; i++) {
                permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
                if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                    break;
                }
            }

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                    Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(this, permissions, 1);
                }
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            if (requestCode == 1) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
}