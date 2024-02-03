package org.whh.car.service;

import org.whh.car.common.Params;
import org.whh.car.entity.UsedCar;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
public interface UsedCarService extends IService<UsedCar> {

    List<UsedCar> findListByParams(Params params);
}
