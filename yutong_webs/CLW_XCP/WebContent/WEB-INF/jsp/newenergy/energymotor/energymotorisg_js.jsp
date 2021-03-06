<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_middelside' ],
			height_offset : 0,
			width_exclude: ['#content_middelside'],
			width_include : [ '#content_rightside'],
			width_offset : 1
		});

		//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_exclude : [ '.titleBar'],
			height_include : '#right_main',
			height_offset : 0,
			width_include : [ '.titleBar','#right_main'],
			width_offset : 0
		});
		jQuery('#right_main').mk_autoresize( {
			height_exclude : ['.car-info'],
			height_include : '.table_list',
			height_offset : 70,
			width_include : ['.car-info','.table_list'],
			width_offset : 0
		});		
		jQuery('.table_list').mk_autoresize({
		    height_include: '.bDiv',
		    height_offset: 70, // 该值各页面根据自己的页面布局调整
		    width_include: '.flexigrid',
		    width_offset: 8// 该值各页面根据自己的页面布局调整
		  });			
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		auto_size();
		jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
		auto_size();
	}
}

function mytreeonClick(type, treeId){
	jQuery("#isSearch").val("");
	jQuery("#alarmKey").val("");
	if(type=='1'){
		selectCar(treeId);
	}else{
		selectEnt(treeId);	
	}
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
var pomSel6 = null;
jQuery(function() {
	pomSel6 = new newSelect(jQuery("#routeName"),120);
	pomSel6.addSelect();
	getRouteSel();
	var galaList = jQuery('#galaList');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},
		        {display: '', name : 'longitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'latitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'speed', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'route_name', width : 200, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: 'ISG工作模式', name : 'ISG_mode', width : 110, sortable : true, align: 'center',escape: true,process: reWriteLink},
				{display: 'ISG电机当前输出转矩/Nm', name : 'ISG_TORQUE_OUT', width : 140, sortable : true, align: 'center',escape: true},
				{display: 'ISG电机当前转速/rpm', name : 'ISG_RATOTE', width : 130, sortable : true, align: 'center',escape: true},							  
		        {display: 'ISG电机温度/℃', name : 'ISG_TEMP', width : 110, sortable : true, align: 'center',escape: true},							  
		        {display: 'ISG电机控制器温度/℃', name : 'ISG_CONTROLLER_TEMP', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: 'ISG电机控制器母线电流/A', name : 'ISG_CONTROLLER_C', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: 'ISG电机控制器母线电压/V', name : 'ISG_CONTROLLER_V', width : 150, sortable : true, align: 'center',escape: true},						  
		        {display: 'ISG电机控制器实际接收扭矩', name : 'ISG_CONTROLLER_TORQUE_IN', width : 160, sortable : true, align: 'center',escape: true},							  
		        {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'center',escape: true}
		        ], 
		       sortname: 'terminal_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	 searchMotorList();
});

function reWriteLink(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[7];
	
	if( record == "100"){
		tdV = record+"(复位)";
	} else if(record == "001"){
		tdV = record+"(驱动)";
	} else if(record == "010"){
		tdV = record+"(发电)";
	} else if(record == "011"){
		tdV = record+"(待机)";
	} else if(record == "110"){
		tdV = record+"已停机";
	} else {
		tdV = record;
	}
	return tdV;	
}

function getRouteSel(){
	jQuery.ajax({
		  type: 'POST',  
		  url: '<s:url value="/routeGrid/getUserRouteFind.shtml" />',	
		  data: {},	
		 success: function(data) {
			 if(data.message == "success"){
	 			var routeList = data.list;
	 			for(var i = 0,len = routeList.length; i < len; i++){
	 				var objs = routeList[i];
	 				pomSel6.addOption(objs.route_id,objs.route_name);
	 			}
	 			pomSel6.seBindOption(function(){searchMotorList();});
			 } else {
				 
			 }
	      },
		  error:function (){
	      }
	});
}

function calcColumn(persent, minwidth, offset) {
    var pagewidth = 0;
    
    var width = 0;
    if(jQuery.browser.msie){ 
       pagewidth = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
    } else { 
       pagewidth = self.innerWidth; 
    } 
    if(offset > 0) {
       width = pagewidth - offset;
    } else {
       width = pagewidth;
    }
    return width*persent < minwidth ? minwidth : width*persent;
}



jQuery(function() {
	auto_size();	
	jQuery(window).mk_autoresize({
		height_include: '#content',
		height_exclude: ['#header', '#footer'],
		height_offset: 0,
		width_include: ['#header', '#content', '#footer'],
		width_offset: 0});
	auto_size();
});

function auto_size(){
	jQuery(window).mk_autoresize({
		min_width: 1170
	  });
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : [ '#content_rightside'],
		width_offset : 1
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : [ '#leftInfoDiv', '.newsearchPlan'],
		height_include : '#lefttree',
		height_offset : 10

	});

	//计算树区域高度
	//jQuery('#lefttree').mk_autoresize( {
	//	height_include : '#treeDemo',
	//	height_offset : 20
	//});


	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : [ '.titleBar'],
		height_include : '#right_main',
		height_offset : 0,
		width_include : [ '.titleBar','#right_main'],
		width_offset : 0
	});
	jQuery('#right_main').mk_autoresize( {
		height_exclude : ['.car-info'],
		height_include : '.table_list',
		height_offset : 0,
		width_include : ['.car-info','.table_list'],
		width_offset : 0
	});		
	jQuery('.table_list').mk_autoresize({
	    height_include: '.bDiv',
	    height_offset: 70, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 8// 该值各页面根据自己的页面布局调整
	  });
}

function keyEnter(event){
	var e = event ? event : window.event;
	if(e.keyCode=='13'){
		searchMotorList();
	}
}

//查询
function searchMotorList() {
	var filterFlag = "";
	if(jQuery("#filterFlag").attr("checked")){
		filterFlag = "1";
	}
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/energyMotor/getMontorISGList.shtml" />',
		params: [{name: 'vehicleVin', value: finalVIN },
		         {name: 'vehicleln',value: jQuery("#vehicleln").val()},
			     {name: 'startTime', value: jQuery("#startTime").val()},
			     {name: 'endTime', value: jQuery("#endTime").val()},
			     {name: 'routeName',value: jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val()},
			     {name: 'organization_id',value: finalENT},
			     {name: 'car_state',value: filterFlag}]
	});
	jQuery('#galaList').flexReload();
}

function exportData(){
// 	if(finalVIN != "" || finalENT != ""){
// 		var vehicleln="";
// 		var node = jQuery.fn.zTree.getZTreeObj("treeDemo").getNodeByParam("id", finalVIN, null);
// 		if(node != null) {
// 			vehicleln = node.name;
// 		}
		if(confirm("确定要导出ISG电机状态信息吗？")) {
			jQuery("#organization_id").val(finalENT);
			jQuery("#vehicleVin").val(finalVIN);
			document.getElementById('vehiclestatus_form').action="<s:url value='/energyMotor/exportMontorISG.shtml' />";
			document.getElementById('vehiclestatus_form').submit();
		}
// 	}else {
// 		alert("请选择车辆！");
// 	}
}



//选择车辆后，刷新车辆运行日志数据
var finalVIN = "";
var finalENT = "";
function selectCar(radioObj){
	finalVIN = radioObj;
	finalENT = "";
	searchMotorList();
}
function selectEnt(radioObj){
	finalVIN = "";
	finalENT = radioObj;
	searchMotorList();
}	

function pickedstarttime(){
	var date1 = document.getElementById('startTime').value + ":00";
	var date2 = document.getElementById('endTime').value + ":00";
    var line_start_time=date1.replace(/-/g,"/");
    var line_end_time=date2.replace(/-/g,"/");
    
	var startdate=new Date(line_start_time);
	var enddate=new Date(line_end_time);
	var iDays = parseInt((enddate - startdate) / 1000 / 60 / 60 / 24);
	
	if(iDays < 0 || iDays > 6){
		enddate.setTime(startdate.getTime() + 1000*60*60*24*7);
	}
	var str1 = startdate.pattern("yyyy/MM/dd");
	var str2 = enddate.pattern("yyyy/MM/dd");
	//if(str1 != str2){
		enddate = new Date(str1+" 23:59");
	//}
	$dp.$('endTime').value=enddate.pattern("yyyy-MM-dd HH:mm") ;
}
function pickedendtime(){
	var date1 = document.getElementById('startTime').value + ":00";
	var date2 = document.getElementById('endTime').value + ":00";
    var line_start_time=date1.replace(/-/g,"/");
    var line_end_time=date2.replace(/-/g,"/");

	var startdate=new Date(line_start_time);
	var enddate=new Date(line_end_time);
	var iDays = parseInt((enddate - startdate) / 1000 / 60 / 60 / 24);

	if(iDays < 0 || iDays > 6){
		startdate.setTime(enddate.getTime() - 1000*60*60*24*7);
	}
// 	var str1 = startdate.pattern("yyyy/MM/dd");
// 	var str2 = enddate.pattern("yyyy/MM/dd");
// 	if(str1 != str2){
// 		startdate = new Date(str2+" 00:00:00");
// 	}
	$dp.$('startTime').value=startdate.pattern("yyyy-MM-dd HH:mm") ;
}
/**      
* 对Date的扩展，将 Date 转化为指定格式的String      
* 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
* 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
* eg:      
* (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
*/        
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "\u65e5",         
    "1" : "\u4e00",         
    "2" : "\u4e8c",         
    "3" : "\u4e09",         
    "4" : "\u56db",         
    "5" : "\u4e94",         
    "6" : "\u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}
</script>
