<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript">
	/** 添加家长**/
    function addParent() {
      var form = $('editsms_form');
      form.action = 'addEditParent.shtml';
      Wit.commitAll(form);
    }

    /** 删除家长 **/
    function deleteParent(parentId) {
      $('delParentId').value = parentId;
      var form = $('editsms_form');
      form.action = 'deleteEditParent.shtml';
      Wit.commitAll(form);
    }

	/** 验证联系人信息 **/
    function checkPersonInfo() {
        var relativeTypeObjs = document.getElementsByName("relativeType");
        var relativeNameObjs = document.getElementsByName("relativeName");
        var cellPhoneObjs = document.getElementsByName("cellPhone");
        var endTimeObjs = document.getElementsByName("endTime");

        var warnObj = $('warnMessage');

        if(relativeTypeObjs.length==0) {
  		  warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;请添加联系人！";
          return false;
  		}
        
    	for( i=0; i<relativeTypeObjs.length; i++) {
          if(!Mat.checkNameNull(relativeTypeObjs[i].value)) {
            warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;联系人信息未填写完整，请补充填写！";
            return false;
          }
    	}

    	for( i=0; i<relativeNameObjs.length; i++) {
          if(!Mat.checkNameNull(relativeNameObjs[i].value)) {
        	warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;联系人信息未填写完整，请补充填写！";
            return false;
          }
    	}

    	for( i=0; i<cellPhoneObjs.length; i++) {
            if(!Mat.checkNameNull(cellPhoneObjs[i].value)) {
          	  warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;联系人信息未填写完整，请补充填写！";
              return false;
            } else {
              var reg = /^[0-9]{11}$/;
              if(!reg.test(cellPhoneObjs[i].value)) {
            	warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;手机号码格式不正确，请输入11位数字！";
                return false;
              }
            }
      	}

    	for( i=0; i<endTimeObjs.length; i++) {
            if(!Mat.checkNameNull(endTimeObjs[i].value)) {
          	warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;联系人信息未填写完整，请补充填写！";
              return false;
            }
      	}
    	warnObj.innerHTML = "";
    	return true;
      }
    
	/** 初始化样式 **/
	function setFormMessage() {
	}

	/** 加载事件 **/
	function setFormEvent() {
	}

	function submitAlterForm() {
		trimAllObjs();
		var f1 = checkPersonInfo();
		if (f1) {
			Wit.commitAll($('editsms_form'));
		} else {
			return false;
		}
	}

	function goBack(url) {
		Wit.goBack(url);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);

	//页面自适应
	(function (jQuery) {
	 jQuery(window).resize(function(){
	  testWidthHeight();
	 });
	 jQuery(window).load(function (){
	  testWidthHeight();
	 });
	})(jQuery);
	
	//获取页面高度
	function get_page_height() {
	  var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
	  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
	//获取页面宽度
	function get_page_width() {
	  var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
	  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}
	 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var test=document.getElementById("smsTable");
		if(h>205){
			test.style.height = h-162;
		}else{
		}
		jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });
		h = get_page_height();
		if(h>205){
			test.style.height = h-162;
		}else{
		}
	}
</script>