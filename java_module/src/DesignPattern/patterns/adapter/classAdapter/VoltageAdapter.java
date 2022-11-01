package DesignPattern.patterns.adapter.classAdapter;

/**
 * @date 2022/11/1
 * 
 * 适配器类：它继承被适配的类，并实现适配接口
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V{
    
    @Override
    public int output5V() {
        //获取到 220V 的电压
        int srcV = output220V();
        //适配转换，转成 5V
        int dstV = srcV / 44;
        return dstV;
    }
}
