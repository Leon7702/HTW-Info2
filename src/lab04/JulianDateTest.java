package lab04;

class JulianDateTest {

    // Main method to run all the tests
    public static void main(String[] args) {
        testConstructor();
        testRegulartoJD();
        testJDtoRegular();
        testMonday();

    }

    // Method to test the  constructor
    public static void testConstructor() {
        JulianDate jd = new JulianDate(7, 7, 2002);

        if (jd.julianDate != 2452463 || jd.day != 7 || jd.month != 7 || jd.year != 2002) {
            System.out.println("testConstructor failed");
        } else {
            System.out.println("testConstructor passed");
        }
    }

    // Method to test the RegulartoJD method
    public static void testRegulartoJD() {
        int jd = JulianDate.RegulartoJD(7, 7, 2002);

        if (jd != 2452463) {
            System.out.println("testRegulartoJD failed");
        } else {
            System.out.println("testRegulartoJD passed");
        }
    }

    // Method to test the JDtoRegular method
    public static void testJDtoRegular() {
        String reg = JulianDate.JDtoRegular(2452463);

        if (!reg.equals("7/7/2002 (DD/MM/YYYY)")) {
            System.out.println("testJDtoRegular failed");
        } else {
            System.out.println("testJDtoRegular passed");
        }
    }

    public static void testMonday() {
        String weekday = JulianDate.getWeekday(0);

        if(!weekday.equals("monday")) {
            System.out.println("testMonday failed");

        } else {
            System.out.println("testMonday  passed");

        }
    }

}

