package lab05;

public class Main {

    public static void main(String[] args) {

        String outputLine;
        for (int row = 1; row <= 40; row++){
            outputLine = "";
            for (int column = 1; column <= 40; column++){
                outputLine = outputLine + determineCharacter(column, row);
            }
            System.out.println (outputLine);
        }
    }



    public static char determineCharacter(int column, int row){
        // '?' erstellen
        if(((column >= 0 && column <=2) || (row > 38) || row >= 0 && row <=2) || (column >= 37)) {
            return '?';
        }
        // '+' erstellen
        if((column + row) == 41) {
            return '+';
        }
        // '-' erstellem
        if(row == 17 && column >= 3 && column < 18) {
            return '-';
        }
        // '|' erstellen
        if(column == 17 && row > 2 && row < 17) {
            return '|';
        }
        // '/' erstellen
        if(column % 3 == 0 && row % 3 == 0 && row > 2 && row < 17 && column > 2 && column < 17) {
            return '/';
        }
        if((column+1) % 3 == 0 && (row+2) % 3 == 0 && row > 2 && row <= 16 && column > 2 && column <= 16) {
            return '/'; }
        if((column+2) % 3 == 0 && (row+1) % 3 == 0 && row > 2 && row < 17 && column > 2 && column < 17) {
            return '/';
        }
        // '=' erstellen
        if((column+2) % 3 == 0 && row % 3 == 0 && row > 2 && row < 17 && column > 2 && column < 17) {
            return '=';
        }
        if((column) % 3 == 0 && (row+2) % 3 == 0 && row > 2 && row <= 16 && column >= 3 && column <= 16) {
            return '=';
        }
        if((column+1) % 3 == 0 && (row+1) % 3 == 0 && row > 2 && row <= 16 && column > 2 && column < 17) {
            return '=';
        }
        // '(' erstellen
        if((row > 17 || column <= 16) && column % 5 == 0 && ((column + row) < 41)) {
            return '('; }
        // ')' erstellen
        if(row % 3 == 0 && ((column + row) > 41)) {
            return ')';
        }
        return ' ';
    }

}
