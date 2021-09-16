package Interview;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统计某字符串在文件中出现的次数
 *
 * 注意事项：
 * 1. 默认文件里的字符串是按行进行统计的，如果字符串存在跨行的情况，那需要考虑把字符串进行拼接、去除换行符。这里未考虑
 * 2. 字符串里出现的字符串的次数的问题可以使用： indexOf 方法配合 substring 方法获取；正则表达匹配；替换指定单词未空，通过
 *    缩减长度 / 单词长度，即未次数。这里只用正则实现。
 */
public class TestCountWord {
    public static void main(String[] args) {
        String filePath = "/Users/jacksteve/Downloads/test.txt";
        String word = "线程";
        System.out.println(countWordAppearTimes(filePath,word));
    }

    /**
     * 统计每行的出现单词的出现次数之后
     * @param filePath  文件路径
     * @param word  搜索的单词
     * @return  返回单词出现的次数
     */
    public static int countWordAppearTimes(String filePath , String word){
        //单词出现次数的结果
        int result = 0;
        //匹配单词的正则：
        Pattern p = Pattern.compile(word);

        File f = new File(filePath);
        InputStreamReader ir = null;
        BufferedReader br = null;
        try{
            ir = new InputStreamReader(new FileInputStream(f),"UTF-8");
            br = new BufferedReader(ir);
            String line;
            //读文件的每一行字符串
            while((line = br.readLine()) != null){
                //按照单词的正则，查找出现次数
                Matcher m = p.matcher(line);
                while(m.find()){
                    result++;
                }
            }
        } catch (IOException e) {
            //找不到文件异常
            e.printStackTrace();
        } finally {
            //最后安静的关闭
            try{
                if(br!=null){
                    br.close();
                }
                if(ir!=null){
                    ir.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
