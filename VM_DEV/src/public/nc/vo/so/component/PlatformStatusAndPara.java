package nc.vo.so.component;

/**
 * ����ƽ̨״̬������SOʱ��һЩ����
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
	
	//��è
	//�ȴ���Ҹ���
	public static final String TMALL_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	//�ȴ����ҷ���
	public static final String TMALL_WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
	//���Ҳ��ַ���
	public static final String TMALL_SELLER_CONSIGNED_PART = "SELLER_CONSIGNED_PART";
	//�ȴ����ȷ���ջ�
	public static final String TMALL_WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
	//�����ǩ�գ���������ר�ã�
	public static final String TMALL_TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";
	//���׳ɹ�
	public static final String TMALL_TRADE_FINISHED = "TRADE_FINISHED";
	//���׹ر�
	public static final String TMALL_TRADE_CLOSED = "TRADE_CLOSED";
	//���ױ��Ա��ر�
	public static final String TMALL_TRADE_CLOSED_BY_TAOBAO = "TRADE_CLOSED_BY_TAOBAO";
	//û�д����ⲿ���ף�֧�������ף�
	public static final String TMALL_TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";
	//��0Ԫ����Լ��
	public static final String TMALL_WAIT_PRE_AUTH_CONFIRM = "WAIT_PRE_AUTH_CONFIRM";
	//�⿨֧������ȷ����
	public static final String TMALL_PAY_PENDING = "PAY_PENDING";
	//�������δ����Ľ��ף�������WAIT_BUYER_PAY��TRADE_NO_CREATE_PAY��
	public static final String TMALL_ALL_WAIT_PAY = "ALL_WAIT_PAY";
	//���йرյĽ��ף�������TRADE_CLOSED��TRADE_CLOSED_BY_TAOBAO��
	public static final String TMALL_ALL_CLOSED = "ALL_CLOSED";
	//��״̬�������Ѹ���Ǵ��ڽ�ֹ����״̬��
	public static final String TMALL_PAID_FORBID_CONSIGN = "PAID_FORBID_CONSIGN";
	
	//SG
	public static final String SG_PKORG = "0001VV10000000001LZV";
	//SG ����
	public static final String SG_DEPTID = "1001G610000000000EQ4";
	//SG �ͻ�
	public static final String SG_CUSTOMERID = "1001G61000000000PT0E";
	//SG ����ƽ̨
	public static final String SG_PLATFORMSTR = "004";
	//CN
	public static final String CN_PKORG = "0001VV10000000001LYX";
	//CN ����
	public static final String CN_DEPTID = "1001G61000000000S5MR";
	//CN �ͻ�
	public static final String CN_CUSTOMERID = "1001G61000000000PCQP";
	//CN ����ƽ̨
	public static final String CN_PLATFORMSTR = "LA-";
	
	
	//��Ҫ����SO��״̬
	public static final String[] GENERATE_ORDERSTATUS = new String[] {LAZADA_READYTOSHIP, LAZADA_SHIPPED, LAZADA_DELIVERED,
		TMALL_WAIT_SELLER_SEND_GOODS, TMALL_SELLER_CONSIGNED_PART, TMALL_WAIT_BUYER_CONFIRM_GOODS, TMALL_TRADE_BUYER_SIGNED,
		TMALL_TRADE_FINISHED, TMALL_WAIT_PRE_AUTH_CONFIRM};
}
