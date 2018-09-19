package devilliers_214062813.nmu;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class mapData {

    public ArrayList<String> listSuburbs;
    public HashSet<String> possibleSuburbs;


    public ArrayList<String> listType;
    public HashSet<String> possibleType;

    public ArrayList<Integer> listNoRooms;
    public HashSet<Integer> possibleNoRooms;

    public ArrayList<Integer> listNoBathRooms;
    public HashSet<Integer> possibleNoBathRooms;

    public ArrayList<Integer> listNoParking;
    public HashSet<Integer> possibleNoParking;

    public ArrayList<Integer> listArea;
    public HashSet<Integer> possibleArea;

    public ArrayList<Integer> listPrice;
    public HashSet<Integer> possiblePrice;

    public mapData() {
        this.listSuburbs = new ArrayList<>();
        this.possibleSuburbs = new HashSet<>();
        this.listType = new ArrayList<>();
        this.possibleType = new HashSet<>();
        this.listNoRooms = new ArrayList<>();
        this.possibleNoRooms = new HashSet<>();
        this.listNoBathRooms = new ArrayList<>();
        this.possibleNoBathRooms = new HashSet<>();
        this.listNoParking = new ArrayList<>();
        this.possibleNoParking = new HashSet<>();
        this.listArea = new ArrayList<>();
        this.possibleArea = new HashSet<>();
        this.listPrice = new ArrayList<>();
        this.possiblePrice = new HashSet<>();
    }

    public mapData(ArrayList<String> listSuburbs, HashSet<String> possibleSuburbs, ArrayList<String> listType, HashSet<String> possibleType, ArrayList<Integer> listNoRooms, HashSet<Integer> possibleNoRooms, ArrayList<Integer> listNoBathRooms, HashSet<Integer> possibleNoBathRooms, ArrayList<Integer> listNoParking, HashSet<Integer> possibleNoParking, ArrayList<Integer> listArea, HashSet<Integer> possibleArea, ArrayList<Integer> listPrice, HashSet<Integer> possiblePrice) {
        this.listSuburbs = listSuburbs;
        this.possibleSuburbs = possibleSuburbs;
        this.listType = listType;
        this.possibleType = possibleType;
        this.listNoRooms = listNoRooms;
        this.possibleNoRooms = possibleNoRooms;
        this.listNoBathRooms = listNoBathRooms;
        this.possibleNoBathRooms = possibleNoBathRooms;
        this.listNoParking = listNoParking;
        this.possibleNoParking = possibleNoParking;
        this.listArea = listArea;
        this.possibleArea = possibleArea;
        this.listPrice = listPrice;
        this.possiblePrice = possiblePrice;
    }

    public boolean populateLists()
    {
        this.listSuburbs.addAll(possibleSuburbs);
        this.listType.addAll(possibleType);
        this.listNoRooms.addAll(possibleNoRooms);
        this.listNoBathRooms.addAll(possibleNoBathRooms);
        this.listNoParking.addAll(possibleNoParking);
        this.listArea.addAll(possibleArea);
        this.listPrice.addAll(possiblePrice);
        return true;
    }

    public boolean add(String input) {
        String[] sdata = input.split(",");
        boolean success = false;
        if (sdata.length == 7)
        {
            possibleSuburbs.add(sdata[0]);
            possibleType.add(sdata[1]);

            if (StringUtils.isNumeric(sdata[2]))
            {
                possibleNoRooms.add(Integer.valueOf(sdata[2]));
            }
            else
                success = false;
            if (StringUtils.isNumeric(sdata[3]))
            {
                possibleNoBathRooms.add(Integer.valueOf(sdata[3]));
            }
            else
                success = false;
            if (StringUtils.isNumeric(sdata[4]))
            {
                possibleNoParking.add(Integer.valueOf(sdata[4]));
            }
            else
                success = false;
            if (StringUtils.isNumeric(sdata[5]))
            {
                possibleArea.add(Integer.valueOf(sdata[5]));
            }
            else
                success = false;
            if (StringUtils.isNumeric(sdata[6]))
            {
                possiblePrice.add(Integer.valueOf(sdata[6]));
            }
            else
                success = false;
        }
        else
            success = false;
        return success;
    }

    @Override
    public String toString() {
        return "mapData{" +
                "listSuburbs=" + listSuburbs +
                ", listType=" + listType +
                ", listNoRooms=" + listNoRooms +
                ", listNoBathRooms=" + listNoBathRooms +
                ", listNoParking=" + listNoParking +
                ", listArea=" + listArea +
                ", listPrice=" + listPrice +
                '}';
    }

    public String toStringOrder() {
        return "mapData{" +
                " listArea=" + listArea +
                ", listNoParking=" + listNoParking +
                ", listNoBathRooms=" + listNoBathRooms +
                ", listNoRooms=" + listNoRooms +
                "listSuburbs=" + listSuburbs +
                ", listType=" + listType+
                '}';
    }

}

class stringCompare implements Comparator<String>
{
    @Override
    public int compare(String a, String b)
    {
        return a.compareTo(b);
    }
}
