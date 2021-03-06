package dice.sinanya.db.kp;

import dice.sinanya.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesKp.KP_GROUP;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询KP主群类，刷写到静态变量中，只在静态变量中找不到时才需要使用
 */
public class SelectKp {
    private static final Logger Log = LogManager.getLogger(SelectKp.class);

    public SelectKp() {
        //        初始化时无需逻辑
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public void flushKpFromDatabase() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from kp";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        KP_GROUP.put(set.getString("qqId"), set.getString("groupId"));
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
