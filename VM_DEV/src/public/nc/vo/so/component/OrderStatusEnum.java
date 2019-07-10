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
		
		//��è
		//�ȴ���Ҹ���
		public static final OrderStatusEnum WAIT_BUYER_PAY = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_WAIT_BUYER_PAY);
		//�ȴ����ҷ���
		public static final OrderStatusEnum WAIT_SELLER_SEND_GOODS = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_WAIT_SELLER_SEND_GOODS);
		//���Ҳ��ַ���
		public static final OrderStatusEnum SELLER_CONSIGNED_PART = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_SELLER_CONSIGNED_PART);
		//�ȴ����ȷ���ջ�
		public static final OrderStatusEnum WAIT_BUYER_CONFIRM_GOODS = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_WAIT_BUYER_CONFIRM_GOODS);
		//�����ǩ�գ���������ר�ã�
		public static final OrderStatusEnum TRADE_BUYER_SIGNED = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_BUYER_SIGNED);
		//���׳ɹ�
		public static final OrderStatusEnum TRADE_FINISHED = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_FINISHED);
		//���׹ر�
		public static final OrderStatusEnum TRADE_CLOSED = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_CLOSED);
		//���ױ��Ա��ر�
		public static final OrderStatusEnum TRADE_CLOSED_BY_TAOBAO = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_CLOSED_BY_TAOBAO);
		//û�д����ⲿ���ף�֧�������ף�
		public static final OrderStatusEnum TRADE_NO_CREATE_PAY = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_TRADE_NO_CREATE_PAY);
		//��0Ԫ����Լ��
		public static final OrderStatusEnum WAIT_PRE_AUTH_CONFIRM = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_WAIT_PRE_AUTH_CONFIRM);
		//�⿨֧������ȷ����
		public static final OrderStatusEnum PAY_PENDING = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_PAY_PENDING);
		//�������δ����Ľ��ף�������WAIT_BUYER_PAY��TRADE_NO_CREATE_PAY��
		public static final OrderStatusEnum ALL_WAIT_PAY = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_ALL_WAIT_PAY);
		//���йرյĽ��ף�������TRADE_CLOSED��TRADE_CLOSED_BY_TAOBAO��
		public static final OrderStatusEnum ALL_CLOSED = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_ALL_CLOSED);
		//��״̬�������Ѹ���Ǵ��ڽ�ֹ����״̬��
		public static final OrderStatusEnum PAID_FORBID_CONSIGN = MDEnum.valueOf(OrderStatusEnum.class, PlatformStatusAndPara.TMALL_PAID_FORBID_CONSIGN);
}
