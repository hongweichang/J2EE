<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title><s:property value="getText('menu.xs')" />&nbsp;|&nbsp;<s:property value="getText('informationmanage.menu')" /></title>

<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="informationInfo_validate.jsp"%>
  <s:form id="informationinfo_form" action="addInfo" method="post" onsubmit="return false;">
    <s:hidden id="issueStatus" name="issueStatus" />
    
    <table height="628px" width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td align="center" valign="top" >
    
    <div id="message">
      <s:actionerror theme="mat" />
      <s:fielderror theme="mat"/>
      <s:actionmessage theme="mat"/>
    </div>
    
    <table class="msgBox6" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
          <span class="msgTitle">&nbsp;&nbsp;
            <s:property value="getText('informationmanage.new')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="10%" height="28" align="right">
                <s:property value="getText('informationmanage.title')" />：
              </td>
              <td class="fsBlack" align="left">
                <s:textfield id="issueTheme" name="issueInfo.issueTheme" maxlength="60"/>
              </td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('informationmanage.content')" />：
              </td>
              <td class="fsBlack" align="left">
                <s:textarea id="issueContent"
						    name="issueInfo.issueContent" 
						    rows="3">
				</s:textarea>
				<div id="bottomSpace" style="display:none"></div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('informationmanage.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          
          <s:if test="#session.perUrlList.contains('111_0_6_5_4')">
          <a href="#" class="buttontwo" onclick="submitForm()">
            <s:property value="getText('btn.publish')" />
          </a>
          </s:if>
          
          <s:if test="#session.perUrlList.contains('111_0_6_5_3')">
          <a href="#" class="buttontwo" onclick="summitPurse()">
          	<s:property value="getText('btn.ts')" />
          </a>
          </s:if>
        </td>
      </tr>
    </table>
    
    </td>
    </tr>
    </table>
  </s:form>
  <%@include file="../common/copyright.jsp"%>
</body>
</html>
