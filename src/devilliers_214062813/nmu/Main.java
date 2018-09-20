package devilliers_214062813.nmu;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.javatuples.Quartet;
import org.javatuples.Quintet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Main {

    Integer Threshold = 10;

    public static void main(String[] args) {
        readCSV rc = new readCSV();
        ArrayList<Data> listData = new ArrayList<>();


         Pair<mapData, ArrayList<Data>> data = readCSV.READcsvfile("Source Data (2).csv");

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
        /*[0] = suburb;
        [1] = type;
        [2] = noroom;
        [3] = nobaths;
        [4] = noparking;
        [5] = area;
        [6] = price;*/

     /*   System.out.println("<nummber of rooms, area> = " + maxSizeNoRoomsArea);
        //q2(data, 10);
        q2D(gmap, rd, 6, 5, 2, curmap, "ANSWER2.csv");
        System.out.println("<number of bathrooms, number of parking areas> = " + maxSizeNoBathRoomsParkingArea);
       // q3(data, 10);
        q2D(gmap, rd, 6, 3, 4, curmap, "ANSWER3.csv");
        System.out.println("<suburb, residence type, area> = " + maxSizeSuburbTypeArea);
       // System.out.println(curmap.listArea.size());
        q3D(gmap, rd, 6, 0, 1, 5, curmap, "ANSWER4.csv");
       // fakeq3D(gmap, rd, 10, 4, 5, 0, curmap);
        listData.sort(new areaCompare());*/

        System.out.println("Suburb = " + curmap.listSuburbs.size());
        System.out.println(curmap.listSuburbs);
        System.out.println("Type = " + curmap.listType.size());
        System.out.println(curmap.listType);
        System.out.println("noroom = " + curmap.listNoRooms.size());
        System.out.println(curmap.listNoRooms);
        System.out.println("nobaths = " + curmap.listNoBathRooms.size());
        System.out.println(curmap.listNoBathRooms);
        System.out.println("noparking = " + curmap.listNoParking.size());
        System.out.println(curmap.listNoParking);
        System.out.println("area = " + curmap.listArea.size());
        System.out.println(curmap.listArea);
        System.out.println("price = " + curmap.listPrice.size());
        //       // System.out.println(sum);
       /* for (Data d : listData)
        {
            System.out.println(d.toStringPrint(curmap));
        }*/


	// write your code here
    }

    public static void q1(Pair<mapData, ArrayList<Data>> data)
    {
        Long sum = 0l;
        for (Data d : data.getRight())
        {
            sum = sum + data.getLeft().listPrice.get(d.price);
        }
        double dave = 0.0;
        dave = (sum/(data.getRight().size()*1.0));
        System.out.println("________________________");
        System.out.println("Apex");
        System.out.println("Cells = 1");
        String s = "*, *, *, *, *, " + dave + 1;
        try {
            readCSV.writeCsvFile("ANSWER1.csv", s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(s);
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

    public static void q2D(generalMap map, ArrayList<rowdata> data, int minThresh, int a, int b, mapData fullmap, String filename)
    {
        int first = Math.min(a, b);
        int second = Math.max(a, b);
        int[][] count = new int [map.map[first].length][map.map[second].length];
        int[][] sum = new int [map.map[first].length][map.map[second].length];
        ArrayList<Triple<Integer, Integer, Integer>> successPairs = new ArrayList<>();

        for (rowdata d : data) {
            count[d.d[first]][d.d[second]] =count[d.d[first]][d.d[second]] + 1;
            sum[d.d[first]][d.d[second]] = sum[d.d[first]][d.d[second]] + fullmap.listPrice.get(d.d[6]);
        }
        int icounter = 0;
        for (int x = 0; x <= map.map[first].length - 1; x ++)
        {
            for (int y = 0; y <= map.map[second].length - 1; y++)
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
            String sline = "";
            System.out.print("(");
            for (int n = 0; n <= first -1; n++)
            {
                sline = sline + "*, ";
                System.out.print("*, ");
            }
            double davg = s.getRight()/(count[s.getLeft()][s.getMiddle()]*1.0);

            switch (first)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getLeft()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getLeft()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getLeft()) + ", ");
                    sline = sline + fullmap.listType.get(s.getLeft()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getLeft()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getLeft()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getLeft()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getLeft()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getLeft()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getLeft()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getLeft()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getLeft()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getLeft()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getLeft()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getLeft()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getLeft()) + ", ";
                }
                break;
            }
            for (int n = first+1; n <= second -1; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }
            switch (second)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getMiddle()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getMiddle()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getMiddle()) + ", ");
                    sline = sline + fullmap.listType.get(s.getMiddle()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getMiddle()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getMiddle()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getMiddle()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getMiddle()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getMiddle()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getMiddle()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getMiddle()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getMiddle()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getMiddle()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getMiddle()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getMiddle()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getMiddle()) + ", ";
                }
                break;
            }
            for (int n = second+1; n <= 5; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }


            System.out.print(" " + davg);
            System.out.println(") " + "Count = " + count[s.getLeft()][s.getMiddle()]);
            sline = sline + davg + ", " + count[s.getLeft()][s.getMiddle()];
            try {
                readCSV.writeCsvFile(filename, sline);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        String q = "Greater than threshold " +icounter;
        System.out.println("Greater than threshold " +icounter);
        try {
            readCSV.writeCsvFile(filename, q);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void fakeq3D(generalMap map, ArrayList<rowdata> data, int minThresh, int a, int b, int c, mapData fullmap) {
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


    public static void q3D(generalMap map, ArrayList<rowdata> data, int minThresh, int a, int b, int c, mapData fullmap, String filename) {
        ArrayList<Integer> so = new ArrayList<>();
        so.add(a);
        so.add(b);
        so.add(c);
        Collections.sort(so);
        int first = so.get(0);
        int second = so.get(1);
        int third = so.get(2);

        int[][][] count = new int[map.map[first].length][map.map[second].length][map.map[third].length];
        int[][][] sum = new int[map.map[first].length][map.map[second].length][map.map[third].length];
        ArrayList<Quartet<Integer, Integer, Integer, Integer>> successPairs = new ArrayList<>();

        for (rowdata d : data) {
            count[d.d[first]][d.d[second]][d.d[third]] = count[d.d[first]][d.d[second]][d.d[third]] + 1;
            sum[d.d[first]][d.d[second]][d.d[third]] = sum[d.d[first]][d.d[second]][d.d[third]] + fullmap.listPrice.get(d.d[6]);
        }
        int icounter = 0;
        for (int x = 0; x <= map.map[first].length - 1; x++) {
            for (int y = 0; y <= map.map[second].length - 1; y++) {
                for (int z = 0; z <= map.map[third].length - 1; z++) {
                    if (count[x][y][z] >= minThresh) {
                        Quartet<Integer, Integer, Integer, Integer> position = new Quartet<Integer, Integer, Integer, Integer>(x, y, z, sum[x][y][z]);
                        successPairs.add(position);
                        icounter = icounter + 1;
                    }
                }
            }
        }
        //Display Results
        for (Quartet<Integer, Integer, Integer, Integer> s : successPairs)
        {
            String sline = "";
            System.out.print("(");
            for (int n = 0; n <= first -1; n++)
            {
                sline = sline + "*, ";
                System.out.print("*, ");
            }
            double davg = s.getValue3()/((count[s.getValue0()][s.getValue1()][s.getValue2()])*1.0);
            switch (first)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getValue0()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listType.get(s.getValue0()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getValue0()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getValue0()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getValue0()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getValue0()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue0()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue0()) + ", ";
                }
                break;
            }
            for (int n = first+1; n <= second -1; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }
            switch (second)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getValue1()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listType.get(s.getValue1()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getValue1()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getValue1()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getValue1()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getValue1()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue1()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue1()) + ", ";
                }
                break;
            }
            for (int n = second+1; n <= third; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }
            switch (third)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getValue2()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listType.get(s.getValue2()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getValue2()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getValue2()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getValue2()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getValue2()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue2()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue2()) + ", ";
                }
                break;
            }
            for (int n = third+1; n <= 5; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }



            System.out.print(" " + davg);
            System.out.println(") " + "Count = " + count[s.getValue0()][s.getValue1()][s.getValue2()]);
            sline = sline + davg + ", " + count[s.getValue0()][s.getValue1()][s.getValue2()];
            try {
                readCSV.writeCsvFile(filename, sline);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        String q = "Greater than threshold " +icounter;
        System.out.println("Greater than threshold " +icounter);
        try {
            readCSV.writeCsvFile(filename, q);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public static void q4D(generalMap map, ArrayList<rowdata> data, int minThresh, int a, int b, int c, int e, mapData fullmap, String filename) {
        ArrayList<Integer> so = new ArrayList<>();
        so.add(a);
        so.add(b);
        so.add(c);
        so.add(e);
        Collections.sort(so);
        int first = so.get(0);
        int second = so.get(1);
        int third = so.get(2);
        int fourth = so.get(3);

        int[][][][] count = new int[map.map[first].length][map.map[second].length][map.map[third].length][map.map[fourth].length];
        int[][][][] sum = new int[map.map[first].length][map.map[second].length][map.map[third].length][map.map[fourth].length];
        ArrayList<Quintet<Integer, Integer, Integer, Integer, Integer>> successPairs = new ArrayList<>();

        for (rowdata d : data) {
            count[d.d[first]][d.d[second]][d.d[third]][d.d[fourth]] = count[d.d[first]][d.d[second]][d.d[third]][d.d[fourth]] + 1;
            sum[d.d[first]][d.d[second]][d.d[third]][d.d[fourth]] = sum[d.d[first]][d.d[second]][d.d[third]][d.d[fourth]] + fullmap.listPrice.get(d.d[6]);
        }
        int icounter = 0;
        for (int x = 0; x <= map.map[first].length - 1; x++) {
            for (int y = 0; y <= map.map[second].length - 1; y++) {
                for (int z = 0; z <= map.map[third].length - 1; z++) {
                    for (int w = 0; w <= map.map[fourth].length - 1; w++)
                    {
                        if (count[x][y][z][w] >= minThresh) {
                            Quintet<Integer, Integer, Integer, Integer, Integer> position = new Quintet<Integer, Integer, Integer, Integer, Integer>(x, y, z, w, sum[x][y][z][w]);
                            successPairs.add(position);
                            icounter = icounter + 1;
                        }
                    }

                }
            }
        }
        //Display Results
        for (Quintet<Integer, Integer, Integer, Integer, Integer> s : successPairs)
        {
            String sline = "";
            System.out.print("(");
            for (int n = 0; n <= first -1; n++)
            {
                sline = sline + "*, ";
                System.out.print("*, ");
            }
            double davg = s.getValue3()/((count[s.getValue0()][s.getValue1()][s.getValue2()][s.getValue3()])*1.0);
            switch (first)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getValue0()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listType.get(s.getValue0()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getValue0()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getValue0()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getValue0()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getValue0()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue0()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue0()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue0()) + ", ";
                }
                break;
            }
            for (int n = first+1; n <= second-1; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }
            switch (second)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getValue1()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listType.get(s.getValue1()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getValue1()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getValue1()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getValue1()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getValue1()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue1()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue1()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue1()) + ", ";
                }
                break;
            }
            for (int n = second+1; n <= third-1; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }
            switch (third)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getValue2()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listType.get(s.getValue2()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getValue2()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getValue2()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getValue2()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getValue2()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue2()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue2()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue2()) + ", ";
                }
                break;
            }
            for (int n = third+1; n <= fourth-1; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }
            switch (fourth)
            {
                case 0 :
                {
                    System.out.print(fullmap.listSuburbs.get(s.getValue3()) + ", ");
                    sline = sline + fullmap.listSuburbs.get(s.getValue3()) + ", ";
                }
                break;
                case 1 :
                {
                    System.out.print(fullmap.listType.get(s.getValue3()) + ", ");
                    sline = sline + fullmap.listType.get(s.getValue3()) + ", ";
                }
                break;
                case 2 :
                {
                    System.out.print(fullmap.listNoRooms.get(s.getValue3()) + ", ");
                    sline = sline + fullmap.listNoRooms.get(s.getValue3()) + ", ";
                }
                break;
                case 3 :
                {
                    System.out.print(fullmap.listNoBathRooms.get(s.getValue3()) + ", ");
                    sline = sline + fullmap.listNoBathRooms.get(s.getValue3()) + ", ";
                }
                break;
                case 4 :
                {
                    System.out.print(fullmap.listNoParking.get(s.getValue3()) + ", ");
                    sline = sline + fullmap.listNoParking.get(s.getValue3()) + ", ";
                }
                break;
                case 5 :
                {
                    System.out.print(fullmap.listArea.get(s.getValue3()) + ", ");
                    sline = sline + fullmap.listArea.get(s.getValue3()) + ", ";
                }
                break;
                case 6 :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue3()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue3()) + ", ";
                }
                break;
                default :
                {
                    System.out.print(fullmap.listPrice.get(s.getValue3()) + ", ");
                    sline = sline + fullmap.listPrice.get(s.getValue3()) + ", ";
                }
                break;
            }
            for (int n = fourth+1; n < 5; n++)
            {
                System.out.print("*, ");
                sline = sline + "*, ";
            }


            System.out.print(" " + davg);
            System.out.println(") " + "Count = " + count[s.getValue0()][s.getValue1()][s.getValue2()][s.getValue3()]);
            sline = sline + davg + ", " + count[s.getValue0()][s.getValue1()][s.getValue2()][s.getValue3()];
            try {
                readCSV.writeCsvFile(filename, sline);
            }
            catch (Exception g)
            {
                g.printStackTrace();
            }
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



