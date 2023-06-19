package lab04;

import java.util.Scanner;

public class JulianDate {

    protected int julianDate;
    protected int day;
    protected int month;
    protected int year;

    // First constructor with null value for julian date
    public JulianDate() {
        julianDate = 0;
    }

    // Second constructor with values for julian date
    public JulianDate(int day, int month, int year) {

        this.year = year;
        this.month = month;
        this.day = day;

        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;

        julianDate = RegulartoJD(day, month, year);
    }

    // Takes in a regular date DD MM YYYY over the scanner and returns the julian
    // date
    private void regularToJDScanner() {

        Scanner numberReader = new Scanner(System.in);

        System.out.println("Enter the day: ");
        day = numberReader.nextInt();

        System.out.println("Enter the month: ");
        month = numberReader.nextInt();

        System.out.println("Enter the year: ");
        year = numberReader.nextInt();

        RegulartoJD(day, month, year);
    }

    // Takes in a regular date DD MM YYYY and returns the julian date
    public static int RegulartoJD(int day, int month, int year) {

        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;

        int JD = day + ((153 * m + 2) / 5) + (365 * y) + (y / 4) - (y / 100) + (y / 400) - 32045;
//			System.out.println("\n" + "The Julian Date number of " + day + "/" + month + "/" + year + " (DD/MM/YYYY) is: " + JD);

        return JD;
    }

    // Takes in a Julian Date over the scanner and prints out the regular Date
    public void JDtoRegularScanner() {

        Scanner numberReader = new Scanner(System.in);

        int JGREG = 15 + 31 * (10 + 12 * 1582);
        double HALFSECOND = 0.5;

        System.out.println("Enter the Julian Date: ");

        int julianDate = numberReader.nextInt();

        int jalpha, ja, jb, jc, jd, je, year, month, day;
        double julian = julianDate + HALFSECOND / 86400.0;
        ja = (int) julian;

        if (ja >= JGREG) {
            jalpha = (int) (((ja - 1867216) - 0.25) / 36524.25);
            ja = ja + 1 + jalpha - jalpha / 4;
        }

        jb = ja + 1524;
        jc = (int) (6680.0 + ((jb - 2439870) - 122.1) / 365.25);
        jd = 365 * jc + jc / 4;
        je = (int) ((jb - jd) / 30.6001);
        day = jb - jd - (int) (30.6001 * je);
        month = je - 1;
        if (month > 12)
            month = month - 12;
        year = jc - 4715;
        if (month > 2)
            year--;
        if (year <= 0)
            year--;

        System.out.println("\n" + "The regular calender date of " + julianDate + " is: " + day + "/" + month + "/"
                + year + " (DD/MM/YYYY)");
    }

    // Takes in a Julian Date and converts it to regular date
    public static String JDtoRegular(int julianDate) {

        int JGREG = 15 + 31 * (10 + 12 * 1582);
        double HALFSECOND = 0.5;
        int jalpha, ja, jb, jc, jd, je, year, month, day;
        double julian = julianDate + HALFSECOND / 86400.0;
        ja = (int) julian;

        if (ja >= JGREG) {
            jalpha = (int) (((ja - 1867216) - 0.25) / 36524.25);
            ja = ja + 1 + jalpha - jalpha / 4;
        }

        jb = ja + 1524;
        jc = (int) (6680.0 + ((jb - 2439870) - 122.1) / 365.25);
        jd = 365 * jc + jc / 4;
        je = (int) ((jb - jd) / 30.6001);
        day = jb - jd - (int) (30.6001 * je);
        month = je - 1;
        if (month > 12)
            month = month - 12;
        year = jc - 4715;
        if (month > 2)
            year--;
        if (year <= 0)
            year--;

        String reg = (day + "/" + month + "/" + year + " (DD/MM/YYYY)");
        return reg;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    public int getJulianDate() {
        return julianDate;
    }



    // Takes in 2 days, converts them to Julian Date and returns the number of days
    // betweeen
    public int daysBetween() {

        Scanner numberReader = new Scanner(System.in);

        System.out.println("Starting with the first date.");

        System.out.println("Enter the day: ");
        int day1 = numberReader.nextInt();

        System.out.println("Enter the month: ");
        int month1 = numberReader.nextInt();

        System.out.println("Enter the year: ");
        int year1 = numberReader.nextInt();

        System.out.println("Now the second date.");

        System.out.println("Enter the day: ");
        int day2 = numberReader.nextInt();

        System.out.println("Enter the month: ");
        int month2 = numberReader.nextInt();

        System.out.println("Enter the year: ");
        int year2 = numberReader.nextInt();

        int jd1 = RegulartoJD(day1, month1, year1);
        int jd2 = RegulartoJD(day2, month2, year2);

        System.out.println("\nThe days between are: " + (jd2 - jd1 + 1) + " (including start date)");
        return jd2 - jd1;
    }

    // Takes a Julian Date and returns the day after the Julian Date (plus 1)
    public int tomorrow() {
        int nextJD = julianDate + 1;
        System.out.println(
                ("The Julian Date after " + day + "/" + month + "/" + year + " (DD/MM/YYYY)" + " is: " + nextJD));
        return nextJD;
    }

    // Takes a Julian Date and returns the day before the Julian Date (minus 1)
    public int yesterday() {
        int lastJD = julianDate - 1;
        System.out.println(
                ("The Julian Date after " + day + "/" + month + "/" + year + " (DD/MM/YYYY)" + " is: " + lastJD));
        return lastJD;
    }

    // Takes a Julian Date and subtracts an int
    public int subDays(int sub) {
        int newJD = julianDate - sub;
        System.out.println(("Subtracting " + sub + " days from " + julianDate + " makes: " + newJD));
        return newJD;
    }

    // Takes a Julian Date and subtracts an int
    public int addDays(int add) {
        int newJD = julianDate + add;
        System.out.println(("Adding " + add + " days to " + julianDate + " makes: " + newJD));
        return newJD;
    }

    // Prints out the weekday of the JD
    public static String getWeekday(int julianDate) {
        if (julianDate % 7 == 0) {
            // System.out.println("The " + julianDate + " (JD) is on a " + "monday");
            return "monday";
        }
        if (julianDate % 7 == 1) {
            // System.out.println("The " + julianDate + " (JD) is on a " + "tuesday");
            return "tuesday";
        }
        if (julianDate % 7 == 2) {
            // System.out.println("The " + julianDate + " (JD) is on a " + "wednesday");
            return "wednesday";
        }
        if (julianDate % 7 == 3) {
            // System.out.println("The " + julianDate + " (JD) is on a " + "thursday");
            return "thursday";
        }
        if (julianDate % 7 == 4) {
            // System.out.println("The " + julianDate + " (JD) is on a " + "friday");
            return "friday";
        }
        if (julianDate % 7 == 5) {
            // System.out.println("The " + julianDate + " (JD) is on a " + "saturday");
            return "saturday";
        } else {
            // System.out.println("The " + julianDate + " (JD) is on a " + "sunday");
            return "sunday";
        }
    }

}



