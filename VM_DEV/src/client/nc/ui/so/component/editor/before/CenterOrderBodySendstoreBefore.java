package nc.ui.so.component.editor.before;

import org.apache.commons.lang.StringUtils;
import org.restlet.Context;

import nc.ui.ct.editor.listener.IBodyBeforeEditEventListener;
import nc.ui.ct.util.CardEditorHelper;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterRefUtils;

public class CenterOrderBodySendstoreBefore implements IBodyBeforeEditEventListener {

	@Override
	public void beforeEdit(CardBodyBeforeEditEvent event) {
		CardEditorHelper utilHelper =
		        CardEditorHelper.getInstance(event.getBillCardPanel());
	    FilterRefUtils util =
	        new FilterRefUtils(utilHelper.getBodyItem(utilHelper
	            .getSelectTableCode(), "csendstordocid"));
	    util.filterItemRefByGroup(event.getContext().getPk_group());
	    //发货组织
	    String csendstockorgid = (String) event.getBillCardPanel().getBodyValueAt(event.getRow(), "csendstockorgid");
	    if(!StringUtils.isBlank(csendstockorgid)) {
	    	util.filterItemRefByOrg(csendstockorgid);
	    }
		
	}

}
