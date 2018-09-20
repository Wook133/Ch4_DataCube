package devilliers_214062813.nmu;

import java.util.ArrayList;

public class generalMap {
    public int[][] map = new int[7][];

    public generalMap(mapData md) {
        map[0]= new int[md.listSuburbs.size()];
        for (int i = 0; i <= md.listSuburbs.size() - 1; i++)
        {
            map[0][i] = i;
        }
        map[1]= new int[md.listType.size()];
        for (int i = 0; i <= md.listType.size() - 1; i++)
        {
            map[1][i] = i;
        }
        map[2]= new int[md.listNoRooms.size()];
        for (int i = 0; i <= md.listNoRooms.size() - 1; i++)
        {
            map[2][i] = i;
        }
        map[3]= new int[md.listNoBathRooms.size()];
        for (int i = 0; i <= md.listNoBathRooms.size() - 1; i++)
        {
            map[3][i] = i;
        }
        map[4]= new int[md.listNoParking.size()];
        for (int i = 0; i <= md.listNoParking.size() - 1; i++)
        {
            map[4][i] = i;
        }
        map[5]= new int[md.listArea.size()];
        for (int i = 0; i <= md.listArea.size() - 1; i++)
        {
            map[5][i] = i;
        }
        map[6]= new int[md.listPrice.size()];
        for (int i = 0; i <= md.listPrice.size() - 1; i++)
        {
            map[6][i] = i;
        }
    }
}
