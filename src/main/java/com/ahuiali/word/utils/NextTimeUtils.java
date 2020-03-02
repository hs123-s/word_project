package com.ahuiali.word.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class NextTimeUtils {
    public String getNextTime(Integer count,Calendar calendar){


        //HH是24小时制，hh是12小时制，巨坑！
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (count){
            case 1: calendar.add(Calendar.MINUTE,30);
                return sdf.format(calendar.getTime());

            case 2: calendar.add(Calendar.HOUR_OF_DAY,12);
                return sdf.format(calendar.getTime());

            case 3: calendar.add(Calendar.DAY_OF_MONTH,1);
                return sdf.format(calendar.getTime());

            case 4: calendar.add(Calendar.DAY_OF_MONTH,2);
                return sdf.format(calendar.getTime());

            case 5: calendar.add(Calendar.DAY_OF_MONTH,4);
                return sdf.format(calendar.getTime());

            case 6: calendar.add(Calendar.DAY_OF_MONTH,7);
                return sdf.format(calendar.getTime());
//不要这个了
//            case 7: calendar.add(Calendar.DAY_OF_MONTH,15);
//                return sdf.format(calendar.getTime());

        }
        //否则传回当前时间
        return sdf.format(new Date());
    }
}
