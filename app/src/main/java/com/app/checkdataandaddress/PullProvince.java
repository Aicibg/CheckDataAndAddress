package com.app.checkdataandaddress;

import android.os.Handler;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @FileName: com.app.checkdataandaddress.PullProvince.java
 * Description:
 * Created by donghao on 2016/7/8.
 */
public class PullProvince {
    public static int PARSESUCCESS=12000;
    private Handler mHandler;
    private ArrayList<String> citys;
    private ArrayList<String> areas;
    private ArrayList<ArrayList<String>> item3;

    public PullProvince(Handler handler) {
        mHandler = handler;
        this.citys=new ArrayList<String>();
        this.areas=new ArrayList<String>();
        this.item3=new ArrayList<ArrayList<String>>();
    }

    public void getProvince(final InputStream is){
        new Thread(new Runnable() {
            @Override
            public void run() {
             try {
                XmlPullParser xmlPullParser= Xml.newPullParser();
                xmlPullParser.setInput(is,"utf-8");
                 int event;
                 event=xmlPullParser.getEventType();
                 String pName="";
                 String cName="";
                 String aNAme="";
                 String targetName;
                 while (event!=XmlPullParser.END_DOCUMENT){
                     targetName=xmlPullParser.getName();
                     switch (event){
                           case XmlPullParser.START_TAG:
                            if("province".equals(targetName)){
                                pName=xmlPullParser.getAttributeValue(0);
                                citys=new ArrayList<String>();
                                item3=new ArrayList<ArrayList<String>>();
                            }else if("city".equals(targetName)){
                                cName=xmlPullParser.getAttributeValue(0);
                                areas=new ArrayList<String>();
                            }else if("area".equals(targetName)){
                                aNAme=xmlPullParser.getAttributeValue(0);
                            }
                           break;
                           case XmlPullParser.END_TAG:
                               if("province".equals(targetName)){
                                   MainActivity.options1item.add(pName);
                                   MainActivity.options3item.add(item3);
                                   MainActivity.options2item.add(citys);
                               }else if("city".equals(targetName)){
                                   citys.add(cName);
                                   item3.add(areas);
                               }else if("area".equals(targetName)){
                                   areas.add(aNAme);
                               }
                           break;
                           default:

                               break;
                     }
                     event=xmlPullParser.next();
                 }
                 mHandler.sendEmptyMessage(PARSESUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
