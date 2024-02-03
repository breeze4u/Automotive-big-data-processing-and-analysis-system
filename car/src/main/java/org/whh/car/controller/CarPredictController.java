package org.whh.car.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whh.car.common.PredictPrice;
import org.whh.car.common.Result;
import org.whh.car.entity.CarPredict;
import org.whh.car.service.CarPredictService;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whh
 * @since 2023-12-26
 */
@RestController
@RequestMapping("/carPredict")
public class CarPredictController {
    @Autowired
    private CarPredictService carPredictService;

    @GetMapping("/predict")
    public Result predict(CarPredict carPredict){
        CarPredict date = carPredictService.getDate(carPredict);
        PredictPrice predit = null;
        try {
            predit = carPredictService.predit(date);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Result.success(predit);
    }

    @GetMapping("/brand")
    public Result getBrand(){
        List<String> brands = carPredictService.getBrands();
        return Result.success(brands);
    }
    @GetMapping("/time")
    public Result getTime(){
        List<String> times = carPredictService.getTimes();
        return Result.success(times);
    }
    @GetMapping("/local")
    public Result getLocal(){
        List<String> locals = carPredictService.getLocals();
        return Result.success(locals);
    }


}

