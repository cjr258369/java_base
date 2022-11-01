package DesignPattern.patterns.adapter.classAdapter;

/**
 * @date 2022/11/1
 */
public class Phone {
    //充电方法
    public void charging(IVoltage5V iVoltage5V){
        int outputVoltage = iVoltage5V.output5V();
        if(outputVoltage == 5){
            System.out.println("电压为 5V ，可以开始充电");
        }else if(outputVoltage > 5){
            System.out.println("电压大于 5V ，不能充电");
        }
    }
}
