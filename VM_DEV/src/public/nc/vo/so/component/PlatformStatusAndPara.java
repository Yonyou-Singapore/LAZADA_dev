package nc.vo.so.component;

/**
 * 电商平台状态及生成SO时的一些常量
 * @author weiningc
 *
 */
public class PlatformStatusAndPara {
	
	//Lazada
	public static final String LAZADA_UNPAID = "unpaid";
	public static final String LAZADA_PENDING = "pending";
	public static final String LAZADA_CANCELED = "canceled";
	public static final String LAZADA_READYTOSHIP = "ready_to_ship";
	public static final String LAZADA_DELIVERED = "delivered";
	public static final String LAZADA_RETURNED = "returned";
	public static final String LAZADA_SHIPPED = "shipped";
	public static final String LAZADA_FAILED = "failed";
	
	//天猫
	//等待买家付款
	public static final String TMALL_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	//等待卖家发货
	public static final String TMALL_WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
	//卖家部分发货
	public static final String TMALL_SELLER_CONSIGNED_PART = "SELLER_CONSIGNED_PART";
	//等待买家确认收货
	public static final String TMALL_WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
	//买家已签收（货到付款专用）
	public static final String TMALL_TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";
	//交易成功
	public static final String TMALL_TRADE_FINISHED = "TRADE_FINISHED";
	//交易关闭
	public static final String TMALL_TRADE_CLOSED = "TRADE_CLOSED";
	//交易被淘宝关闭
	public static final String TMALL_TRADE_CLOSED_BY_TAOBAO = "TRADE_CLOSED_BY_TAOBAO";
	//没有创建外部交易（支付宝交易）
	public static final String TMALL_TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";
	//余额宝0元购合约中
	public static final String TMALL_WAIT_PRE_AUTH_CONFIRM = "WAIT_PRE_AUTH_CONFIRM";
	//外卡支付付款确认中
	public static final String TMALL_PAY_PENDING = "PAY_PENDING";
	//所有买家未付款的交易（包含：WAIT_BUYER_PAY、TRADE_NO_CREATE_PAY）
	public static final String TMALL_ALL_WAIT_PAY = "ALL_WAIT_PAY";
	//所有关闭的交易（包含：TRADE_CLOSED、TRADE_CLOSED_BY_TAOBAO）
	public static final String TMALL_ALL_CLOSED = "ALL_CLOSED";
	//该状态代表订单已付款但是处于禁止发货状态。
	public static final String TMALL_PAID_FORBID_CONSIGN = "PAID_FORBID_CONSIGN";
	
	//SG
	public static final String SG_PKORG = "0001VV10000000001LZV";
	//SG 部门
	public static final String SG_DEPTID = "1001G610000000000EQ4";
	//SG 客户
	public static final String SG_CUSTOMERID = "1001G61000000000PT0E";
	//SG 交易平台
	public static final String SG_PLATFORMSTR = "004";
	//CN
	public static final String CN_PKORG = "0001VV10000000001LYX";
	//CN 部门
	public static final String CN_DEPTID = "1001G61000000000S5MR";
	//CN 客户
	public static final String CN_CUSTOMERID = "1001G61000000000PCQP";
	//CN 交易平台
	public static final String CN_PLATFORMSTR = "LA-";
	
	
	//需要生成SO的状态
	public static final String[] GENERATE_ORDERSTATUS = new String[] {LAZADA_READYTOSHIP, LAZADA_SHIPPED, LAZADA_DELIVERED,
		TMALL_WAIT_SELLER_SEND_GOODS, TMALL_SELLER_CONSIGNED_PART, TMALL_WAIT_BUYER_CONFIRM_GOODS, TMALL_TRADE_BUYER_SIGNED,
		TMALL_TRADE_FINISHED, TMALL_WAIT_PRE_AUTH_CONFIRM};
}
