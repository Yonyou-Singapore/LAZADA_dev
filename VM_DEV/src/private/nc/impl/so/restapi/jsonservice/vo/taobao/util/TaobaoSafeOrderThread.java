package nc.impl.so.restapi.jsonservice.vo.taobao.util;

import java.util.Date;
import java.util.List;

public class TaobaoSafeOrderThread extends Thread {
    private String userAccount;
    private String userIp;
    private String ati;
    private String url;
    private List<String> tradeIds;
    private String operation;
    private Date date;
    private String appId;
    private String appName;

    public TaobaoSafeOrderThread(String userAccount, String userIp, String ati, String url, List<String> tradeIds, String operation, Date date,String appId,String appName) {
        super();
        this.userAccount = userAccount;
        this.userIp = userIp;
        this.ati = ati;
        this.url = url;
        this.tradeIds = tradeIds;
        this.operation = operation;
        this.date = date;
        this.appId = appId;
        this.appName = appName;
    }

    @Override
    public void run() {
        TaobaoSafeUtils.order(userAccount, userIp, ati, url, tradeIds, operation, date,appId,appName);
    }
}