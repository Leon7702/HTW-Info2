package lab04;

public class MetricDate extends JulianDate {


    public MetricDate(int day, int month, int year) {

        super(day,month,year);

    }

    public static MetricDate JDtoMD(JulianDate jd) {

        int jdn = jd.getJulianDate();
        int year = (jdn - (jdn % 1000)) / 1000;
        int restMonth = jdn % 1000;
        int month = (restMonth-(restMonth % 100)) / 100 + 1;
        int day = restMonth % 100;

        return new MetricDate(day, month, year);


    }

    public static int MDtoJD(MetricDate md) {


        int day = md.getDay();
        int month = md.getMonth() * 100;
        int year = md.getYear() * 1000;

        return day + month + year;
    }


}
