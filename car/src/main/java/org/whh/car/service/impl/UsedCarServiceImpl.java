package org.whh.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.whh.car.common.Params;
import org.whh.car.entity.UsedCar;
import org.whh.car.mapper.UsedCarMapper;
import org.whh.car.service.UsedCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
@Service
public class UsedCarServiceImpl extends ServiceImpl<UsedCarMapper, UsedCar> implements UsedCarService {
    @Autowired
    private UsedCarMapper usedCarMapper;

    @Override
    public List<UsedCar> findListByParams(Params params) {

        QueryWrapper<UsedCar> wrapper = new QueryWrapper<>();
        if (params != null && params.getInfor1() !=null && !params.getInfor1().equals(""))
            wrapper.like("brand",params.getInfor1());
        if (params != null && params.getInfor2() !=null && !params.getInfor2().equals(""))
            wrapper.like("local",params.getInfor2());
        if (params != null && params.getInfor3() !=null && !params.getInfor3().equals(""))
            wrapper.like("mileage",params.getInfor3());
        if (params != null && params.getInfor4() !=null && !params.getInfor4().equals(""))
            wrapper.like("price",params.getInfor4());
        return usedCarMapper.selectList(wrapper);
    }

}
