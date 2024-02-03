package org.whh.car.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.whh.car.common.Params;
import org.whh.car.common.Result;
import org.whh.car.entity.*;
import org.whh.car.service.NewCarService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/newCar")
public class NewCarController {
    @Autowired
    private NewCarService newCarService;

    @GetMapping("/getListByParams")
    public Result findByParams(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<NewCar> newCars = newCarService.findListByParams(params);
        return Result.success(PageInfo.of(newCars));
    }


    @GetMapping("/bin")
    public Result Bin(){
        List<Map<String, Object>> brandTop20 = newCarService.getBinTop20();
        return Result.success(brandTop20);
    }

    @GetMapping("/bar")
    public Result Bar(){
        HashMap<String, Object> brandTop20 = newCarService.getBarTop20();
        return Result.success(brandTop20);
    }

}

