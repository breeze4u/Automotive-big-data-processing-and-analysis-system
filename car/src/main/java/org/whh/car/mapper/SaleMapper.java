package org.whh.car.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.whh.car.entity.Sale;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.whh.car.common.Tu;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whh
 * @since 2023-12-14
 */
@Mapper
public interface SaleMapper extends BaseMapper<Sale> {

    @Select("SELECT DISTINCT `name` FROM `sale`")
    List<String> getName();
    @Select("SELECT DISTINCT `brand` FROM `sale`")
    List<String> getBrand();
    @Select("SELECT DISTINCT`name`,sales as num FROM `sale` WHERE time=#{time} ORDER BY num DESC LIMIT 30")
    List<Tu> getByTime(String time);
    @Select("SELECT DISTINCT time AS `name`, sales AS num FROM `sale` WHERE  `name`= #{name} and time like #{time} ORDER BY time")
    List<Tu> getByNameAndTime(@Param("name") String name,@Param("time") String time);

    @Select("SELECT DISTINCT brand as `name` ,monthSales as num FROM `sale` WHERE `name`= #{name} AND time = #{time} ORDER BY monthSales DESC")
    List<Tu> getBrandSaleByNameMonth(@Param("name") String name,@Param("time") String time);

    @Select("SELECT DISTINCT brand AS `name`,monthSales as num FROM `sale` WHERE time= #{time} ORDER BY monthSales desc LIMIT 30 ")
    List<Tu> getBrandSaleByMonth(@Param("time") String time);

}
