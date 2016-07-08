package com.app.checkdataandaddress;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button btnCheckTime,btnBtnCheckAds;
    private TextView tvTime,tvAds;
    TimePickerView pvTime;
    OptionsPickerView pvOption;
    private PullProvince pullProvince;
    private final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
             if(msg.what==PullProvince.PARSESUCCESS){
                 pvOption.setPicker(options1item,options2item,options3item,true);
                 pvOption.setCyclic(false,false,false);
                 pvOption.setTitle("选择城市");
                 pvOption.setSelectOptions(0,0,0);
                 pvOption.show();
             }
        }
    };

 //   public static ArrayList<ProvinceBean> options1item=new ArrayList<ProvinceBean>();
    public static ArrayList<String> options1item=new ArrayList<String>();
    public static ArrayList<ArrayList<String>> options2item=new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<ArrayList<String>>> options3item=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        pvTime=new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setTime(new Date());//默认当前时间
        pvTime.setCyclic(false);//是否循环滚动
        pvTime.setCancelable(true);
        //弹出时间选择
        btnCheckTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvTime.show();
            }
        });
        //时间选择回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
            }
        });

        pvOption=new OptionsPickerView(this);
//        initPotions();


        btnBtnCheckAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputStream in=MainActivity.this.getResources().getAssets().open("city.xml");
                    pullProvince=new PullProvince(handler);
                    pullProvince.getProvince(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
 //               pvOption.show();

            }
        });

        pvOption.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String txt=options1item.get(options1)
                        +options2item.get(options1).get(option2)
                        +options3item.get(options1).get(option2).get(options3);
                tvAds.setText(txt);
            }
        });

    }

    private void initPotions() {

//选项1
//        options1item.add(new ProvinceBean(0,"广东","广东省，以岭南东道、广南东路得名","其他数据"));
//        options1item.add(new ProvinceBean(1,"湖南","湖南省地处中国中部、长江中游，因大部分区域处于洞庭湖以南而得名湖南","芒果TV"));
//        options1item.add(new ProvinceBean(3,"广西","嗯～～",""));

        //选项2
        ArrayList<String> options2Items_01=new ArrayList<String>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("阳江");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02=new ArrayList<String>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        ArrayList<String> options2Items_03=new ArrayList<String>();
        options2Items_03.add("桂林");
        options2item.add(options2Items_01);
        options2item.add(options2Items_02);
        options2item.add(options2Items_03);

        //选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_03 = new ArrayList<ArrayList<String>>();
        ArrayList<String> options3Items_01_01=new ArrayList<String>();
        options3Items_01_01.add("白云");
        options3Items_01_01.add("天河");
        options3Items_01_01.add("海珠");
        options3Items_01_01.add("越秀");
        options3Items_01.add(options3Items_01_01);
        ArrayList<String> options3Items_01_02=new ArrayList<String>();
        options3Items_01_02.add("南海");
        options3Items_01_02.add("高明");
        options3Items_01_02.add("顺德");
        options3Items_01_02.add("禅城");
        options3Items_01.add(options3Items_01_02);
        ArrayList<String> options3Items_01_03=new ArrayList<String>();
        options3Items_01_03.add("其他");
        options3Items_01_03.add("常平");
        options3Items_01_03.add("虎门");
        options3Items_01.add(options3Items_01_03);
        ArrayList<String> options3Items_01_04=new ArrayList<String>();
        options3Items_01_04.add("其他1");
        options3Items_01_04.add("其他2");
        options3Items_01_04.add("其他3");
        options3Items_01.add(options3Items_01_04);
        ArrayList<String> options3Items_01_05=new ArrayList<String>();
        options3Items_01_05.add("其他1");
        options3Items_01_05.add("其他2");
        options3Items_01_05.add("其他3");
        options3Items_01.add(options3Items_01_05);

        ArrayList<String> options3Items_02_01=new ArrayList<String>();
        options3Items_02_01.add("长沙长沙长沙长沙长沙长沙长沙长沙长沙1111111111");
        options3Items_02_01.add("长沙2");
        options3Items_02_01.add("长沙3");
        options3Items_02_01.add("长沙4");
        options3Items_02_01.add("长沙5");
        options3Items_02_01.add("长沙6");
        options3Items_02_01.add("长沙7");
        options3Items_02_01.add("长沙8");
        options3Items_02.add(options3Items_02_01);
        ArrayList<String> options3Items_02_02=new ArrayList<String>();
        options3Items_02_02.add("岳1");
        options3Items_02_02.add("岳2");
        options3Items_02_02.add("岳3");
        options3Items_02_02.add("岳4");
        options3Items_02_02.add("岳5");
        options3Items_02_02.add("岳6");
        options3Items_02_02.add("岳7");
        options3Items_02_02.add("岳8");
        options3Items_02_02.add("岳9");
        options3Items_02.add(options3Items_02_02);
        ArrayList<String> options3Items_03_01=new ArrayList<String>();
        options3Items_03_01.add("好山水");
        options3Items_03.add(options3Items_03_01);

        options3item.add(options3Items_01);
        options3item.add(options3Items_02);
        options3item.add(options3Items_03);

        pvOption.setPicker(options1item,options2item,options3item,true);
        pvOption.setCyclic(false,true,true);
        pvOption.setTitle("选择城市");
        pvOption.setSelectOptions(1,1,1);
        pvOption.show();

    }

    private void initView() {
        btnCheckTime= (Button) findViewById(R.id.btn_checktime);
        btnBtnCheckAds= (Button) findViewById(R.id.btn_checkaddress);
        tvAds= (TextView) findViewById(R.id.tv_address);
        tvTime= (TextView) findViewById(R.id.tv_time);
    }

    public static String getTime(Date data){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(data);
    }
}
