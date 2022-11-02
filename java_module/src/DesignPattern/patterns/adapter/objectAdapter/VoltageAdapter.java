package DesignPattern.patterns.adapter.objectAdapter;

/**
 * @date 2022/11/1
 * 
 * 适配器类：它聚合一个 被适配的类 的实例，并实现适配接口
 */
public class VoltageAdapter implements IVoltage5V {
    
    private Voltage220V voltage220V;    //关联关系中的聚合

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int dstV = 0;
        if(null != voltage220V){
            //获取到 220V 的电压
            int srcV = voltage220V.output220V();
            System.out.println("使用对象适配器，进行适配~~~");
            //适配转换，转成 5V
            dstV = srcV / 44;
            System.out.println("适配完成，输出的电压为：" + dstV);
        }
        return dstV;
    }
}
