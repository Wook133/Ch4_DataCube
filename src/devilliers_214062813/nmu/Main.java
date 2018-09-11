package devilliers_214062813.nmu;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        readCSV rc = new readCSV();
        ArrayList<Data> listData = new ArrayList<>();
        Pair<mapData, ArrayList<Data>> data = readCSV.READcsvfile("data.csv");
        listData=data.getRight();
        mapData curmap = data.getLeft();
        System.out.println();
        System.out.println(curmap);
        System.out.println();
        int sum = 0;
        for (Data d : listData)
        {
            sum = sum + curmap.listPrice.get(d.price);
        }
       // System.out.println(sum);
        /*for (Data d : listData)
        {
            System.out.println(d.toString());
        }*/
	// write your code here
    }
}
