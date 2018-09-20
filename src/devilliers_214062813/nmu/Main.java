package devilliers_214062813.nmu;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Main {

    Integer Threshold = 10;

    public static void main(String[] args) {
        readCSV rc = new readCSV();
        ArrayList<Data> listData = new ArrayList<>();
        Pair<mapData, ArrayList<Data>> data = readCSV.READcsvfile("Source Data.csv");
        listData=data.getRight();
        mapData curmap = data.getLeft();
        System.out.println();
        System.out.println(curmap);
        System.out.println();

        Integer maxSizeApex = curmap.listSuburbs.size() * curmap.listType.size() * curmap.listNoRooms.size() * curmap.listNoBathRooms.size() * curmap.listNoParking.size() * curmap.listArea.size();
        Integer maxSizeNoRoomsArea = curmap.listNoRooms.size() * curmap.listArea.size();
        Integer maxSizeNoBathRoomsParkingArea = curmap.listNoBathRooms.size() * curmap.listNoParking.size();
        Integer maxSizeSuburbTypeArea = curmap.listSuburbs.size() * curmap.listType.size() * curmap.listArea.size();
        System.out.println("Apex = " + maxSizeApex);
        q1(data);

        System.out.println("<nummber of rooms, area> = " + maxSizeNoRoomsArea);
        q2(data, 10);

        System.out.println("<number of bathrooms, number of parking areas> = " + maxSizeNoBathRoomsParkingArea);
        System.out.println("<suburb, residence type, area> = " + maxSizeSuburbTypeArea);
        System.out.println(curmap.listArea.size());


        listData.sort(new areaCompare());



        //       // System.out.println(sum);
       /* for (Data d : listData)
        {
            System.out.println(d.toStringPrint(curmap));
        }*/


	// write your code here
    }

    public static void q1(Pair<mapData, ArrayList<Data>> data)
    {
        int sum = 0;
        for (Data d : data.getRight())
        {
            sum = sum + data.getLeft().listPrice.get(d.price);
        }
        double dave = 0.0;
        dave = (sum/(data.getRight().size()*1.0));
        System.out.println("________________________");
        System.out.println("Apex");
        System.out.println("Cells = 1");
        System.out.println("AVG Apex = " + dave);
    }
    public static void q2(Pair<mapData, ArrayList<Data>> data, int minThresh)
    {
        data.getRight().sort(new areaCompare());
        data.getRight().sort(new parkingCompare());
        int[][] count = new int [data.getLeft().listArea.size()][data.getLeft().listNoRooms.size()];
        int[][] sum = new int [data.getLeft().listArea.size()][data.getLeft().listNoRooms.size()];
        ArrayList<Triple<Integer, Integer, Integer>> successPairs = new ArrayList<>();


       /* for (int i = 0; i <= data.getLeft().listArea.size() - 1; i++)
        {
            for (int j = 0; j <= data.getLeft().listNoRooms.size() - 1; j++)
            {
                count[i][j] = 0;
                sum[i][j] = 0;
            }
        }*/
        for (Data d : data.getRight()) {
            count[d.area][d.noroom] =count[d.area][d.noroom] + 1;
            sum[d.area][d.noroom] = sum[d.area][d.noroom] + data.getLeft().listPrice.get(d.price);
        }
        int icounter = 0;
        for (int x = 0; x <= data.getLeft().listArea.size() - 1; x ++)
        {
            for (int y = 0; y <= data.getLeft().listNoRooms.size() - 1; y++)
            {
              //  System.out.print(count[x][y] + " ");
                if (count[x][y] >= minThresh)
                {
                    ImmutableTriple<Integer, Integer, Integer> position = new ImmutableTriple<Integer, Integer, Integer>(x, y, sum[x][y]);
                    successPairs.add(position);
                    icounter = icounter + 1;
                }
            }
          //  System.out.println();
        }

        //Display Results
        for (Triple<Integer, Integer, Integer> s : successPairs)
        {
            double davg = s.getRight()/(count[s.getLeft()][s.getMiddle()]*1.0);
            System.out.println(data.getLeft().listArea.get(s.getLeft()) + " : " + data.getLeft().listNoRooms.get(s.getMiddle()) + " (" + count[s.getLeft()][s.getMiddle()] + ")" + " = " + davg);

        }



     /*   for (Integer[] x : count)
        {
            for (Integer y : x)
            {
                System.out.print(y + " ");
                if (y >= 2)
                {
                    icounter = icounter + 1;
                }
            }
            System.out.println();
        }*/
        System.out.println("Greater than threshold " +icounter);

    }














}
class areaCompare implements Comparator<Data>
{
    @Override
    public int compare(Data a, Data b)
    {
        return a.area.compareTo(b.area);
    }
}
class parkingCompare implements Comparator<Data>
{
    @Override
    public int compare(Data a, Data b)
    {
        return a.noparking.compareTo(b.noparking);
    }
}
class bathCompare implements Comparator<Data>
{
    @Override
    public int compare(Data a, Data b)
    {
        return a.nobaths.compareTo(b.nobaths);
    }
}
class roomsCompare implements Comparator<Data>
{
    @Override
    public int compare(Data a, Data b)
    {
        return a.noroom.compareTo(b.noroom);
    }
}
class suburbCompare implements Comparator<Data>
{
    @Override
    public int compare(Data a, Data b)
    {
        return a.suburb.compareTo(b.suburb);
    }
}
class typeCompare implements Comparator<Data>
{
    @Override
    public int compare(Data a, Data b)
    {
        return a.type.compareTo(b.type);
    }
}



