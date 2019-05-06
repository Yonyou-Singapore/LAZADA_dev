package nc.pubitf.so.restapi.jsonservice;

import nc.vo.so.restapi.restlog.RestLogVO;


/**
 * json data DDL
 * @author weiningc
 *
 */
public interface IJsonServiceForRestful {
	
	public void insertJsonInfoAndOther(RestLogVO[] logvos);
}
