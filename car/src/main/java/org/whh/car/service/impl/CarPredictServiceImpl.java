package org.whh.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.whh.car.common.PredictPrice;
import org.whh.car.entity.CarPredict;
import org.whh.car.exception.CustomException;
import org.whh.car.mapper.CarPredictMapper;
import org.whh.car.service.CarPredictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2023-12-26
 */
@Service
public class CarPredictServiceImpl extends ServiceImpl<CarPredictMapper, CarPredict> implements CarPredictService {

    @Autowired
    private CarPredictMapper carPredictMapper;
    @Override
    public PredictPrice predit(CarPredict carPredict)  {
        String brand = carPredict.getBrand();
        String local = carPredict.getLocal();
        String time = carPredict.getTime();
        String mileage = carPredict.getMileage();
        String sprice = carPredict.getSprice();
        String s = null;
        s = org.whh.car.util.Predict.runPythonScript(mileage, sprice, brand, local, time);
        System.out.println(s);
        String[] results = s.split(" ");
        double price1 = Double.parseDouble(results[0]);
        double price2 = Double.parseDouble(results[1]);
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedPrice1 = df.format(price1);
        String formattedPrice2 = df.format(price2);
        return new PredictPrice(formattedPrice1,formattedPrice2);
    }

    @Override
    public List<String> getTimes() {
        return carPredictMapper.getTimes();
    }
    @Override
    public String getTimeId(String time) {
        return carPredictMapper.getTimeId(time);
    }

    @Override
    public List<String> getLocals() {
        return carPredictMapper.getLocals();
    }
    @Override
    public String getLocalId(String local) {
        return carPredictMapper.getLocalId(local);
    }

    @Override
    public List<String> getBrands() {
        return carPredictMapper.getBrands();
    }
    @Override
    public String getBrandAndId(String brand) {
        return carPredictMapper.getBrandAndId(brand);
    }

    @Override
    public CarPredict getDate(CarPredict carPredict){
        double mileageMin = 0.01;
        double mileageMax = 19.0;
        double spriceMin = 2.6;
        double spriceMax = 8000.0;

        String brand = carPredict.getBrand();
        String local = carPredict.getLocal();
        String time = carPredict.getTime();
        String mileage = carPredict.getMileage();
        String sprice = carPredict.getSprice();

        double mil = 0;
        double sp = 0;

        try {
            mil = Double.parseDouble(mileage);
            sp = Double.parseDouble(sprice);
        } catch (NumberFormatException e) {
            throw new CustomException("输入数据类型不匹配");
        }
        if(mil>mileageMax||mil<mileageMin){
            throw new CustomException("输入汽车公里数不能超出0.01到19.00万公里范围");
        }
        if(sp>spriceMax||sp<spriceMin){
            throw new CustomException("输入汽车原价不能超出2.60到8000.00万元范围");
        }

        String brandAndId = getBrandAndId(brand);
        String localId = getLocalId(local);
        String timeId = getTimeId(time);

        double normalizedMileage = (mil - mileageMin) / (mileageMax - mileageMin);
        double normalizedSprice = (sp - spriceMin) / (spriceMax - spriceMin);
        DecimalFormat df = new DecimalFormat("#.###############"); // 设置保留16位小数的格式
        String mileage1 = df.format(normalizedMileage);
        String sprice1 = df.format(normalizedSprice);

        return new CarPredict(brandAndId, localId, timeId, mileage1, sprice1);
    }


}
