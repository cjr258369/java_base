import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class test223 {
    //public static String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
    public static String regex = "([^省]+自治区|.*?省|.*?行政区|.*?市)([^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)";
    public static Pattern pattern = Pattern.compile(regex);

    public static String provinceRegex = "(上海市|北京市|天津市|重庆市|.*?省|.*?行政区|.*?自治区)";
    public static String cityRegex = "([^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)";
    public static Pattern provincePpattern = Pattern.compile(provinceRegex);
    public static Pattern cityPpattern = Pattern.compile(cityRegex);

    public static void main(String[] args) {
        String address[] = new String[]{
                "湖北省武汉市武昌区湖北省武汉市武昌区万科朗苑A栋203","湖北省武汉市武昌区广东省武汉市武昌区万科朗苑A栋203",
                "广东省武汉市武昌区广东省武汉市武昌区万科朗苑A栋203","湖北省广州市武昌区广东省广州市武昌区万科朗苑A栋203",
                "湖北省湖北省武汉市武昌区万科朗苑A栋203","武汉市武昌区湖北省武汉市武昌区万科朗苑A栋203",
                "武昌区湖北省武汉市武昌区万科朗苑A栋203","湖北省武汉市武昌区湖北省武汉市武昌区万科朗苑A栋203",
                "湖北省武汉市武昌区万科朗苑A栋203","北京市北京市武昌区万科朗苑A栋203","北京市武昌区万科朗苑A栋203","北京市北京市北京市北京市武昌区万科朗苑A栋203"
                ,"北京市朝阳区的广东省广州市朝阳区快感尽快跟进街23号"};
        for(String s : address){
            System.out.println(s);
            System.out.println("isRepeat_old = " + isRepeat(s));
            System.out.println("isRepeat_new = " + isRepeat2(s));
            System.out.println("-----------------------");
        }
    }

    public static boolean isRepeat(String address){
        Matcher m = pattern.matcher(address);
        String province = "", city = "";
        if(m.find()){
            province=m.group(1);
            city=m.group(2);
        }
        int provinceCount = 0, cityCount = 0;
        //使用循环来计算字符串中省份的出现次数
        for(int i = 0; province.length() > 0 && i < address.length() - province.length(); i++){
            provinceCount += address.startsWith(province, i) ? 1 : 0;
        }
        //使用循环来计算字符串中市的出现次数
        for(int i = 0; city.length() > 0 && i < address.length() - city.length(); i++){
            cityCount += address.startsWith(city, i) ? 1 : 0;
        }
        System.out.println("省份：" + province + "，出现次数：" + provinceCount + "次，市：" + city + " ，出现次数：" + cityCount);
        return provinceCount >= 2 || cityCount >= 2;
    }
    
    public static boolean isRepeat2(String address){
        int provinceCount = 0, cityCount = 0;
        Matcher m1 = provincePpattern.matcher(address);
        while(m1.find()){
            //System.out.println("省：" + m1.group());
            provinceCount++;
        }
        Matcher m2 = cityPpattern.matcher(address);
        while(m2.find()){
            //System.out.println("市：" + m2.group());
            cityCount++;
        }
        return provinceCount >= 2 || cityCount >= 2;
    }
    
}