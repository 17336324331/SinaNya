package dice.sinanya.tools.getinfo;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 当前时间的相关方法
 */
public class GetTime {


    private GetTime() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @return 获取当前时间（格式化后的）
     */
    static public String getNowString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 将格式化好的时间转化为时间戳
     *
     * @param time 传入格式化后的时间
     * @return 时间戳
     */
    public static Timestamp getTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = null;

        try {
            d = format.parse(time);
        } catch (Exception e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }

        return new Timestamp((d != null)
                ? d.getTime()
                : 0);
    }

}
