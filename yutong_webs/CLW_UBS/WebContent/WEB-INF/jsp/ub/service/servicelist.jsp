<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<title><s:property
	value="getText('menu.ub.serviceliveness')" /></title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>



</head>
<body>
<%@include file="../../common/menu.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/fusioncharts/FusionCharts.js' />"></script>
<script type="text/javascript" 
	src="<s:url value='/scripts/indicator/GlareTpl.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>
<script type="text/javascript" 
	src="<s:url value='/scripts/grouptype/GroupType.js' />"></script>
<link rel="stylesheet" href="<s:url value='/scripts/layer/skin/layer.css' />"
	type="text/css" media="all" />
<script type="text/javascript" 
	src="<s:url value='/scripts/layer/layer.min.js' />"></script>

<script type="text/javascript">
var gtype=null;
var chartData=null;
var chartData_month=null;
function clearQSwitch()
{
	jQuery('#q_r7').attr("class", "");
	jQuery('#q_r30').attr("class", "");

}
function q_switch(d) {
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	
	switch (d) {
	case 7:
		jQuery('#q_r7').attr("class", "r_current");
		var m=moment().subtract("days",1);
		var lm=moment().subtract("days",7);
		jQuery("#startTime").attr("value",lm.format("YYYY-MM-DD"));
		jQuery("#endTime").attr("value",m.format("YYYY-MM-DD"));
		break;
	case 30:
		jQuery('#q_r30').attr("class", "r_current");
		var m=moment().subtract("days",1);
		var lm=moment().subtract("days",30);
		jQuery("#startTime").attr("value",lm.format("YYYY-MM-DD"));
		jQuery("#endTime").attr("value",m.format("YYYY-MM-DD"));
		break;

	}
	gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	searchChartData();
	searchList();
}

function iniTimeSpan()
{
	
	var st=getQueryStringByName("st");
	var et=getQueryStringByName("et");
	if(st)
	{
		jQuery("#startTime").attr("value",st);
	}
	if(et)
	{
		jQuery("#endTime").attr("value",et);
	}
	if((!st)|| (!et))
	{
		var month=moment().subtract("months",1).format("YYYY-MM");	
		jQuery("#selMonth").attr("value",month);
		setMonth(month);
	}
	
}
function iniPlat()
{
	var v_plat=getQueryStringByName("plat");
	if(v_plat)
	{
		jQuery("#plat").val(v_plat);
	}
}

//如果当前日期是每月2号以前，则不允许选择当前月。
function getMaxDateMonth()
{
	var d=moment().date();
	var r=moment().format("YYYY-MM-DD");
	if(d<=2)
		r=moment().subtract("days",d).format("YYYY-MM-DD");
	return r;
}
//m:日期字符串，格式：YYYY-MM
function setMonth(m)
{	
	var d=moment(m, "YYYY-MM");	
	
	var curmaonth=moment().format("YYYY-MM");
	
	var firstDay = d.startOf('month').format('YYYY-MM-DD');
	var  lastDay  = (curmaonth==m)?moment().subtract("days",1).format('YYYY-MM-DD'):d.endOf('month').format('YYYY-MM-DD');
	jQuery("#startTime").attr("value",firstDay);		
	jQuery("#endTime").attr("value",lastDay);	
}
function selMonthClick()
{
	clearQSwitch();
	var m=jQuery("#selMonth").attr("value");	
	setMonth(m);
	gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	searchChartData();
	searchList();
}

function startTimeClick()
{
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
}
function endTimeClick()
{
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	searchChartData();
	searchList();
}
function serviceClick()
{
	searchChartData();
	searchList();
}
function platClick()
{
		
	searchChartData();
	searchList();
}
function onGTypeChange(data)
{
	loger(data);
	searchChartData();
	searchList();
}
function searchChartData()
{
	setChartLoading("n_chart");
	
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat").val();
	
	
	
	//?plat=web&startDay=20130601&endDay=20130720&dateType=week&serviceID=2
	var url='<s:url value="/ub/srv/json/getServiceVisChart.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+"&plat="+plat;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			chartData=data;
			loadChart(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData("n_chart");
		}
	});

	
}
function loadChart(data)
{
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData("n_chart");
		return;		
	}
	if(data.bars==null || data.bars.length<=0)
	{
		loger("loadChart:data.lines is null or empty.");
		setChartNoData("n_chart");
		return;			
	}

	var sbar=[];
	
	sbar.push("<chart caption='服务访问情况'  yAxisName='次数' >");
			
	
	for(var i=0; i< data.bars.length;i++)
	{
		var item=data.bars[i];
		sbar.push("<set label='"+item.name+"' value='"+item.vis+"' tooltext='"+item.name+";访问次数："+item.vis+"'/>");
		
	}
	
	var tail="<styles>"+
	"<definition>"+
	"<style name='MyCaptionFontStyle' type='font' face='Verdana' size='16' bold='1' />"+
	"<style name='myToolTipFont' type='font' font='Arial' size='12' color='FF5904'/>"+
	"</definition>"+
	"<application>"+
		"<apply toObject='caption' styles='MyCaptionFontStyle' />"+
		"<apply toObject='ToolTip' styles='myToolTipFont' />"+
	"</application>"+
	"</styles>" +
"</chart>";
	var charXml=sbar.join("")+tail;
	var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Column2D.swf' />","myChartId4", "1000","300","0");
	myChartbarms.setDataXML(charXml);
	myChartbarms.render("n_chart");
		
	
}

function setChartNoData(chartdiv,w,h)
{
	if(typeof(w)=="undefined")w=1000;
	if(typeof(h)=="undefined")h=300;
	var tmp_nodata=jQuery("#tmp_chart_nodata").html();
	var tmp=GlareTpl.parse(tmp_nodata,{width:w,height:h});
	var chardiv=jQuery("#"+chartdiv);	
	chardiv.html(tmp);
}
function setChartLoading(chartdiv,w,h)
{	
	if(typeof(w)=="undefined")w=1000;
	if(typeof(h)=="undefined")h=300;
	var tmp_loading=jQuery("#tmp_chart_loading").html();
	var tmp=GlareTpl.parse(tmp_loading,{width:w,height:h});
	//var tmp_nodata=jQuery("#tmp_chart_nodata").html();
	var chardiv=jQuery("#"+chartdiv);
	chardiv.html(tmp);
}
function tabClick(url)
{
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&plat="+jQuery("#plat").val();
	var url_q=url+q;
	location=url_q;
}
function goToService(sid)
{
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&plat="+jQuery("#plat").val()+"&srvID="+sid;
	var url='<s:url value="/ub/srv/overall.shtml"/>';
	var url_q=url+q;
	location=url_q;
}
function viewSingleServiceChart(pid)
{
	var servicename=get_cell_text(pid, 0);
	
	var i=jQuery.layer({
	    type : 1,
	    title : servicename+'服务月活跃度',
	    closeBtn:[0,true],
	    shadeClose:true,	    
	    page:{dom:'#viewmonthlivenessdiv'},
	    //msg:'ttt',
	    area :['auto' , 'auto'],
	    success:function(){loadMonthLiveness(pid);}//闭包传参数
	});
	//jQuery(document).one("click",function(){layer.close(i);});
	

}
function loadMonthLiveness(pid)
{
	setChartLoading("n_chart_month",798,298);//去掉 2px border ，ie 7下测试
	
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat").val();
	
	
	
	
	var url='<s:url value="/ub/srv/json/getSingleServiceActive.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+"&plat="+plat+"&serviceID="+pid;
	//var url="http://localhost:8080/CLW_XCM/ub/srv/json/getSingleServiceActive.shtml?startDay=2013-01-19&endDay=2013-07-19&plat=web&serviceID=1";
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			chartData_month=data;
			loadChart_month(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData("n_chart_month",798,298);
		}
	});	
}
//月活跃度
function loadChart_month(data)
{
	if(data==null)
	{
		loger("data is null");
		setChartNoData("n_chart_month",798,298);
		return;		
	}
	if(data.singleBar==null || data.singleBar.length<=0)
	{
		loger("data.singleBar is null or empty.");
		setChartNoData("n_chart_month",798,298);
		return;			
	}

	var sbar=[];
	
	sbar.push("<chart caption=''  yAxisName='' numberSuffix='%25 '>");
			
	
	for(var i=0; i< data.singleBar.length;i++)
	{
		var item=data.singleBar[i];
		sbar.push("<set label='"+item.reportDate+"' value='"+item.visActivity+"' tooltext='"+item.reportDate+";活跃度："+getActivityPercent(item.visActivity)+"'/>");
		
	}
	
	var tail="<styles>"+
	"<definition>"+
	"<style name='MyCaptionFontStyle' type='font' face='Verdana' size='16' bold='1' />"+
	"<style name='myToolTipFont' type='font' font='Arial' size='12' color='FF5904'/>"+
	"</definition>"+
	"<application>"+
		"<apply toObject='caption' styles='MyCaptionFontStyle' />"+
		"<apply toObject='ToolTip' styles='myToolTipFont' />"+
	"</application>"+
	"</styles>" +
	"<trendlines>"+ 	 
    	"<line startValue='50' color='91C728' displayValue='50%25' />"+ 
 	"</trendlines>"+ 
"</chart>";
	var charXml=sbar.join("")+tail;
	var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Column2D.swf' />","myChartId4", "800","300","0");
	myChartbarms.setDataXML(charXml);
	myChartbarms.render("n_chart_month");
		
	
}
function getActivityPercent(a)
{
	//return a;
	return a+'%25';
	
}
/////////////////

//flexgrid
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
//转换链接
function reWriteLink(tdDiv,pid){
		tdDiv.innerHTML = '<a href="#" onclick="goToService('+pid+')" >' + tdDiv.innerHTML +'</a>';
}
function sufixPercent(tdDiv,pid){
	tdDiv.innerHTML =tdDiv.innerHTML +'%';
}

function setViewLink(tdDiv,pid)
{
	
	tdDiv.innerHTML = '<a href="#" onclick="viewSingleServiceChart('+pid+')" >查看</a>';
}
function changeBoolean(tdDiv,pid)
{
	if(tdDiv.innerHTML=="false")
		tdDiv.innerHTML="<span style='color:red'>否</span>";
	else
		tdDiv.innerHTML="<span style='color:green'>是</span>";
}

jQuery(function() {
	iniTimeSpan();
   	
   	iniPlat();
   	
   	
   	var q_gtype=getQueryStringByName("datetype");
   	if(typeof(q_gtype)=="undefined")q_gtype="day";
	gtype=new GroupType({containerId:"timespancon1",startTime:jQuery("#startTime").val(),endTime:jQuery("#endTime").val(),selectedType:q_gtype,onChange:onGTypeChange});
   	gtype.render();
   	gtype.disable(true);
   	searchChartData();
	
	
	//	var url='<s:url value="/ub/srv/json/getServiceVisChart.shtml" />'
	//		+'?startDay='+startDate+'&endDay='+endDate+"&plat="+plat;
	var roleList = jQuery('#vlist');
	roleList.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
				{name: 'plat', value: jQuery('#plat').val()}],
		url: '<s:url value="/ub/srv/json/getServiceVisChart.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
					
		    		{display: '<s:text name="ub.service.name" />', name : 'z', width : '200', sortable : false, align: 'left', process: reWriteLink},
		    		{display: '<s:text name="ub.system.vcnt" />', name : 'y', width : '100', sortable : true, align: 'left'},
		       		{display: '<s:text name="ub.system.ventcnt" />', name : 'g', width : '100', sortable : true, align: 'left'},
		       		{display: '<s:text name="ub.openentcnt" />', name : 'col1', width : '100', sortable : true, align: 'left'},		       		
		       		{display: '<s:text name="ub.liveness" />', name : 'col1', width : '80', sortable : true, align: 'left',process:sufixPercent},
		       		{display: '上线时间', name : 'col1', width : '100', sortable : true, align: 'left'},
		       		{display: '<s:text name="ub.publishduration" />', name : 'col1', width : '100', sortable : true, align: 'left'},
		       		{display: '<s:text name="ub.service.fullfillbase" />', name : 'col1', width : '100', sortable : true, align: 'left',process:changeBoolean},
		   		   	{display: '<s:text name="ub.service.fullfillqua" />', name : 'full_name', width : '100', sortable : true, align: 'left',process:changeBoolean},
		   		   	{display: '<s:text name="ub.service.viewlivenessbymonth" />', name : 'full_name', width : '100', sortable : true, align: 'left', process: setViewLink}
		    		],
		    	sortname: 'apply_id',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp:20,	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	});
});

function searchList() {
	jQuery('#empDiv').css("display","none");
	jQuery('#vlist').flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
				{name: 'plat', value: jQuery('#plat').val()}]
	});
	jQuery('#vlist').flexReload();
}

</script>
<s:form id="logincntstat_form" action="querystat" method="post">

	<table height="628px" width="100%" border="0" cellspacing="0"
		cellpadding="0">
		<tr valign="top">
			<td class="reportInline">

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				
					<tr>
						<td class="reportOnline2" >
						<div
							style="float: left;  padding: 5px; line-height: 30px;width:100%;">
						<span style="float:left;padding:0px 10px;font-weight:bold;"> <s:property
							value="getText('ub.system.statduration')" />：</span>
						<ul class="recent_ul">
							<li id="recent7"><a href="#" id="q_r7" onclick="q_switch(7)">
							 <s:property
							value="getText('ub.recent7day')" /></a></li>

							<a href="#" id="q_r30" onclick="q_switch(30)">
							<s:property
							value="getText('ub.recent30day')" /> </a>
							</li>
							</ul>
							
							<span style="float:left;padding:0px 10px;"> <s:property
							value="getText('ub.selectmonth')" />：</span>
							<div style="float:left;padding:0px 10px;">
 &nbsp;
								 <s:textfield
												id="selMonth" name="selMonth" 
												onfocus="var date=getMaxDateMonth();WdatePicker({maxDate:date,skin:'whyGreen',dateFmt:'yyyy-MM',onpicked:selMonthClick})"
												cssClass="Wdate" ></s:textfield>
							</div>
							<span style="float:left;padding:0px 10px;"> <s:property
							value="getText('ub.dateduration')" />：</span>
							<div style="float:left;padding:0px 10px;">

								 <s:textfield
								id="startTime" name="searchparam.startDate"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\\\'endTime\\\');}',dateFmt:'yyyy-MM-dd',autoPickDate:true,onpicked:function(){startTimeClick();endTime.focus();}})"
								cssClass="Wdate">
							</s:textfield>-<s:textfield id="endTime" name="searchparam.endDate"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\\\'startTime\\\');}',maxDate:'%y-%M-{%d-1}',dateFmt:'yyyy-MM-dd',autoPickDate:true,onpicked:endTimeClick})"
								cssClass="Wdate">
							</s:textfield>
							</div>
							<!-- timespan -->
							<div id="timespancon1" style="margin:0px 10px;float:left;"></div>						
							<!-- timespan -->
							<div style="float:left;padding:0px 10px;" >
								<s:property value="getText('ub.selectplat')" />：
                              
                              	 <s:select id="plat" list="#{'web':'WEB平台','app':'手机客户端'}"  listKey="key" listValue="value" onchange="platClick()">
		                        </s:select></div>

						</div>

						</td>
					</tr>
				
				<tr>
					<td valign="top" style="background:white;"><!-- body -->
					<div style="margin:10px;min-height:600px;background-color:white;">
						<div class="ubtab">
							<ul>
							<li><a href="#"  onclick="tabClick('<s:url value='/ub/srv/overall.shtml'/>')"> <s:property
								value="getText('ub.passivity.totalv')" /> </a></li>
							<li class="ubtabselect"><a href="#" > <s:property
								value="getText('ub.service.srvlistv')" /> </a></li>
								
							</ul>
							<div class="ubtabcontent" >
								<div style="margin:5px">
								<!-- tab内容 -->
																					
									<div style="text-align:center;width:100%;">
										<div id="n_chart"></div>
									</div>
									<div style="margin:5px;border:0px solid #cccccc;">
										<div style="height:30px;background:#eeeeee;border-top:1px solid #cccccc;border-left:1px solid #cccccc;border-right:1px solid #cccccc;margin-bottom:0px;line-height:30px;font-weight:bold;padding-left:10px;">服务访问详情</div>		
										<div style="margin:0px;">				
									  	<table id="vlist" width="100%"  cellspacing="0">
			                          	</table>
			                          	</div>
									</div>
									
								</div>
							</div>
						</div>
					
					
					
						
						<!-- tab内容 -->
						
					</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<div id="viewmonthlivenessdiv" style=""><div id="n_chart_month" style="width:800px;height:300px;"></div></div>
</s:form>

<script type="text/html" id="tmp_chart_nodata">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:[[height]]px;width:[[width]]px;color:rgb(234,118,79);line-height:[[height]]px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height[[height]]px;width:[[width]]px;color:rgb(72,190,244);line-height:[[height]]px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>
<%@include file="../../common/copyright.jsp"%>
</body>
</html>