package nc.vo.so.component;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class OrderStatusEnum extends MDEnum {

	public OrderStatusEnum(IEnumValue enumValue) {
		super(enumValue);
	}
	//Lazada
		public static final OrderStatusEnum unpaid = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.LAZADA_UNPAID);
		public static final OrderStatusEnum pending = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.LAZADA_PENDING);
		public static final OrderStatusEnum canceled = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.LAZADA_CANCELED);
		public static final OrderStatusEnum ready_to_ship = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.LAZADA_READYTOSHIP);
		public static final OrderStatusEnum delivered = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.LAZADA_DELIVERED);
		public static final OrderStatusEnum returned = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.LAZADA_RETURNED);
		public static final OrderStatusEnum shipped = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.LAZADA_RETURNED);
		public static final OrderStatusEnum failed = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.LAZADA_FAILED);
		
		//天猫
		//等待买家付款
		public static final OrderStatusEnum WAIT_BUYER_PAY = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_WAIT_BUYER_PAY);
		//等待卖家发货
		public static final OrderStatusEnum WAIT_SELLER_SEND_GOODS = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_WAIT_SELLER_SEND_GOODS);
		//卖家部分发货
		public static final OrderStatusEnum SELLER_CONSIGNED_PART = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_SELLER_CONSIGNED_PART);
		//等待买家确认收货
		public static final OrderStatusEnum WAIT_BUYER_CONFIRM_GOODS = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_WAIT_BUYER_CONFIRM_GOODS);
		//买家已签收（货到付款专用）
		public static final OrderStatusEnum TRADE_BUYER_SIGNED = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_BUYER_SIGNED);
		//交易成功
		public static final OrderStatusEnum TRADE_FINISHED = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_FINISHED);
		//交易关闭
		public static final OrderStatusEnum TRADE_CLOSED = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_CLOSED);
		//交易被淘宝关闭
		public static final OrderStatusEnum TRADE_CLOSED_BY_TAOBAO = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_CLOSED_BY_TAOBAO);
		//没有创建外部交易（支付宝交易）
		public static final OrderStatusEnum TRADE_NO_CREATE_PAY = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_NO_CREATE_PAY);
		//余额宝0元购合约中
		public static final OrderStatusEnum WAIT_PRE_AUTH_CONFIRM = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_WAIT_PRE_AUTH_CONFIRM);
		//外卡支付付款确认中
		public static final OrderStatusEnum PAY_PENDING = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_PAY_PENDING);
		//所有买家未付款的交易（包含：WAIT_BUYER_PAY、TRADE_NO_CREATE_PAY）
		public static final OrderStatusEnum ALL_WAIT_PAY = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_ALL_WAIT_PAY);
		//所有关闭的交易（包含：TRADE_CLOSED、TRADE_CLOSED_BY_TAOBAO）
		public static final OrderStatusEnum ALL_CLOSED = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_ALL_CLOSED);
		//该状态代表订单已付款但是处于禁止发货状态。
		public static final OrderStatusEnum PAID_FORBID_CONSIGN = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_PAID_FORBID_CONSIGN);
}
