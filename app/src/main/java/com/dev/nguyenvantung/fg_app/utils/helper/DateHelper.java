package com.dev.nguyenvantung.fg_app.utils.helper;

import java.util.Date;

public class DateHelper {
    private static date instance;

    public static date getInstance() {
        if (instance == null) {
            instance = new date();
        }
        return instance;
    }
    public DateHelper() {

    }

    public date splitDate(String strdate) {
        String[] arStr = strdate.split("/");
        date date = new date();
        if (Integer.parseInt(arStr[0]) > 100) {
            date.setYear(Integer.parseInt(arStr[0]));
            date.setMonth(Integer.parseInt(arStr[1]));
            date.setDay(Integer.parseInt(arStr[2]));
        } else {
            date.setYear(Integer.parseInt(arStr[2]));
            date.setMonth(Integer.parseInt(arStr[1]));
            date.setDay(Integer.parseInt(arStr[0]));
        }
        return date;
    }

   public static class date {
        private int day;
        private int month;
        private int year;

       public date() {
       }

       public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

    }

}
