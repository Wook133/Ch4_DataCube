package devilliers_214062813.nmu;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.javatuples.Quartet;

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

        ArrayList<rowdata> rd = new ArrayList<>();
        mapData curmap = data.getLeft();
        for (Data d : listData)
        {
            rd.add(new rowdata(d));
        }
        generalMap gmap = new generalMap(curmap);

        System.out.println();
        System.out.println(curmap);
        System.out.println();

        Integer maxSizeApex = curmap.listSuburbs.size() * curmap.listType.size() * curmap.listNoRooms.size() * curmap.listNoBathRooms.size() * curmap.listNoParking.size() * curmap.listArea.size();
        Integer maxSizeNoRoomsArea = curmap.listNoRooms.size() * curmap.listArea.size();
        Integer maxSizeNoBathRoomsParkingArea = curmap.listNoBathRooms.size() * curmap.listNoParking.size();
        Integer maxSizeSuburbTypeArea = curmap.listSuburbs.size() * curmap.listType.size() * curmap.listArea.size();
        System.out.println("Apex = " + maxSizeApex);
        q1(data);
        //0 area
        //1 parking
        //2 bathrooms
        //3 rooms
        //4 suburb
        //5 type
        //6 price

        System.out.println("<nummber of rooms, area> = " + maxSizeNoRoomsArea);
        //q2(data, 10);
        q2D(gmap, rd, 10, 0, 3, curmap);
        System.out.println("<number of bathrooms, number of parking areas> = " + maxSizeNoBathRoomsParkingArea);
       // q3(data, 10);
       // q2D(gmap, rd, 10, 3, 4, curmap);
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
        System.out.println("(*, *, *, *, *, " + dave + ")");
    }
    public static void q2(Pair<mapData, ArrayList<Data>> data, int minThresh)
    {
        data.getRight().sort(new roomsCompare());
        data.getRight().sort(new areaCompare());
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


    public static void q3(Pair<mapData, ArrayList<Data>> data, int minThresh)
    {
        data.getRight().sort(new bathCompare());
        data.getRight().sort(new parkingCompare());
        int[][] count = new int [data.getLeft().listNoBathRooms.size()][data.getLeft().listNoParking.size()];
        int[][] sum = new int [data.getLeft().listNoBathRooms.size()][data.getLeft().listNoParking.size()];
        ArrayList<Triple<Integer, Integer, Integer>> successPairs = new ArrayList<>();

        for (Data d : data.getRight()) {
            count[d.nobaths][d.noparking] =count[d.nobaths][d.noparking] + 1;
            sum[d.nobaths][d.noparking] = sum[d.nobaths][d.noparking] + data.getLeft().listPrice.get(d.price);
        }
        int icounter = 0;
        for (int x = 0; x <= data.getLeft().listNoBathRooms.size() - 1; x ++)
        {
            for (int y = 0; y <= data.getLeft().listNoParking.size() - 1; y++)
            {
                if (count[x][y] >= minThresh)
                {
                    ImmutableTriple<Integer, Integer, Integer> position = new ImmutableTriple<Integer, Integer, Integer>(x, y, sum[x][y]);
                    successPairs.add(position);
                    icounter = icounter + 1;
                }
            }
        }

        //Display Results
        for (Triple<Integer, Integer, Integer> s : successPairs)
        {
            double davg = s.getRight()/(count[s.getLeft()][s.getMiddle()]*1.0);
            System.out.println(data.getLeft().listNoBathRooms.get(s.getLeft()) + " : " + data.getLeft().listNoParking.get(s.getMiddle()) + " (" + count[s.getLeft()][s.getMiddle()] + ")" + " = " + davg);
        }
        System.out.println("Greater than threshold " +icounter);

    }

    public static void q2D(generalMap map, ArrayList<rowdata> data, int minThresh, int a, int b, mapData fullmap)
    {
        int[][] count = new int [map.map[a].length][map.map[b].length];
        int[][] sum = new int [map.map[a].length][map.map[b].length];
        ArrayList<Triple<Integer, Integer, Integer>> successPairs = new ArrayList<>();

        for (rowdata d : data) {
            count[d.d[a]][d.d[b]] =count[d.d[a]][d.d[b]] + 1;
            sum[d.d[a]][d.d[b]] = sum[d.d[a]][d.d[b]] + fullmap.listPrice.get(d.d[6]);
        }
        int icounter = 0;
        for (int x = 0; x <= map.map[a].length - 1; x ++)
        {
            for (int y = 0; y <= map.map[b].length - 1; y++)
            {
                if (count[x][y] >= minThresh)
                {
                    ImmutableTriple<Integer, Integer, Integer> position = new ImmutableTriple<Integer, Integer, Integer>(x, y, sum[x][y]);
                    successPairs.add(position);
                    icounter = icounter + 1;
                }
            }
        }

        //Display Results
        for (Triple<Integer, Integer, Integer> s : successPairs)
        {
            System.out.print("(");
            for (int n = 0; n <= a -1; n++)
            {
                System.out.print("*, ");
            }
            double davg = s.getRight()/(count[s.getLeft()][s.getMiddle()]*1.0);

            switch (a)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getLeft()) + ", ");
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getLeft()) + ", ");
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getLeft()) + ", ");
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getLeft()) + ", ");
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getLeft()) + ", ");
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getLeft()) + ", ");
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getLeft()) + ", ");
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getLeft()) + ", ");
                }
                break;
            }
            for (int n = a+1; n <= b -1; n++)
            {
                System.out.print("*, ");
            }
            switch (b)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getMiddle()) + ", ");
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getMiddle()) + ", ");
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getMiddle()) + ", ");
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getMiddle()) + ", ");
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getMiddle()) + ", ");
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getMiddle()) + ", ");
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getMiddle()) + ", ");
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getMiddle()) + ", ");
                }
                break;
            }
            for (int n = b+1; n <= 5; n++)
            {
                System.out.print("*, ");
            }


            System.out.print(" " + davg);
            System.out.println(") " + "Count = " + count[s.getLeft()][s.getMiddle()]);
        }
        System.out.println("Greater than threshold " +icounter);
    }

    public static void q3D(generalMap map, ArrayList<rowdata> data, int minThresh, int a, int b, int c, mapData fullmap) {
        int[][][] count = new int[map.map[a].length][map.map[b].length][map.map[c].length];
        int[][][] sum = new int[map.map[a].length][map.map[b].length][map.map[c].length];
        ArrayList<Quartet<Integer, Integer, Integer, Integer>> successPairs = new ArrayList<>();

        for (rowdata d : data) {
            count[d.d[a]][d.d[b]][d.d[c]] = count[d.d[a]][d.d[b]][d.d[c]] + 1;
            sum[d.d[a]][d.d[b]][d.d[c]] = sum[d.d[a]][d.d[b]][d.d[c]] + fullmap.listPrice.get(d.d[6]);
        }
        int icounter = 0;
        for (int x = 0; x <= map.map[a].length - 1; x++) {
            for (int y = 0; y <= map.map[b].length - 1; y++) {
                for (int z = 0; z <= map.map[c].length - 1; z++) {
                    if (count[x][y][z] >= minThresh) {
                        Quartet<Integer, Integer, Integer, Integer> position = new Quartet<Integer, Integer, Integer, Integer>(x, y, z, sum[x][y][z]);
                        successPairs.add(position);
                        icounter = icounter + 1;
                    }
                }
            }
        }

        for (Quartet<Integer, Integer, Integer, Integer> s : successPairs)
        {
            double davg = s.getValue3()/((count[s.getValue0()][s.getValue1()][s.getValue2()])*1.0);
            switch (a)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue0()));
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue0()));
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue0()));
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue0()));
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue0()));
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue0()));
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue0()));
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue0()));
                }
                break;
            }
            System.out.print(" : ");
            switch (b)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue1()));
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue1()));
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue1()));
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue1()));
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue1()));
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue1()));
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue1()));
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue1()));
                }
                break;
            }
            System.out.print(" : ");
            switch (c)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue2()));
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue2()));
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue2()));
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue2()));
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue2()));
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue2()));
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue2()));
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue2()));
                }
                break;
            }
            System.out.println(" (" + count[s.getValue0()][s.getValue1()][s.getValue2()] + ")" + " = " + davg);
        }
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



