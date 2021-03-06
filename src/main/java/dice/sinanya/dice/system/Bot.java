package dice.sinanya.dice.system;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.dice.manager.imal.AtQq;
import dice.sinanya.entity.EntityGroupCensus;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.system.MessagesSystem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import static com.forte.qqrobot.beans.messages.types.MsgGetTypes.discussMsg;
import static com.forte.qqrobot.beans.messages.types.MsgGetTypes.groupMsg;
import static dice.sinanya.db.system.SelectBot.selectBot;
import static dice.sinanya.system.MessagesLoginInfo.ENTITY_LOGINQQ_INFO;
import static dice.sinanya.system.MessagesSystem.*;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.getinfo.SwitchBot.*;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 机器人控制类，如开关退群等
 */
public class Bot implements AtQq, MakeNickToSender {

    private Logger log = LogManager.getLogger(Bot.class.getName());

    private EntityTypeMessages entityTypeMessages;

    public Bot(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 开启
     */
    public void on() {
        String tag = TAG_BOT_ON;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        if (qqList.isEmpty()) {
            qqList.add(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
        }
        for (String qq : qqList) {
            if (qq.equals(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()))) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("can'tInPrivate"));
                    return;
                }
                if (getBot(groupId)) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("botAlreadyStart"));
                } else {
                    botOn(groupId);
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("botStart"));
                }
            }
        }
    }

    /**
     * 关闭
     */
    public void off() {
        String tag = TAG_BOT_OFF;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        if (qqList.isEmpty()) {
            qqList.add(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
        }

        for (String qq : qqList) {
            if (qq.equals(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()))) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("can'tInPrivate"));
                    return;
                }
                if (!getBot(groupId)) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("botAlreadyStop"));
                } else {
                    botOff(groupId);
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("botStop"));
                }
            }
        }
    }

    /**
     * 退群
     */
    public void exit() {
        String tag = TAG_BOT_EXIT;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        if (qqList.isEmpty()) {
            qqList.add(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
        }

        for (String qq : qqList) {
            if (qq.equals(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()))) {
                sender(entityTypeMessages, MESSAGES_SYSTEM.get("botExit"));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                    Thread.currentThread().interrupt();
                }
                if (entityTypeMessages.getMsgGetTypes() == groupMsg) {
                    entityTypeMessages.getMsgSender().SETTER.setGroupLeave(entityTypeMessages.getFromGroup());
                } else if (entityTypeMessages.getMsgGetTypes() == discussMsg) {
                    entityTypeMessages.getMsgSender().SETTER.setDiscussLeave(entityTypeMessages.getFromGroup());
                }
            }
        }
    }

    /**
     * 机器人信息
     */
    public void info() {
        String botInfo = MESSAGES_SYSTEM.get("botInfo").toString();
        if (!botInfo.equals(NONE)) {
            botInfo = "\n" + botInfo;
        }
        EntityGroupCensus entityGroupCensus = selectBot();
        sender(entityTypeMessages, STR_BOT_VERSIONS.toString() + "\n目前供职于:\t" + entityGroupCensus.getGroupNum() + " 个群，其中 " + entityGroupCensus.getOnNum() + " 个群处于开启状态" + botInfo);
    }

    /**
     * 机器人更新日志
     */
    public void update() {
        sender(entityTypeMessages, "当前版本:\t" + MessagesSystem.VERSIONS + "\n" + UPDATE.toString());
    }

}
