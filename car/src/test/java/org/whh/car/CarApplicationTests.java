package org.whh.car;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.whh.car.common.PredictPrice;
import org.whh.car.entity.CarPredict;
import org.whh.car.service.CarPredictService;
import org.whh.car.util.Predict;
import org.whh.car.mapper.NewCarMapper;
import org.whh.car.service.NewCarService;
import java.io.IOException;
import java.text.DecimalFormat;


@SpringBootTest
class CarApplicationTests {
    @Autowired
    private NewCarMapper newCarMapper;
    @Autowired
    private NewCarService newCarService;

    @Autowired
    private CarPredictService carPredictService;

    @Test
    public void  test() throws IOException, InterruptedException {
        String s = Predict.runPythonScript("1.6", "0.018968664816065225", "18", "5", "39");
        System.out.println(s);
        String[] results = s.split(" ");
        double price1 = Double.parseDouble(results[0]);
        double price2 = Double.parseDouble(results[1]);
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedPrice1 = df.format(price1);
        String formattedPrice2 = df.format(price2);
        System.out.println(formattedPrice1);
        System.out.println(formattedPrice2);
    }


    @Test
    public void  test1() throws IOException, InterruptedException {
        CarPredict carPredict = new CarPredict("总裁", "广州", "2023款", "2.3", "23");
        CarPredict date = carPredictService.getDate(carPredict);
        System.out.println(date);
        PredictPrice predit = carPredictService.predit(date);
        System.out.println(predit);
    }






}
