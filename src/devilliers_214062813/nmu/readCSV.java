package devilliers_214062813.nmu;

import org.apache.commons.lang3.tuple.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class readCSV {
    private static final String NEW_LINE_SEPARATOR = "\n";
//http://www2.cs.uregina.ca/~dbd/cs831/notes/dcubes/dcubes.html

    public static Pair<mapData, ArrayList<Data>> READcsvfile(String sfilename)
    {
        mapData map = new mapData();
        ArrayList<Data> data = new ArrayList<>();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(sfilename));
            String line = null;
            Scanner scanner = null;
            int index = 0;
            reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                scanner = new Scanner(line);
                while (scanner.hasNext())//one pass through data to get all columns
                {
                    String sliner = scanner.next();
                    map.add(sliner);
                }
            }
            reader.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        map.populateLists();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(sfilename));
            String line = null;
            Scanner scanner = null;
            int index = 0;
            reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                scanner = new Scanner(line);
                while (scanner.hasNext())//one pass through data to get all columns
                {
                    String sliner = scanner.next();
                    //System.out.println(sliner);
                    Data curFF = new Data(map, sliner);
                    System.out.println(curFF.toString());
                    System.out.println(curFF.toStringPrint(map));
                    data.add(curFF);
                }
            }
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Pair<mapData, ArrayList<Data>> output = Pair.of(map, data);
        return output;
    }


}
