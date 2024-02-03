package org.whh.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.whh.car.common.Params;
import org.whh.car.entity.Sale;
import org.whh.car.common.Tu;
import org.whh.car.mapper.SaleMapper;
import org.whh.car.service.SaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.whh.car.util.MyBinAndBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2023-12-14
 * infor1:时间 2023
 * infor2：时间 2023-10
 * infor3：name 汽车厂商
 * infor4：brand 汽车品牌
 */
@Service
public class SaleServiceImpl extends ServiceImpl<SaleMapper, Sale> implements SaleService {
    @Autowired
    private SaleMapper saleMapper;

    @Override
    public List<Sale> findListByParams(Params params) {
        QueryWrapper<Sale> wrapper = new QueryWrapper<>();
        if (params != null && params.getInfor1() !=null && !params.getInfor1().equals(""))
            wrapper.like("id",params.getInfor1());
        if (params != null && params.getInfor2() !=null && !params.getInfor2().equals(""))
            wrapper.like("name",params.getInfor2());
        if (params != null && params.getInfor3() !=null && !params.getInfor3().equals(""))
            wrapper.like("time",params.getInfor3());
        if (params != null && params.getInfor4() !=null && !params.getInfor4().equals(""))
            wrapper.like("brand",params.getInfor4());
        return saleMapper.selectList(wrapper);
    }
    @Override
    public ArrayList<Map<String, Object>> getName() {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<String> list = saleMapper.getName();
        for (String name:list){
            HashMap<String, Object> map = new HashMap<>();
            map.put("label",name);
            map.put("value",name);
            maps.add(map);
        }return maps;
    }
    @Override
    public ArrayList<Map<String, Object>> getBrand() {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<String> list = saleMapper.getBrand();
        for (String name:list){
            HashMap<String, Object> map = new HashMap<>();
            map.put("label",name);
            map.put("value",name);
            maps.add(map);
        }return maps;
    }

    @Override
    public List<Map<String, Object>> getBinNameSalesByMonth(Params params) {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<Tu> list = saleMapper.getByTime(params.getInfor1());
        return MyBinAndBar.BinData(maps, list);
    }
    @Override
    public HashMap<String, Object> getBarNameSalesByMonth(Params params) {
        ArrayList<String> xAxis = new ArrayList<>();
        ArrayList<Integer> yAxis = new ArrayList<>();
        List<Tu> list = saleMapper.getByTime(params.getInfor1());
        return MyBinAndBar.BarData(xAxis,yAxis,list);
    }



    @Override
    public List<Map<String, Object>> getBinMonthSalesByNameYear(Params params) {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<Tu> list = saleMapper.getByNameAndTime(params.getInfor1(),params.getInfor2()+"%");
        return MyBinAndBar.BinData(maps, list);
    }
    @Override
    public HashMap<String, Object> getBarMonthSalesByNameYear(Params params) {
        ArrayList<String> xAxis = new ArrayList<>();
        ArrayList<Integer> yAxis = new ArrayList<>();
        List<Tu> list = saleMapper.getByNameAndTime(params.getInfor1(), params.getInfor2()+"%");
        return MyBinAndBar.BarData(xAxis,yAxis,list);
    }



    @Override
    public List<Map<String, Object>> getBinBrandSaleByNameMonth(Params params) {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<Tu> list = saleMapper.getBrandSaleByNameMonth(params.getInfor1(), params.getInfor2());
        return MyBinAndBar.BinData(maps,list);
    }
    @Override
    public HashMap<String, Object> getBarBrandSaleByNameMonth(Params params) {
        ArrayList<String> xAxis = new ArrayList<>();
        ArrayList<Integer> yAxis = new ArrayList<>();
        List<Tu> list = saleMapper.getBrandSaleByNameMonth(params.getInfor1(), params.getInfor2());
        return MyBinAndBar.BarData(xAxis,yAxis,list);
    }



    @Override
    public List<Map<String, Object>> getBinBrandSaleByMonth(Params params) {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<Tu> list = saleMapper.getBrandSaleByMonth(params.getInfor1());
        return MyBinAndBar.BinData(maps, list);
    }
    @Override
    public HashMap<String, Object> getBarBrandSaleByMonth(Params params) {
        ArrayList<String> xAxis = new ArrayList<>();
        ArrayList<Integer> yAxis = new ArrayList<>();
        List<Tu> list = saleMapper.getBrandSaleByMonth(params.getInfor1());
        return MyBinAndBar.BarData(xAxis,yAxis,list);
    }




}
