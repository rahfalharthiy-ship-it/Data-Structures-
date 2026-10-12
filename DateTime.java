public class DateTime implements IDateTime{
    
private final int year;
private final int month;
private final int day;
private final int hour;
private final int minute;

//construct
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

//return year (e.g., 2026).
@Override
public int getYear() {
    return year;
}

//return month in [1..12].
@Override
public int getMonth() {
    return month;
}

//return day in [1..31]. 
@Override
public int getDay() {
    return day;
}

//return hour in [0..23]. 
@Override
public int getHour() {
    return hour;
}

//return minute in [0..59].
@Override
public int getMinute() {
    return minute;
}

 //Formats this date/time for display (e.g., "MM/DD/YYYY HH:MM").
@Override
public String format() {
    return String.format("%02d/%02d/%04d %02d:%02d",month, day, year, hour, minute);
}

//Compares this date/time with another chronologically: by year, then* month, then day, then hour, then minute.
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

// Converts a string in the format MM/DD/YYYY HH:MM into a DateTime object
public static DateTime parse(String str) {
String[] parts = str.trim().split(" ");
String[] dateParts = parts[0].split("/");
String[] timeParts = parts[1].split(":");

int month = Integer.parseInt(dateParts[0].trim());
int day = Integer.parseInt(dateParts[1].trim());
int year = Integer.parseInt(dateParts[2].trim());

int hour = Integer.parseInt(timeParts[0].trim());
int minute = Integer.parseInt(timeParts[1].trim());

return new DateTime (year, month, day, hour, minute);
}

}//end class
