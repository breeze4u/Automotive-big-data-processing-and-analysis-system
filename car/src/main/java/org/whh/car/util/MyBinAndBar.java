package org.whh.car.util;

import org.whh.car.common.Tu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBinAndBar {

    public static List<Map<String, Object>> BinData(ArrayList<Map<String, Object>> maps,List<Tu> list){
        for (Tu tu:list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",tu.getName());
            map.put("value",tu.getNum());
            maps.add(map);
        }return maps;
    }

    public static HashMap<String, Object> BarData(ArrayList<String> xAxis,ArrayList<Integer> yAxis,List<Tu> list){
        for (Tu tu:list) {
            xAxis.add(tu.getName());
            yAxis.add(tu.getNum());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("xAxis",xAxis);
        map.put("yAxis",yAxis);
        return map;
    }

}
