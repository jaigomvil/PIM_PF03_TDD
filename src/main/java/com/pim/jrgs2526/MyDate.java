package com.pim.jrgs2526;

public class MyDate {

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    private int day;
    private Months month;
    private int year;

    public MyDate() {
        this.day = 1;
        this.month = Months.JANUARY;
        this.year = 2000;
    }

    public MyDate(int day, Months month, int year) {

        this.year = year;
        this.month = month;
        this.day = day;

        try {
            setYear(year);
            setMonth(month);
            setDay(day);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }
    }


    private int getDaysInMonth(Months month, int year) {
        switch (month) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return 31;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            case FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 0;
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void setDay(int day) {
        if (day < 1 || day > getDaysInMonth(this.month, this.year)) {
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }
        this.day = day;
    }

    public void setMonth(Months month) {
        if (month == null) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }
        
        if (this.day > getDaysInMonth(month, this.year)) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }
        
        this.month = month;
    }

    public void setYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        }
        
        if (this.day > getDaysInMonth(this.month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        }
        
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public Months getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth( int monthNumber ) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }
}
