package nc.impl.so.restapi.jsonservice;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.jdbc.framework.generator.IdGenerator;
import nc.pubitf.so.restapi.jsonservice.IJsonServiceForRestful;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.restapi.restlog.RestLogVO;


public class JsonServiceForRestfulServiceImpl implements IJsonServiceForRestful {

	@Override
	public void insertJsonInfoAndOther(RestLogVO[] logvos) {
		if(logvos == null || logvos.length < 1) {
			return;
		}
		try {
			new BaseDAO().insertVOArray(logvos);
		} catch (DAOException e) {
			Logger.error(e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
	}
}
