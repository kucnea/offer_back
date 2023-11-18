package com.phy.CommonFuncs;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateFunc {

    // 두번째 인자의 날자가 몇일 뒤인지 반환
    public static int betweenDays(Date first, Date second){

        LocalDate date1 = first.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = second.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Math.abs(date1.until(date2).getDays());

    }

}
