package dice.sinanya.db.system;

import dice.sinanya.db.tools.DbUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 录入机器人在某个群内的开关情况
 */
public class InsertBot {


    public static void deleteBot(long groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from switchBot where botId=? and groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                ps.setString(2, String.valueOf(groupId));
                ps.execute();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 将机器人的开关值插入某个群，true为开启，false为关闭
     *
     * @param groupId   群号
     * @param switchBot 开关
     */
    public void insertBot(long groupId, boolean switchBot) {
        int num = 0;
        if (CQ.getLoginQQ() == 0) {
            return;
        }
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from switchBot where groupId=? and botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, groupId);
                ps.setString(2, String.valueOf(CQ.getLoginQQ()));
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        num++;
                    }
                }
            }

            if (num == 0) {
                sql = "INSERT INTO switchBot(" +
                        "botId," +
                        "groupId," +
                        "switchBot" +
                        ") VALUES(?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                    ps.setLong(2, groupId);
                    ps.setBoolean(3, switchBot);
                    ps.executeUpdate();
                    CQ.logInfo("插入新的群开关", CQ.getLoginQQ() + "groupId: " + groupId + "switchBot: " + switchBot);
                }
            } else {
                sql = "update switchBot set " +
                        "switchBot=? where groupId=? and botId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setBoolean(1, switchBot);
                    ps.setLong(2, groupId);
                    ps.setString(3, String.valueOf(CQ.getLoginQQ()));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}
