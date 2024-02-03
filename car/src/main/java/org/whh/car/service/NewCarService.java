package org.whh.car.service;

import org.whh.car.entity.NewCar;
import com.baomidou.mybatisplus.extension.service.IService;
import org.whh.car.common.Params;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
public interface NewCarService extends IService<NewCar> {


    List<NewCar> findListByParams(Params params);
    List<Map<String, Object>> getBinTop20();
    HashMap<String, Object> getBarTop20();

}
