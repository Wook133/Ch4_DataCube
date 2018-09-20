package devilliers_214062813.nmu;

public class rowdata {
    int[] d = new int[7];
    //    public Data(Integer suburb, Integer type, Integer noroom, Integer nobaths, Integer noparking, Integer area, Integer price)


    public rowdata(Integer suburb, Integer type, Integer noroom, Integer nobaths, Integer noparking, Integer area, Integer price) {
        d[0] = suburb;
        d[1] = type;
        d[2] = noroom;
        d[3] = nobaths;
        d[4] = noparking;
        d[5] = area;
        d[6] = price;
    }


    /*public rowdata(Integer suburb, Integer type, Integer noroom, Integer nobaths, Integer noparking, Integer area, Integer price) {
        d[4] = suburb;
        d[5] = type;
        d[3] = noroom;
        d[2] = nobaths;
        d[1] = noparking;
        d[0] = area;
        d[6] = price;
    }*/

    public rowdata(Data data) {
        d[0] = data.suburb;
        d[1] = data.type;
        d[2] = data.noroom;
        d[3] = data.nobaths;
        d[4] = data.noparking;
        d[5] = data.area;
        d[6] = data.price;
    }

   /* @Override
    public String toString() {
        return "Data{" +
                "suburb=" + d[4]  +
                ", type=" + d[5] +
                ", noroom=" + d[3] +
                ", nobaths=" + d[2] +
                ", noparking=" + d[1] +
                ", area=" + d[0] +
                ", price=" + d[6] +
                '}';
    }*/

       @Override
    public String toString() {
        return "Data{" +
                "suburb=" + d[0]  +
                ", type=" + d[1] +
                ", noroom=" + d[2] +
                ", nobaths=" + d[3] +
                ", noparking=" + d[4] +
                ", area=" + d[5] +
                ", price=" + d[6] +
                '}';
    }

    public String toStringPrint(mapData map) {
        return "Data{" +
                "suburb=" + map.listSuburbs.get(d[0]) +
                ", type=" + map.listType.get(d[1]) +
                ", noroom=" + map.listNoRooms.get(d[2]) +
                ", nobaths=" + map.listNoBathRooms.get(d[3]) +
                ", noparking=" + map.listNoParking.get(d[4]) +
                ", area=" + map.listArea.get(d[5]) +
                ", price=" + map.listPrice.get(d[6]) +
                '}';
    }


}
