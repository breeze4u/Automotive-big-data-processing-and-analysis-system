package org.whh.car.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.whh.car.entity.CarPredict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whh
 * @since 2023-12-26
 */
@Mapper
public interface CarPredictMapper extends BaseMapper<CarPredict> {

    @Select("select brand from brand")
    List<String> getBrands();
    @Select("select id from brand where brand = #{brand}")
    String getBrandAndId(String brand);

    @Select("select time from time ORDER BY time")
    List<String> getTimes();
    @Select("select id from time where time = #{time}")
    String getTimeId(String time);

    @Select("select local from local")
    List<String> getLocals();
    @Select("select id from local where local = #{local}")
    String getLocalId(String local);


}
