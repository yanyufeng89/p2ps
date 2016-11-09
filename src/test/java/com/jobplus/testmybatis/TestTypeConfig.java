package com.jobplus.testmybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTypeConfig {

	/**
	 * 
	 * 
	 * 
	 * select bb.typename, bb.parentid,aa.typename from 
tbl_typeConfig aa , (
	SELECT parentid,  group_concat(typeid,':',typename)  
	as typename FROM tbl_typeConfig group by parentid)
bb  where bb.parentid = aa.typeid


	 * @param args
	 */
	public static void main(String[] args) {
		

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
		paMap.put("firstViewName1","模式创新");
		paMap.put("firstViewID1","101");
		paMap.put("firstViewName2","创新方法");
		paMap.put("firstViewID2","102");
		paMap.put("firstViewName3","准备阶段");
		paMap.put("firstViewID3","201");
		paMap.put("firstViewName4","融资股权");
		paMap.put("firstViewID4","202");	

		paMap.put("childID1","100");	
		paMap.put("childName1","创新");	
		paMap.put("childListData1","101:模式创新,102:创新方法,103:制度创新,104:技术创新,105:产品（服务）创新,106:组织创新,107:创新管理,108:其他");	
		paMap.put("childID2","200");	
		paMap.put("childName2","创业");	
		paMap.put("childListData2","201:准备阶段,202:融资股权,203:成长阶段,204:起步阶段,205:并购上市,206:成功之道,207:成功之母,208:其他");	
		
		
		
		
		paMap2.put("rootName", "咨询/财务");
		paMap2.put("rootID", "300,400");
		paMap2.put("firstViewName1","企业管理");
		paMap2.put("firstViewID1","301");
		paMap2.put("firstViewName2","企业培训");
		paMap2.put("firstViewID2","302");
		paMap2.put("firstViewName3","财务咨询");
		paMap2.put("firstViewID3","401");
		paMap2.put("firstViewName4","内外审计");
		paMap2.put("firstViewID4","402");

		paMap2.put("childID1","300");	
		paMap2.put("childName1","咨询");	
		paMap2.put("childListData1","301:企业管理,302:企业培训,303:法律法规,304:企业认证,305:人事猎头,306:金融投资,307:心理健康,308:工商注册,309:商标专利,310:其他");	
		paMap2.put("childID2","400");	
		paMap2.put("childName2","财务");	
		paMap2.put("childListData2","401:财务咨询,402:内外审计,403:资产评估,404:财务会计,405:管理会计,406:成本会计,407:税务会计,408:其他");	
		
		
		
		paMap3.put("rootName", "IT/互联网");
		paMap3.put("rootID", "500,600");
		paMap3.put("firstViewName1","工业智能");
		paMap3.put("firstViewID1","501");
		paMap3.put("firstViewName2","管理系统");
		paMap3.put("firstViewID2","502");
		paMap3.put("firstViewName3","互联网运营");
		paMap3.put("firstViewID3","601");
		paMap3.put("firstViewName4","互联网Plus");
		paMap3.put("firstViewID4","602");		

		paMap.put("childID1","500");	
		paMap.put("childName1","IT");	
		paMap.put("childListData1","501:工业智能4.0,502:管理系统,503:软件开发,504:大数据,505:DBA,506:基础架构,507:网络硬件 ,508:IT 咨询,509:服务支持,510:其他");	
		paMap.put("childID2","600");	
		paMap.put("childName2","互联网");	
		paMap.put("childListData2","601:互联网Plus,602:互联网运营,603:市场销售,604:平台开发,605:产品设计,606:大数据,607:DBA,608:系统架构,609:网络安全,610:其他");	
		
		
		
		

		paMap4.put("rootID", "700,800");
		paMap4.put("rootName", "工程/技术");
		paMap4.put("firstViewName1","生物制药");
		paMap4.put("firstViewID1","701");
		paMap4.put("firstViewName2","新材料|能源");
		paMap4.put("firstViewID2","702");
		paMap4.put("firstViewName3","生物制药");
		paMap4.put("firstViewID3","801");
		paMap4.put("firstViewName4","新材料能源");
		paMap4.put("firstViewID4","802");

		paMap.put("childID1","700");	
		paMap.put("childName1","工程");	
		paMap.put("childListData1","701:生物制药,702:新材料|能源,703:机械仪表,704:电子通信,705:建筑建材,706:环境节能,707:石油化工,708:轻工食品,709:服装纺织,710:其他");	
		paMap.put("childID2","800");	
		paMap.put("childName2","技术");	
		paMap.put("childListData2","801:生物制药,802:新材料能源,803:机械仪表,804:电子通信,805:建筑建材,806:环境节能,807:石油化工,808:轻工食品,809:服装纺织,810:其他");
		
		
		
		
		paMap5.put("rootID", "900,1000");
		paMap5.put("rootName", "市场/销售");
		paMap5.put("firstViewName1","市场调研");
		paMap5.put("firstViewID1","901");
		paMap5.put("firstViewName2","行业调研");
		paMap5.put("firstViewID2","902");
		paMap5.put("firstViewName3","销售管理");
		paMap5.put("firstViewID3","1001");
		paMap5.put("firstViewName4","服务管理");
		paMap5.put("firstViewID4","1002");

		paMap5.put("childID1","900");	
		paMap5.put("childName1","市场");	
		paMap5.put("childListData1","901:市场调研,902:行业调研,903:产品战略,904:品牌管理,905:营销策略,906:市场策划,907:其他");	
		paMap5.put("childID2","1000");	
		paMap5.put("childName2","销售");	
		paMap5.put("childListData2","1001:销售管理,1002:服务管理,1003:客户关系管理,1004:渠道管理,1005:活动策划,1006:广告策划,1007:其他");	
		
		
		
		
		
		paMap6.put("rootID", "1100,1200");
		paMap6.put("rootName", "供应/制造");
		paMap6.put("firstViewName1","供应链金融");
		paMap6.put("firstViewID1","1101");
		paMap6.put("firstViewName2","物流管理");
		paMap6.put("firstViewID2","1102");
		paMap6.put("firstViewName3","生物制药");
		paMap6.put("firstViewID3","1201");
		paMap6.put("firstViewName4","新材料能源");
		paMap6.put("firstViewID4","1202");		

		paMap6.put("childID1","1100");	
		paMap6.put("childName1","供应");	
		paMap6.put("childListData1","1101:供应链金融,1102:物流管理,1103:库存管理,1104:物料管理,1105:采购管理,1106:供应商管理,1107:供应链管理,1108:进出口咨询,1109:其他");	
		paMap6.put("childID2","1200");	
		paMap6.put("childName2","制造");	
		paMap6.put("childListData2","1201:生物制药,1202:新材料能源,1203:机械仪表,1204:电子通信,1205:建筑建材,1206:环境节能,1207:石油化工,1208:轻工食品,1209:服装纺织,1210:其他");	
		
		
		
		paMap7.put("rootID", "1300,1400");
		paMap7.put("rootName", "创意/设计");
		paMap7.put("firstViewName1","艺术文化");
		paMap7.put("firstViewID1","1301");
		paMap7.put("firstViewName2","研发设计");
		paMap7.put("firstViewID2","1302");
		paMap7.put("firstViewName3","工业设计");
		paMap7.put("firstViewID3","1401");
		paMap7.put("firstViewName4","建筑家居");
		paMap7.put("firstViewID4","1402");

		paMap7.put("childID1","1300");	
		paMap7.put("childName1","创意");	
		paMap7.put("childListData1","1301:艺术文化,1302:研发设计,1303:动漫媒体,1304:咨询策划,1305:时尚消费,1306:其他");	
		paMap7.put("childID2","1400");	
		paMap7.put("childName2","设计");	
		paMap7.put("childListData2","1401:工业设计,1402:建筑家居,1403:服装包装,1404:媒体动漫,1405:宣传 LOGO,1406:广告 VI,1407:画册书籍,1408:机械设计,1409:其他");	
		
		
		
		paMap8.put("rootID", "1500,1600");
		paMap8.put("rootName", "媒体/影视");
		paMap8.put("firstViewName1","新媒体");
		paMap8.put("firstViewID1","1501");
		paMap8.put("firstViewName2","网络媒体");
		paMap8.put("firstViewID2","1502");
		paMap8.put("firstViewName3","摄像摄影");
		paMap8.put("firstViewID3","1601");
		paMap8.put("firstViewName4","主持模特");
		paMap8.put("firstViewID4","1602");
		
		paMap8.put("childID1","1500");	
		paMap8.put("childName1","媒体");	
		paMap8.put("childListData1","1501:新媒体,1502:网络媒体,1503:报纸媒体,1504:杂志媒体,1505:广播媒体,1506:电视媒体,1507:户外媒体,1508:其他");	
		paMap8.put("childID2","1600");	
		paMap8.put("childName2","影视");	
		paMap8.put("childListData2","1601:摄像摄影,1602:主持模特,1603:策划制作 ,1604:后期制作,1605:编导演员,1606:化妆造型,1607:艺术设计,1608:灯光音效,1609:其");	
		
		
		
		paMap9.put("rootID", "1700,1800");
		paMap9.put("rootName", "翻译/出版");		
		paMap9.put("firstViewName1","英语");
		paMap9.put("firstViewID1","1701");
		paMap9.put("firstViewName2","法语");
		paMap9.put("firstViewID2","1702");
		paMap9.put("firstViewName3","作家");
		paMap9.put("firstViewID3","1801");
		paMap9.put("firstViewName4","记者");
		paMap9.put("firstViewID4","1802");				
		
		paMap9.put("childID1","1700");	
		paMap9.put("childName1","翻译");	
		paMap9.put("childListData1","1701:英语,1702:法语,1703:西班牙语,1704:葡萄牙语,1705:俄语,1706:阿拉伯语,1707:德语,1708:日语,1709:意大利语,1710:韩语,1711:其他");		
		paMap9.put("childID2","1800");	
		paMap9.put("childName2","出版");	
		paMap9.put("childListData2","1801:作家,1802:记者,1803:文案策划,1804:编辑,1805:撰稿,1806:排版设计,1807:出版发行,1808:采编校对,1809:其他");	
				
	
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list.add(paMap);
		list.add(paMap2);
		list.add(paMap3);
		list.add(paMap4);
		list.add(paMap5);
		list.add(paMap6);
		list.add(paMap7);
		list.add(paMap8);
		list.add(paMap9);
		System.out.println(list);

	}

}
