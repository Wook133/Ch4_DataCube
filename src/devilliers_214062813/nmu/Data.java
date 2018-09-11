package devilliers_214062813.nmu;

public class Data {
    Integer suburb;
    Integer type;
    Integer noroom;
    Integer nobaths;
    Integer noparking;
    Integer area;
    Integer price;
//81435752, 999
//17353434
    //1 40  SUM of the 17 Count =17
    //...
    public Data(Integer suburb, Integer type, Integer noroom, Integer nobaths, Integer noparking, Integer area, Integer price) {
        this.suburb = suburb;
        this.type = type;
        this.noroom = noroom;
        this.nobaths = nobaths;
        this.noparking = noparking;
        this.area = area;
        this.price = price;
    }

    public Data(mapData map, String sinput)
    {
        String[] sData = sinput.split(",");
        for (String s : sData)
        {
           // System.out.print(s + "_");
        }
        if (map.possibleSuburbs.contains(sData[0]))
        {
            suburb = map.listSuburbs.indexOf(sData[0]);
        }
        if (map.possibleType.contains(sData[1]))
        {
            type = map.listType.indexOf(sData[1]);
        }
        Integer cur;
        cur = map.listNoRooms.indexOf(Integer.valueOf(sData[2]));
        noroom = cur;

/*1
        90
                17
                        77*/

        cur = map.listNoBathRooms.indexOf(Integer.valueOf(sData[3]));
        nobaths = cur;
        cur = map.listNoParking.indexOf(Integer.valueOf(sData[4]));
        noparking = cur;

        cur = map.listArea.indexOf(Integer.valueOf(sData[5]));
        area = cur;

        cur = map.listPrice.indexOf(Integer.valueOf(sData[6]));
        price = cur;
    }

    @Override
    public String toString() {
        return "Data{" +
                "suburb=" + suburb +
                ", type=" + type +
                ", noroom=" + noroom +
                ", nobaths=" + nobaths +
                ", noparking=" + noparking +
                ", area=" + area +
                ", price=" + price +
                '}';
    }
    public String toStringPrint(mapData map) {
        return "Data{" +
                "suburb=" + map.listSuburbs.get(suburb) +
                ", type=" + map.listType.get(type) +
                ", noroom=" + map.listNoRooms.get(noroom) +
                ", nobaths=" + map.listNoBathRooms.get(nobaths) +
                ", noparking=" + map.listNoParking.get(noparking) +
                ", area=" + map.listArea.get(area) +
                ", price=" + map.listPrice.get(price) +
                '}';
    }

    public void comments()
    {
              /*System.out.println(cur + " : " + sData[6] + " = " + price + " = " + Integer.valueOf(sData[6]) + " MAP = " + map.listPrice.indexOf(sData[6]) + " == " + map.listPrice.indexOf(cur) +"___"+ map.listPrice.indexOf(Integer.valueOf(sData[6])));
        /*for (Integer m : map.listPrice)
        {
            System.out.println(m+"_");
        }*/

       /* if (map.possibleNoRooms.contains(Integer.valueOf(sData[2])))
        {
            noroom = map.listNoRooms.indexOf(sData[2]);
        }*/
        /*if (map.possibleNoBathRooms.contains(Integer.valueOf(sData[3])))
        {
            nobaths = map.listNoBathRooms.indexOf(sData[3]);
        }
        if (map.possibleNoParking.contains(Integer.valueOf(sData[4])))
        {
            noparking = map.listNoParking.indexOf(sData[4]);
        }
        if (map.possibleArea.contains(Integer.valueOf(sData[5])))
        {
            area = map.listArea.indexOf(sData[5]);
        }
        if (map.possiblePrice.contains(Double.valueOf(sData[6])))
        {
            price = map.listPrice.indexOf(sData[6]);
        }*/
    }

}
