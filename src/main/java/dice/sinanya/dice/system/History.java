package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityHistory;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.History.changeHistory;
import static dice.sinanya.tools.Sender.sender;

/**
 * 骰点历史信息
 *
 * @author SitaNya
 */
public class History {

    private EntityTypeMessages entityTypeMessages;

    public History(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void hiy() {
        StringBuilder stringBuilder = new StringBuilder();
        EntityHistory entityHistory = changeHistory(entityTypeMessages.getFromQq());

        stringBuilder.append("您使用奈梅斯以来，共计产生以下历史数据:\n")
                .append("骰点:\t")
                .append(entityHistory.getTimes())
                .append("次")
                .append("\n")

                .append("大成功:\t")
                .append(entityHistory.getCriticalSuccess())
                .append("次")
                .append("\n")

                .append("大失败:\t")
                .append(entityHistory.getFumble())
                .append("次")
                .append("\n")

                .append("极难成功:\t")
                .append(entityHistory.getExtremeSuccess())
                .append("次")
                .append("\n")

                .append("困难成功:\t")
                .append(entityHistory.getHardSuccess())
                .append("次")
                .append("\n")

                .append("成功:\t")
                .append(entityHistory.getSuccess())
                .append("次")
                .append("\n")

                .append("失败:\t")
                .append(entityHistory.getFailure())
                .append("次")
                .append("\n")

                .append("平均值:\t")
                .append(entityHistory.getMean())
                .append("\n")

                .append("总成功数:\t")
                .append(entityHistory.getSuccess() + entityHistory.getCriticalSuccess() + entityHistory.getExtremeSuccess() + entityHistory.getHardSuccess())
                .append("次")
                .append("\n")

                .append("总失败数:\t")
                .append(entityHistory.getFailure() + entityHistory.getFumble())
                .append("次")
                .append("\n")

                .append("成功率:\t")
                .append((entityHistory.getSuccess() + entityHistory.getCriticalSuccess() + entityHistory.getExtremeSuccess() + entityHistory.getHardSuccess()) * 1.0 / entityHistory.getTimes() * 100)
                .append("%");
        sender(entityTypeMessages, stringBuilder.toString());
    }
}
