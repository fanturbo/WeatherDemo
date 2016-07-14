package com.test.weatherdemo.utils;

import android.content.Context;

import com.test.weatherdemo.beans.City;
import com.test.weatherdemo.beans.District;
import com.test.weatherdemo.beans.Province;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by turbo on 2016/7/14.
 */
public class CommonUtils {
    /**
     * 解析xml文件 获取城市列表
     *
     * @param context
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static List<Province> getProvinces(Context context)
            throws XmlPullParserException, IOException {
        List<Province> provinces = null;
        Province province = null;
        List<City> citys = null;
        City city = null;
        List<District> districts = null;
        District district = null;

        InputStream in = context.getResources().getAssets().open("citys.xml");

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(in, "utf-8");
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    provinces = new ArrayList<Province>();
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if ("p".equals(tagName)) {
                        province = new Province();
                        citys = new ArrayList<City>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("p_id".equals(attrName))
                                province.setId(attrValue);
                        }
                    }
                    if ("pn".equals(tagName)) {
                        province.setName(parser.nextText());
                    }
                    if ("c".equals(tagName)) {
                        city = new City();
                        districts = new ArrayList<District>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("c_id".equals(attrName))
                                city.setId(attrValue);
                        }
                    }
                    if ("cn".equals(tagName)) {
                        city.setName(parser.nextText());
                    }
                    if ("d".equals(tagName)) {
                        district = new District();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("d_id".equals(attrName))
                                district.setId(attrValue);
                        }
                        district.setName(parser.nextText());
                        districts.add(district);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("c".equals(parser.getName())) {
                        city.setDisList(districts);
                        citys.add(city);
                    }
                    if ("p".equals(parser.getName())) {
                        province.setCitys(citys);
                        provinces.add(province);
                    }
                    break;
            }
            event = parser.next();
        }
        return provinces;
    }

    public static void writeCity2Realm(Context context) {
        try {
            final List<Province> provinceList = getProvinces(context);
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for(Province province :provinceList){

                    }
                }
            });
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
