<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
  /** 查询厂家 **/
  function submit() {
    Wit.commitAll($('enterprisebrowse_form'));
  }

  /** 选择企业 **/
  function operate(str1,str2){
    window.dialogArguments.document.getElementById("entipriseId").value=str1;
    window.dialogArguments.document.getElementById("entipriseName").value=str2;
    window.close();
  }

  /** 取消选择 **/
  function cancelSelect(){
    window.dialogArguments.document.getElementById("entipriseId").value="";
    window.dialogArguments.document.getElementById("entipriseName").value="";
    window.close();
  }
</script>