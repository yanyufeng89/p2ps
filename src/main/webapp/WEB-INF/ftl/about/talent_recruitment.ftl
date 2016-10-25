<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          JobPlus-人才招聘
    </title> 
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_framework.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/pj_about.css">
  </head>
  <body>
    <div class="page" id="feedback">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='pj-container'>
         <div class='left'>
           <#include "/mydocs/commonTemplate/aboutnav/aboutnav.ftl"/>
         </div>
         <div class='right'>
            <div class='terms_service'>
              <div>
                 <img alt='' src='/image/talent_recruitment.jpg' width='100%' height='120'>
              </div>
              <ul class="jobList">
		        <li class="currents" id="operator">运营总监</li>
		        <li id="server">产品经理</li>
		      </ul>
              <dl class='contact' id='operatorTab'>
                <dd class='position_statement'>岗位职责：</dd>
                <dd>1.根据公司发展情况，负责公司业务规划、模式搭建，协助制定公司中、长期战略发展规划、竞争策略等，并负责组织实施、监控、推动战略经营目标实现。</dd>
                <dd>2.在公司经营战略指导下，全面进行市场调查和反馈，业务方向的综合策划，加强公司品牌形象建设，宣传公司外延、内涵价值，提高公司知名度及品牌影响力。</dd>
                <dd>3.全面负责公司的新媒体运营，用户运营，活动运营，SNS运营完成公司既定运营指标。</dd>
                <dd>4.把握互联网运营、新媒体产品等发展趋势，进行用户分析，充分利用人力、物力、财力资源，进行产品盈利模式验证。</dd>
                <dd>5.负责公司微信、微博、APP、网站、铺等延伸产品的的设计与推广。</dd>
                <dd>6.全面负责公司市场营销团队搭建，负责线上品牌营销项目， 市场开拓和销售目标达成，完成公司既定市场营销目标。</dd>
                <dd class='position_statement'>任职资格:</dd>
                <dd>1.从事大型知名互联网行业工作至少5年以上，并且有3年以上管理经验，熟知互联网行业，担任过互联网公司运营负责人。</dd>
                <dd>2.对互联网格局、电子商务、新媒体线上线下运营模式等有深刻的理解。</dd>
                <dd>3.具备超强的互联网市场运营开拓及财经创投企业家品牌体验感觉。</dd>
                <dd>4.具备优秀的领导力、良好的表达沟通能力，以及较强的洞察能力、分析能力和语言表达能力，工作积极主动。</dd>
                <dd>5.具有良好的创业精神和责任担当，能承担高负荷工作节奏。</dd>
                <dd>请发邮件到<a href='mailto:bill.chen@jobplus.com.cn'>&nbsp;&nbsp;bill.chen@jobplus.com.cn</a></dd>
              </dl>
              <dl class='contact' id='serverTab' style='display:none'>
                <dd class='position_statement'>岗位要求：</dd>
                <dd>1.负责公司产品规划设计，包括需求文档、流程图、产品原型等文件。</dd>
                <dd>2.负责与研发、设计、运营等各团队沟通协调，引导完成产品的界面、功能、流程设计，把控项目进度及质量。</dd>
                <dd>3.负责在产品上线后为各团队进行产品培训，确保各个协作团队对产品充分理解，并能熟练使用。</dd>
                <dd>4.收集需求，把握产品的迭代更新, . 负责产品的竞争分析，市场动态分析和对应产品策略。</dd>
                <dd>5. 体负责互联网企业服务产品，后台产品和业务流程的设计规划，建立客户数据分析系统，完善决策支持体系。</dd>
                <dd>6.注各项产品及运营数据，为公司产品战略决策提供建议及改进意见。</dd>
                <dd class='position_statement'>任职要求:</dd>
                <dd>1.3年以上产品经理相关工作经验, 思维跳跃，逻辑清晰，能跳出本身的业务模式去全局思考。</dd>
                <dd>2.热爱互联网产品设计，熟练使用相关产品设计软件。</dd>
                <dd>3.具有优秀的项目管理能力和出色的沟通能力。</dd>
                <dd>4.注重交互细节，并有较强的数据及用户行为分析能力与执行能力。</dd>
                <dd>5.出色的沟通、整合、规划、抗压能力，执行力强，对结果负责。</dd>
                <dd>6.懂技术或者有技术背景优先考虑。</dd>
                <dd>请发邮件到<a href='mailto:bill.chen@jobplus.com.cn'>&nbsp;&nbsp;bill.chen@jobplus.com.cn</a></dd>
              </dl>
            </div>
         </div>
      </div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/about/communaljs.ftl"/>
      <script type="text/javascript">
        //人才招聘 职位切换
	    $('.jobList li').live('click',function(){
	    	$(this).addClass('currents').siblings().removeClass('currents');
	    	if($(this).attr('id')=='operator'){
	    	  $('#operatorTab').show();
	    	  $('#serverTab').hide();
	    	}else{
	    	  $('#operatorTab').hide();
	    	  $('#serverTab').show();
	    	}
	    });
      </script>
  </body>

</html>