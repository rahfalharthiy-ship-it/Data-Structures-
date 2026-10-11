
package com.mycompany.ds;

public class DateTime implements IDateTime{
    
private final int year;
private final int month;
private final int day;
private final int hour;
private final int minute;

public DateTime(int year, int month, int day,int hour, int minute){
    if(year<1 || month<1 || month>12 || day<1 || day>31 || hour<0 || hour>23 || minute<0 || minute>59){
    throw new IllegalArgumentException("Invalid date/time");
    }
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
}


@Override
public int getYear() {
    return year;
}

@Override
public int getMonth() {
    return month;
}

@Override
public int getDay() {
    return day;
}

@Override
public int getHour() {
    return hour;
}

@Override
public int getMinute() {
    return minute;
}

@Override
public String format() {
    return String.format("%02d/%02d/%04d %02d:%02d",month, day, year, hour, minute);
}

@Override
public int compareTo(IDateTime other) {
    
    if (year != other.getYear()) 
        return Integer.compare(year, other.getYear());

    if (month != other.getMonth()) 
        return Integer.compare(month, other.getMonth());

    if (day != other.getDay()) 
        return Integer.compare(day, other.getDay());

    if (hour != other.getHour()) 
        return Integer.compare(hour, other.getHour());

        return Integer.compare(minute, other.getMinute());
}

}
