import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Exercise1 {
    static boolean dimGot = false;
    static int dimension = 0;
    static double[][] data;          // all nodes' coordinates data would be saved here
    public static void main(String[] args) throws IOException {
        double[][] a = getData("usa13509.tsp");
        for (int i = 0; i < a.length; i ++){
            System.out.println(a[i][0] + "    " + a[i][1]);
        }
    }

    public static double[][] getData(String fileName) throws IOException {
        String line;
        BufferedReader br1 = new BufferedReader(new FileReader(fileName));
        while ((line = br1.readLine()) != null){
            if (!dimGot ){
                dimGot = getDim(line);
                if (dimGot){
                    data = new double[dimension][2];
                }
            }
            if(line.length() != 0)
                getEveryCord(line);
            //System.out.println(line);
        }
        /*
        for(int i = 0; i < data.length; i ++){
            System.out.println(i+ "  " + data[i][0] + "  " + data[i][1]);
        }
        */
        return data;
    }

    public static boolean getDim(String line){
        int r = 0;
        while (line.charAt(r) == "DIMENSION1".charAt(r)){
            r++;
        }
        if(r >= 8){
            for(int i = 12; i < line.length(); i++){
                dimension = dimension* 10 + (int)line.charAt(i) - 48;
            }
            return true;
        }else {
            return false;
        }
    }
    static int position = 0;

    public static void getEveryCord(String line){
        position = 0;
        //System.out.println(line);
        int a = (int)line.charAt(0);
        int index = 0;
        if (a > 48 && a < 58){
            index = (int) getNumber(line);
            // System.out.println(line + " --- " + index);
            position++;
            data[index - 1][0] = (double)Math.round(getNumber(line)*1000)/1000;
            position++;
            data[index - 1][1] = (double)Math.round(getNumber(line)*1000)/1000;
        }
    }

    public static double getNumber(String line){
        double a = 0;
        int getPoint = 0;
        while (line.charAt(position) != ' '){
            if (getPoint == 0){
                if (line.charAt(position) != '.') {
                    a = a * 10 + line.charAt(position) - 48;
                }else {
                    getPoint = 1;
                }
            }else {
                a += (line.charAt(position) - 48) / Math.pow(10, getPoint);
                getPoint++;
            }
            position++;
            if (position >= line.length())
                break;
        }
        return a;
    }

}
