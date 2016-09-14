package com.jobplus.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * 2016年8月24日上午10:44:17
 *
 */
@Service("typeConfigUtil")
public class TypeConfigUtil {

	/**
	 * 获取首页分类信息
	 * @return
	 */
	public List<Map<String,String>> getHomeTypeList(){
		Map<String, String> paMap  = new HashMap<String, String>();
		Map<String, String> paMap2 = new HashMap<String, String>();
		Map<String, String> paMap3 = new HashMap<String, String>();
		Map<String, String> paMap4 = new HashMap<String, String>();
		Map<String, String> paMap5 = new HashMap<String, String>();
		Map<String, String> paMap6 = new HashMap<String, String>();
		Map<String, String> paMap7 = new HashMap<String, String>();
		Map<String, String> paMap8 = new HashMap<String, String>();
		Map<String, String> paMap9 = new HashMap<String, String>();
		
		
		paMap.put("rootName","创新/创业");
		paMap.put("rootID","100,200");
		paMap.put("firstViewName1","创新方法");
		paMap.put("firstViewID1","102");
		paMap.put("firstViewName2","技术创新");
		paMap.put("firstViewID2","104");
		paMap.put("firstViewName3","融资股权");
		paMap.put("firstViewID3","203");
		paMap.put("firstViewName4","成功之道");
		paMap.put("firstViewID4","206");	

		paMap.put("childID1","100");	
		paMap.put("childName1","创新");	
		paMap.put("childListData1","101:模式创新,102:创新方法,103:制度创新,104:技术创新,105:产品（服务）创新,106:组织创新,107:创新管理,108:其他");	
		paMap.put("childID2","200");	
		paMap.put("childName2","创业");	
		paMap.put("childListData2","201:准备阶段,202:起步阶段,203:成长阶段,204:融资股权,205:并购上市,206:成功之道,207:成功之母,208:其他");	
		
		
		
		
		
		paMap2.put("rootName", "IT/互联网");
		paMap2.put("rootID", "300,400");
		paMap2.put("firstViewName1","工业智能4.0");
		paMap2.put("firstViewID1","301");
		paMap2.put("firstViewName2","管理系统");
		paMap2.put("firstViewID2","302");
		paMap2.put("firstViewName3","互联网运营");
		paMap2.put("firstViewID3","402");
		paMap2.put("firstViewName4","平台开发");
		paMap2.put("firstViewID4","404");		

		paMap2.put("childID1","300");	
		paMap2.put("childName1","IT");	
		paMap2.put("childListData1","301:工业智能4.0,302:管理系统,303:软件开发,304:大数据分析,305:DBA,306:基础架构,307:网络硬件 ,308:IT 管理,309:服务支持,310:其他");	
		paMap2.put("childID2","400");	
		paMap2.put("childName2","互联网");	
		paMap2.put("childListData2","401:互联网Plus,402:互联网运营,403:市场销售,404:平台开发,405:产品设计,406:大数据分析,407:DBA,408:系统架构,409:网络安全,410:其他");	
		
		
		
		paMap3.put("rootName", "咨询/财务");
		paMap3.put("rootID", "500,600");
		paMap3.put("firstViewName1","企业管理");
		paMap3.put("firstViewID1","501");
		paMap3.put("firstViewName2","企业培训");
		paMap3.put("firstViewID2","502");
		paMap3.put("firstViewName3","财务咨询");
		paMap3.put("firstViewID3","601");
		paMap3.put("firstViewName4","财务会计");
		paMap3.put("firstViewID4","604");

		paMap3.put("childID1","300");	
		paMap3.put("childName1","咨询");	
		paMap3.put("childListData1","501:企业管理,502:企业培训,503:法律法规,504:企业认证,505:人事猎头,506:金融投资,507:心理健康,508:工商税务,509:商标专利,510:其他");	
		paMap3.put("childID2","400");	
		paMap3.put("childName2","财务");	
		paMap3.put("childListData2","601:财务咨询,602:内外审计,603:资产评估,604:财务会计,605:管理会计,606:成本会计,607:税务会计,608:金融精算,609:其他");	
		

		
		paMap4.put("rootID", "700,800");
		paMap4.put("rootName", "工程/技术");
		paMap4.put("firstViewName1","生物医药");
		paMap4.put("firstViewID1","701");
		paMap4.put("firstViewName2","建筑建材");
		paMap4.put("firstViewID2","705");
		paMap4.put("firstViewName3","新型材料");
		paMap4.put("firstViewID3","802");
		paMap4.put("firstViewName4","电子通信");
		paMap4.put("firstViewID4","804");

		paMap4.put("childID1","700");	
		paMap4.put("childName1","工程");	
		paMap4.put("childListData1","701:生物医药,702:新型材料,703:机械仪表,704:电子通信,705:建筑建材,706:环保能源,707:石油化工,708:轻工食品,709:冶金矿产,710:农林牧渔,711:其他");	
		paMap4.put("childID2","800");	
		paMap4.put("childName2","技术");	
		paMap4.put("childListData2","801:生物医药,802:新型材料,803:机械仪表,804:电子通信,805:建筑建材,806:环保能源,807:石油化工,808:轻工食品,809:冶金矿产,810:农林牧渔,811:其他");
		
		
		
		
		paMap5.put("rootID", "900,1000");
		paMap5.put("rootName", "市场/销售");
		paMap5.put("firstViewName1","产品战略");
		paMap5.put("firstViewID1","903");
		paMap5.put("firstViewName2","营销策略");
		paMap5.put("firstViewID2","905");
		paMap5.put("firstViewName3","技术服务");
		paMap5.put("firstViewID3","1002");
		paMap5.put("firstViewName4","渠道管理");
		paMap5.put("firstViewID4","1004");

		paMap5.put("childID1","900");	
		paMap5.put("childName1","市场");	
		paMap5.put("childListData1","901:市场调研,902:行业调研,903:产品战略,904:品牌管理,905:营销策略,906:市场策划,907:其他");	
		paMap5.put("childID2","1000");	
		paMap5.put("childName2","销售");	
		paMap5.put("childListData2","1001:销售管理,1002:技术服务,1003:客户关系管理,1004:渠道管理,1005:活动策划,1006:广告策划,1007:其他");	
		
		
		
		
		
		paMap6.put("rootID", "1100,1200");
		paMap6.put("rootName", "供应/制造");
		paMap6.put("firstViewName1","供应链金融");
		paMap6.put("firstViewID1","1101");
		paMap6.put("firstViewName2","采购管理");
		paMap6.put("firstViewID2","1105");
		paMap6.put("firstViewName3","机械仪表");
		paMap6.put("firstViewID3","1203");
		paMap6.put("firstViewName4","冶金矿产");
		paMap6.put("firstViewID4","1209");		

		paMap6.put("childID1","1100");	
		paMap6.put("childName1","供应");	
		paMap6.put("childListData1","1101:供应链金融,1102:物流管理,1103:库存管理,1104:物料管理,1105:采购管理,1106:供应商管理,1107:供应链管理,1108:进口出口,1109:其他");	
		paMap6.put("childID2","1200");	
		paMap6.put("childName2","制造");	
		paMap6.put("childListData2","1201:生物医药,1202:新型材料,1203:机械仪表,1204:电子通信,1205:建筑建材,1206:环保能源,1207:石油化工,1208:轻工食品,1209:冶金矿产,1210:农林牧渔,1211:其他");	
		
		
		
		paMap7.put("rootID", "1300,1400");
		paMap7.put("rootName", "创意/设计");
		paMap7.put("firstViewName1","艺术文化");
		paMap7.put("firstViewID1","1301");
		paMap7.put("firstViewName2","咨询策划");
		paMap7.put("firstViewID2","1304");
		paMap7.put("firstViewName3","工业设计");
		paMap7.put("firstViewID3","1401");
		paMap7.put("firstViewName4","媒体动漫");
		paMap7.put("firstViewID4","1404");

		paMap7.put("childID1","1300");	
		paMap7.put("childName1","创意");	
		paMap7.put("childListData1","1301:艺术文化,1302:研发设计,1303:动漫媒体,1304:咨询策划,1305:时尚消费,1306:其他");	
		paMap7.put("childID2","1400");	
		paMap7.put("childName2","设计");	
		paMap7.put("childListData2","1401:工业设计,1402:机械设计,1403:服装包装,1404:媒体动漫,1405:VI LOGO,1406:广告 宣传,1407:画册书籍,1408:建筑家居,1409:其他");	
		
		
		
		paMap8.put("rootID", "1500,1600");
		paMap8.put("rootName", "媒体/影视");
		paMap8.put("firstViewName1","新媒体");
		paMap8.put("firstViewID1","1501");
		paMap8.put("firstViewName2","户外媒体");
		paMap8.put("firstViewID2","1507");
		paMap8.put("firstViewName3","主持模特");
		paMap8.put("firstViewID3","1602");
		paMap8.put("firstViewName4","化妆造型");
		paMap8.put("firstViewID4","1606");
		
		paMap8.put("childID1","1500");	
		paMap8.put("childName1","媒体");	
		paMap8.put("childListData1","1501:新媒体,1502:网络媒体,1503:报纸媒体,1504:杂志媒体,1505:广播媒体,1506:电视媒体,1507:户外媒体,1508:其他");	
		paMap8.put("childID2","1600");	
		paMap8.put("childName2","影视");	
		paMap8.put("childListData2","1601:摄像摄影,1602:主持模特,1603:策划制作 ,1604:后期制作,1605:编导演员,1606:化妆造型,1607:艺术设计,1608:灯光音效,1609:其它");	
		
		
		
		paMap9.put("rootID", "1700,1800");
		paMap9.put("rootName", "翻译/出版");		
		paMap9.put("firstViewName1","英语");
		paMap9.put("firstViewID1","1701");
		paMap9.put("firstViewName2","德语");
		paMap9.put("firstViewID2","1707");
		paMap9.put("firstViewName3","记者");
		paMap9.put("firstViewID3","1802");
		paMap9.put("firstViewName4","采编校对");
		paMap9.put("firstViewID4","1808");				
		
		paMap9.put("childID1","1700");	
		paMap9.put("childName1","翻译");	
		paMap9.put("childListData1","1701:英语,1702:法语,1703:西班牙语,1704:葡萄牙语,1705:俄语,1706:阿拉伯语,1707:德语,1708:日语,1709:意大利语,1710:韩语,1711:其他");		
		paMap9.put("childID2","1800");	
		paMap9.put("childName2","出版");	
		paMap9.put("childListData2","1801:作家,1802:记者,1803:文案策划,1804:编辑,1805:撰稿,1806:排版设计,1807:出版发行,1808:采编校对,1809:其他");	
				
	
		List<Map<String,String>> homeTypeList = new ArrayList<Map<String,String>>();
		homeTypeList.add(paMap);
		homeTypeList.add(paMap2);
		homeTypeList.add(paMap3);
		homeTypeList.add(paMap4);
		homeTypeList.add(paMap5);
		homeTypeList.add(paMap6);
		homeTypeList.add(paMap7);
		homeTypeList.add(paMap8);
		homeTypeList.add(paMap9);
		
		return homeTypeList;
	}
}
