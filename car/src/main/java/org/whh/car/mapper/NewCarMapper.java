package org.whh.car.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.whh.car.entity.NewCar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.whh.car.common.Tu;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
@Mapper
public interface NewCarMapper extends BaseMapper<NewCar> {


    @Select("SELECT `name`,count(`name`) AS num FROM `new_car` GROUP BY `name` ORDER BY count(`name`) DESC LIMIT 30")
    List<Tu> getBrandTOP();
}
