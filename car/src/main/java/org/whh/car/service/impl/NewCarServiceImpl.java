package org.whh.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.whh.car.entity.NewCar;
import org.whh.car.common.Params;
import org.whh.car.common.Tu;
import org.whh.car.mapper.NewCarMapper;
import org.whh.car.service.NewCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
@Service
public class NewCarServiceImpl extends ServiceImpl<NewCarMapper, NewCar> implements NewCarService {
    @Autowired
    private NewCarMapper newCarMapper;

    @Override
    public List<NewCar> findListByParams(Params params) {
        QueryWrapper<NewCar> wrapper = new QueryWrapper<>();
        if (params != null && params.getInfor1() !=null && !params.getInfor1().equals(""))
            wrapper.like("name",params.getInfor1());
        if (params != null && params.getInfor2() !=null && !params.getInfor2().equals(""))
            wrapper.like("brand",params.getInfor2());
        if (params != null && params.getInfor3() !=null && !params.getInfor3().equals(""))
            wrapper.like("model",params.getInfor3());
        if (params != null && params.getInfor4() !=null && !params.getInfor4().equals(""))
            wrapper.like("price",params.getInfor4());
        return newCarMapper.selectList(wrapper);
    }

    @Override
    public List<Map<String, Object>> getBinTop20() {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<Tu> list = newCarMapper.getBrandTOP();
        for (Tu tu:list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",tu.getName());
            map.put("value",tu.getNum());
            maps.add(map);
        }
        return maps;
    }

    @Override
    public HashMap<String, Object> getBarTop20() {
        ArrayList<String> xAxis = new ArrayList<>();
        ArrayList<Integer> yAxis = new ArrayList<>();
        List<Tu> list = newCarMapper.getBrandTOP();
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
