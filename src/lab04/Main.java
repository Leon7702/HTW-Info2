package lab04;

import java.util.Calendar;
import java.util.Scanner;
public class Main  {
    public static void main(String[] args) {

        birthday();
        System.out.println();
        christmasOnSunday();
        System.out.println();
        AgeinMD();

    }

    public static void birthday() {
        // getting the JD of today
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentDay = cal.get(Calendar.DATE);
        int today = JulianDate.RegulartoJD(currentDay, currentMonth, currentYear);
        // getting the birthday in JD
        Scanner numberReader = new Scanner(System.in);
        System.out.println("Enter some dates of your birthday... ");
        System.out.println("Birthday: ");
        int birthDay = numberReader.nextInt();
        System.out.println("Birthmonth: ");
        int birthMonth = numberReader.nextInt();
        System.out.println("Birthyear: ");
        int birthYear = numberReader.nextInt();
        System.out.println("\n" + "Your birthday: " + birthDay + "/" + birthMonth + "/" + birthYear);
        int birthday = JulianDate.RegulartoJD(birthDay, birthMonth, birthYear);
        // calculating the days on earth
        int daysOld = today - birthday;
        // getting the weekday of the birthday
        String weekDay = JulianDate.getWeekday(birthday);
        System.out.println("Your are " + daysOld + " days old and your birthday was on a " + weekDay + "." + "\n");
        // Print out if sunday's Child
        if (weekDay.equals("sunday")) {
            System.out.println("You are a sundays Child!");
        }
        // Print out if today is the birthday
        if (currentDay == birthDay && currentMonth == birthMonth) {
            System.out.println("And congratulations, today is your birthday!");
        }
        // print out if days on earth is divisible by 100
        if (daysOld % 100 == 0 && daysOld != 0) {
            System.out.println("Oh wow, the numbers you are on earth are divisible by 100. Thats nice!");
        }
    }

    public static void christmasOnSunday() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int christmas = JulianDate.RegulartoJD(24, 12, year);
        while (JulianDate.getWeekday(christmas) != "sunday") {
            year++;
            christmas = JulianDate.RegulartoJD(24, 12, year);
        }
        System.out.println("The next year where christmas will fall on a sunday is in " + year + ".");
    }

    //Age in Metric Date
    public static void AgeinMD() {
        JulianDate currentDate = new JulianDate(7,5,2023);
        MetricDate mcurrentDate = MetricDate.JDtoMD(currentDate);

        JulianDate leonBD = new JulianDate(7,7,2002);
        System.out.println("Geburtstag Leon in JulianDate: " + leonBD.getJulianDate());
        MetricDate mleonBD = MetricDate.JDtoMD(leonBD);
        System.out.println("Geburtstag Leon in MetricDate: " +mleonBD.getDay() + " " + mleonBD.getMonth() + " " + mleonBD.getYear());
        System.out.println("In the metric system Leon is " + (mcurrentDate.getYear() - mleonBD.getYear() + " years old." ));

        JulianDate vivBD = new JulianDate(16,6,1994);
        System.out.println("Geburtstag Vivian in JulianDate: " + vivBD.getJulianDate());
        MetricDate mvivBD = MetricDate.JDtoMD(vivBD);
        System.out.println("Geburtstag Vivian in MetricDate: " +mvivBD.getDay() + " " + mvivBD.getMonth() + " " + mvivBD.getYear());
        System.out.println("In the metric system Vivian is " + (mcurrentDate.getYear() - mvivBD.getYear() + " years old."));

    }
}


