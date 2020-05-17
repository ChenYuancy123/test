package com.example.postgraduate_v1.personfragment_activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.adapter.DynamicAdapter;
import com.example.postgraduate_v1.bmob.Dynamic_bmob;
import com.example.postgraduate_v1.mainfragment_activity.Commodity_xiangxi_Activity;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SelfCreate_DynamicActivity extends AppCompatActivity {

    private ListView lv_selfCreateDynamic;

    private List<Dynamic_bmob> dynamicList = new ArrayList<Dynamic_bmob>();
    private DynamicAdapter dynamicAdapter;

    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(SelfCreate_DynamicActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_self_create__dynamic);

        Bmob.initialize(SelfCreate_DynamicActivity.this,"1d06cc42b24841bcda24570e21b24998");

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();

        String objectId = uSharedPreferences.getString("objectId","");
        Log.i("SelfCreate",objectId);

        //初始化组件
        init();

        BmobQuery<Dynamic_bmob> bmobQuery = new BmobQuery();
        bmobQuery.addWhereEqualTo("postId",objectId);
        bmobQuery.findObjects(new FindListener<Dynamic_bmob>() {
            @Override
            public void done(List<Dynamic_bmob> list, BmobException e) {
                if(e==null){
                    dynamicAdapter = new DynamicAdapter(SelfCreate_DynamicActivity.this,list);
                    lv_selfCreateDynamic.setAdapter(dynamicAdapter);
                }else{
                    Toast.makeText(SelfCreate_DynamicActivity.this, "查询失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        bmobQuery.findObjects(new FindListener() {
//            @Override
//            public void done(List list, BmobException e) {
//                if(e==null){
//                    dynamicAdapter = new DynamicAdapter(SelfCreate_DynamicActivity.this,list);
//                    lv_selfCreateDynamic.setAdapter(dynamicAdapter);
//                }else{
//                    Toast.makeText(SelfCreate_DynamicActivity.this, "查询失败！", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void done(Object o, Object o2) {
//
//            }
//        });


    }

    public void init(){
        lv_selfCreateDynamic = findViewById(R.id.lv_selfCreateDynamic);
    }
}