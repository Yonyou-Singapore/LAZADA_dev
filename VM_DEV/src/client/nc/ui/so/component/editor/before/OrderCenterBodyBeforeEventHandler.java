package nc.ui.so.component.editor.before;

import java.util.Map;

import nc.ui.ct.editor.handler.AbstractBodyBeforeEditEventHandler;
import nc.ui.ct.editor.listener.IBodyBeforeEditEventListener;

@SuppressWarnings("restriction")
public class OrderCenterBodyBeforeEventHandler extends AbstractBodyBeforeEditEventHandler {

	@Override
	public void registerEventListener(
			Map<String, IBodyBeforeEditEventListener> listenerMap) {
		// TODO Auto-generated method stub
		// �����ֿ�༭ǰ
	    listenerMap.put("csendstordocid", new CenterOrderBodySendstoreBefore());
	}

}
