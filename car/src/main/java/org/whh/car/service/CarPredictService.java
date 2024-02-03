package org.whh.car.service;

import org.apache.ibatis.annotations.Select;
import org.whh.car.common.PredictPrice;
import org.whh.car.entity.CarPredict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2023-12-26
 */
public interface CarPredictService extends IService<CarPredict> {

    PredictPrice predit(CarPredict carPredict) throws IOException, InterruptedException;



    List<String> getBrands();

    String getBrandAndId(String brand);


    List<String> getTimes();

    String getTimeId(String time);


    List<String> getLocals();


    String getLocalId(String local);

    public CarPredict getDate(CarPredict carPredict);


}
