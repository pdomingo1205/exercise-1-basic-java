import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.lang3.RandomStringUtils;
import static java.lang.Integer.parseInt;

public class basicJava {
    static Scanner scan=new Scanner(System.in);

    private static class Table {
        private int sizeRow;
        private int sizeCol;
        private String[][] tableContent;

      private Table (int row, int col) {
          sizeRow = row;
          sizeCol = col;
          createTable();
      }
      public int getSizeRow(){
        return sizeRow;
      }
      public int getSizeCol(){
        return sizeCol;
      }
      public void setSizeRow(int row){
        sizeRow=row;
      }
      public void setSizeCol(int col){
        sizeCol=col;
      }

      public void createTable() {
        tableContent = new String[sizeRow][sizeCol];

        for (int col = 0; col < sizeCol; col++) {

          for (int row = 0; row < sizeRow; row++) {

            String cellValue = "";
            char ch;
            cellValue = (RandomStringUtils.randomAscii(3));

            tableContent[row][col] = cellValue + "\t";

          }
        }
        printTable();

      }
        public void resetTable(){
            this.tableContent = null;
            this.setSizeRow(getIntegerInput("size of Row"));
            this.setSizeCol(getIntegerInput("size of Column"));
            createTable();

        }

      public void printTable(){
          for (int col = 0; col < sizeCol; col++){

              for (int row = 0; row < sizeRow; row++){
                  System.out.print("|" + tableContent[row][col] + "|");
              }

          System.out.println();
        }
      }

      public void Search(String str){
          int found = 0;

          for (int col = 0; col < sizeCol; col++){

            for (int row = 0;row < sizeRow; row++){

              int lastIndex = 0;
              int tempFound = 0;

                while(lastIndex != -1) {
                    lastIndex = tableContent[row][col].indexOf(str,lastIndex);

                    if(lastIndex != -1){
                        lastIndex += 1;
                        tempFound++;
                    }

                }

                found += tempFound;
            }

          }
          System.out.println(String.valueOf(found) + " occurences");

      }
      public void editCell(){
        int editRow = getCoordinateInput("of Row",this);
        int editCol = getCoordinateInput("of Column",this);
        String newText;
        System.out.println("Input new text for [" + editRow + "],[" + editCol + "]");
        newText = scan.nextLine();
        tableContent[editRow][editCol] = newText + "\t";
      }
  }

    public static int getIntegerInput(String s){
        boolean valid = true;
        int intInput = 0;

        do{
            System.out.println("Input ".concat(s));
            intInput = Integer.valueOf(scan.nextLine());
            valid = true;

            if(intInput < 0) {
                valid = false;
                System.out.println("Length cannot be negative value");
            }
        }while(!valid);

        return intInput;

    }

    public static int getCoordinateInput(String s, Table tab){
        String strInput;
        boolean inCorrect = true;

        do{

            try{

                System.out.println("Input index "+s);
                strInput = scan.nextLine();

                int intPut = Integer.valueOf(strInput);

                if(s.equals("of Row")){

                    if(intPut <= tab.getSizeRow()-1 && intPut >= 0){
                        inCorrect = false;

                        return intPut;

                    }else{
                        System.out.println("Out of bounds");
                    }

                }else if(s.equals("of Column")){

                    if(intPut <= tab.getSizeCol()-1 && intPut >= 0){
                        inCorrect = false;
                    }else{
                        System.out.println("Out of bounds");
                    }
                }
            }

            catch(Exception e){
                System.out.println("Incorrect Input");
            }
        }while(inCorrect);

        return 0;
    }

    public static boolean menu(Table tab){
        String ans;

        do {
            System.out.println("1.Search 2.Edit 3.Print 4.Reset 5.Exit");
            ans = scan.nextLine();

            switch (ans) {
                case "1":
                    String strSearch;
                    System.out.println("Input string to search");
                    strSearch=scan.nextLine();
                    tab.Search(strSearch);
                    return true;
                case "2":
                    tab.editCell();
                    return true;
                case "3":
                    tab.printTable();
                    return true;
                case "4":
                    tab.resetTable();
                    return true;
                case "5":
                    System.exit(0);
                default :
                    System.out.println("Invalid Input.");
            }
        } while(ans != "5");

        return true;
    }

    public static void main(String[] args) {
        Table t = new Table(getIntegerInput("size of Row"),getIntegerInput("size of Column"));
        do{
        }while(menu(t));

    }
}
