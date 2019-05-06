package nc.vo.scmpub.fill.pricemny.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.ic.pub.env.ICBSContext;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialConvertVO;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.ic.material.define.InvBasVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.pub.keyvalue.IKeyValue;
import nc.vo.scmf.pub.keyvalue.VOKeyValue;
import nc.vo.scmpub.fill.pricemny.ICalculator;
import nc.vo.scmpub.fill.pricemny.IKeyRela;

/**
 * @description
 * 插入API换算率统一计算规则
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-12-10 下午12:59:43
 * @author 刘景
 */
public class SCMChangeRateRule<E extends IBill> {

  /**
   * 各单据字段名称接口
   */
  private IKeyRela keyRela;

  /**
   * 各单据VO
   */
  private E[] vos;

  /**
   * 构造方法
   * @param e
   * @param keyRela
   */
  public SCMChangeRateRule(
      E[] e, IKeyRela keyRela) {
    this.keyRela = keyRela;
    this.vos = e;
  }

  /**
   * 设置换算率和报价换算率
   * 
   * @param calculator 单价金额算法触发接口
   */
  public void calChangeRate(ICalculator calculator) {
    for (E vo : this.vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      String[] itemKey = {
        MaterialConvertVO.FIXEDFLAG, MaterialConvertVO.MEASRATE
      };
      Map<String, MaterialConvertVO> marcvtmap =
          this.getMaterialConvertVO(keyValue, itemKey);
      Map<String, UFBoolean> fixedratemap =
          isAstAndQtFixedRate(keyValue, marcvtmap);
      for (int i = 0; i < keyValue.getBodyCount(); i++) {

        // 处理固定换算率场景
        setChangeRate(keyValue, i, marcvtmap, fixedratemap);

        // 处理浮动换算率场景
        setFixChangeRate(keyValue, i, marcvtmap, fixedratemap);
      }
    }
  }

  private void setChangeRate(IKeyValue keyValue, int row,
      Map<String, MaterialConvertVO> ratemap,
      Map<String, UFBoolean> fixedratemap) {
    String cmaterialvid =
        keyValue.getBodyStringValue(row, keyRela.getCmaterialvidKey());
    String castunitid =
        keyValue.getBodyStringValue(row, keyRela.getCastunitidKey());
    String cqtunitid =
        keyValue.getBodyStringValue(row, keyRela.getCqtunitidKey());

    String materqunit = cmaterialvid + cqtunitid;
    String materastunit = cmaterialvid + castunitid;

    // 报价换算率设置
    if (!fixedratemap.get(materqunit).booleanValue()) {
      return;
    }
    String measrate = ratemap.get(materqunit).getMeasrate();
    if (measrate == null) {
      keyValue.setBodyValue(row, keyRela.getVqtunitrateKey(),
          HslParseUtil.HSL_ONE);
    }
    else {
      keyValue.setBodyValue(row, keyRela.getVqtunitrateKey(), measrate);
    }

    // 换算率设置
    if (!fixedratemap.get(materastunit).booleanValue()) {
      return;
    }
    measrate = ratemap.get(materastunit).getMeasrate();
    if (measrate == null) {
      keyValue.setBodyValue(row, keyRela.getVchangerateKey(),
          HslParseUtil.HSL_ONE);
    }
    else {
      keyValue.setBodyValue(row, keyRela.getVchangerateKey(), measrate);
    }
  }

  private void setFixChangeRate(IKeyValue keyValue, int row,
      Map<String, MaterialConvertVO> ratemap,
      Map<String, UFBoolean> fixedratemap) {
    String cmaterialvid =
        keyValue.getBodyStringValue(row, keyRela.getCmaterialvidKey());
    String castunitid =
        keyValue.getBodyStringValue(row, keyRela.getCastunitidKey());
    String cqtunitid =
        keyValue.getBodyStringValue(row, keyRela.getCqtunitidKey());
    String cunitid = keyValue.getBodyStringValue(row, keyRela.getCunitidKey());

    UFDouble nnum = keyValue.getBodyUFDoubleValue(row, keyRela.getNnumKey());
    UFDouble nastnum =
        keyValue.getBodyUFDoubleValue(row, keyRela.getNastnumKey());
    UFDouble nqtunitnum =
        keyValue.getBodyUFDoubleValue(row, keyRela.getNqtunitnumKey());

    String materqunit = cmaterialvid + cqtunitid;
    String materastunit = cmaterialvid + castunitid;

    // 报价换算率
    if (fixedratemap.get(materqunit).booleanValue()) {
      return;
    }
    if (!MathTool.isZero(nnum)) {
      keyValue.setBodyValue(row, keyRela.getVqtunitrateKey(),
          HslParseUtil.buildHslString(nnum, nqtunitnum));
    }
    else {
    	if(ratemap.get(materqunit) == null) {
    		return;
//    		InvBasVO invBasVO = new ICBSContext().getInvInfo().getInvBasVO(cmaterialvid);
//    		ExceptionUtils.wrappBusinessException("该SKU没有设置辅计量单位 [" + invBasVO.getCode() + "]");
    	}
      keyValue.setBodyValue(row, keyRela.getVqtunitrateKey(),
          ratemap.get(materqunit).getMeasrate());
    }

    // 换算率
    if (fixedratemap.get(materastunit).booleanValue()) {
      return;
    }
    String vqtunitrate =
        keyValue.getBodyStringValue(row, keyRela.getVqtunitrateKey());
    if (PubAppTool.isEqual(cqtunitid, castunitid)) {
      keyValue.setBodyValue(row, keyRela.getVchangerateKey(), vqtunitrate);
    }
    else if (PubAppTool.isEqual(cqtunitid, cunitid)) {
      keyValue.setBodyValue(row, keyRela.getVchangerateKey(),
          HslParseUtil.HSL_ONE);
    }
    else if (!MathTool.isZero(nnum) && !MathTool.isZero(nastnum)) {
      keyValue.setBodyValue(row, keyRela.getVchangerateKey(),
          HslParseUtil.buildHslString(nnum, nastnum));
    }
    else {
      keyValue.setBodyValue(row, keyRela.getVchangerateKey(),
          ratemap.get(materastunit).getMeasrate());
    }

  }

  /**
   * 方法功能描述：批量查询报价单位和主单位、业务单位和主单位之间是否固定换算率。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @time 2015-5-5 下午05:53:51
   */
  private Map<String, UFBoolean> isAstAndQtFixedRate(IKeyValue keyValue,
      Map<String, MaterialConvertVO> fixedMap) {

    Map<String, UFBoolean> unit_fixedmap = new HashMap<String, UFBoolean>();
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      String cmaterialvid =
          keyValue.getBodyStringValue(i, keyRela.getCmaterialvidKey());
      String cunitid = keyValue.getBodyStringValue(i, keyRela.getCunitidKey());
      String castunitid =
          keyValue.getBodyStringValue(i, keyRela.getCastunitidKey());
      String cqtunitid =
          keyValue.getBodyStringValue(i, keyRela.getCqtunitidKey());
      String materastunit = cmaterialvid + castunitid;
      String materqunit = cmaterialvid + cqtunitid;
      // 物料+附单位是否是固定换算率nc.itf.scmpub.reference.uap.bd.material.MaterialPubService
      if (StringUtil.isEmpty(cunitid) || StringUtil.isEmpty(castunitid)) {
        unit_fixedmap.put(materastunit, UFBoolean.FALSE);
      }
      if (PubAppTool.isEqual(cunitid, castunitid)) {
        unit_fixedmap.put(materastunit, UFBoolean.TRUE);
      }
      else {
        unit_fixedmap.put(materastunit,
            fixedMap.get(materastunit) == null ? UFBoolean.FALSE : fixedMap
                .get(materastunit).getFixedflag());
      }

      // 物料+报价单位是否是固定换算率
      if (StringUtil.isEmpty(cunitid) || StringUtil.isEmpty(cqtunitid)) {
        unit_fixedmap.put(materastunit, UFBoolean.FALSE);
      }
      if (PubAppTool.isEqual(cunitid, cqtunitid)) {
        unit_fixedmap.put(materqunit, UFBoolean.TRUE);
      }
      else {
        unit_fixedmap.put(
            materqunit,
            fixedMap.get(materqunit) == null ? UFBoolean.FALSE : fixedMap.get(
                materqunit).getFixedflag());
      }
    }
    return unit_fixedmap;
  }

  /**
   * 批量获取指定字段的MaterialConvertVO
   * 
   * @param rows
   * @param itemKey
   * @return
   */
  private Map<String, MaterialConvertVO> getMaterialConvertVO(
      IKeyValue keyValue, String[] itemKey) {
    Set<String> setmaterid = new HashSet<String>();
    Set<String> setunitid = new HashSet<String>();
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      String cmaterialvid =
          keyValue.getBodyStringValue(i, keyRela.getCmaterialvidKey());
      String castunitid =
          keyValue.getBodyStringValue(i, keyRela.getCastunitidKey());
      String cqtunitid =
          keyValue.getBodyStringValue(i, keyRela.getCqtunitidKey());
      String cunitid = keyValue.getBodyStringValue(i, keyRela.getCunitidKey());
      // 单位和报价单位有为空的情况，所以要把主单位也添加进来
      setmaterid.add(cmaterialvid);
      setunitid.add(castunitid);
      setunitid.add(cqtunitid);
      setunitid.add(cunitid);
    }
    if (setunitid.size() == 0) {
      return new HashMap<String, MaterialConvertVO>();
    }
    Map<String, MaterialConvertVO> measdocMap =
        MaterialPubService.queryMeasVOByMaterialAndMeasdoc(
            setmaterid.toArray(new String[0]),
            setunitid.toArray(new String[0]), itemKey);
    return measdocMap;
  }

}
