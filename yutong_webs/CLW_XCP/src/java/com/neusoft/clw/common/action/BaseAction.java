/*******************************************************************************
 * @(#)BaseAction.java 2008-1-9
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.action;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.log.service.OperateLogRecorder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author <a href="mailto:hegq@neusoft.com">Puras.He </a>
 * @version $Revision 1.1 $ 2008-1-9 11:15:12
 */
public class BaseAction extends ActionSupport implements Externalizable {

    /**
     * 生成序列号
     */
    private static final long serialVersionUID = 5655419318535345313L;

    public static final String CANCEL = "cancel";

    public static final String NEXT = "next";

    public static final String NO_PERMISSION = "no_permission";

    // protected final Log log = LogFactory.getLog(getClass());
    protected final Logger log = Logger.getLogger(getClass());

    public String cancel() {
        return CANCEL;
    }

    /**
     * 无权限字符串返回
     * @return
     */
    public String noPermission() {
        return NO_PERMISSION;
    }

    /**
     * 设置日志操作类型
     * @param operType
     */
    protected void setOperationType(String operType) {
        setLogInRequest(OperateLogRecorder.YTP_USER_OPER_TYPE, operType);
    }

    /**
     * 添加操作日志描述
     * @param operLog
     */
    protected void addOperationLog(String operLog) {
        setLogInRequest(OperateLogRecorder.YTP_USER_OPERATION_LOG, operLog);
    }

    /**
     * 设置所属应用系统
     * @param applyId 所属应用系统
     */
    protected void setApplyId(String applyId) {
        setLogInRequest(OperateLogRecorder.YTP_USER_OPER_APPLY_ID, applyId);
    }

    /**
     * 设置所属模块
     * @param module 所属模块
     */
    protected void setModuleId(String moduleId) {
        setLogInRequest(OperateLogRecorder.YTP_USER_OPER_MODULE_ID, moduleId);
    }

    /**
     * 写入HTTPREQUEST请求中
     * @param key 钥匙
     * @param msg 值
     */
    private void setLogInRequest(String key, String msg) {
        HttpServletRequest request = (HttpServletRequest) ServletActionContext
                .getRequest();
        
        if(null == request) {
            WebContext ctx = WebContextFactory.get();
            request = ctx.getHttpServletRequest();
        }
        request.setAttribute(key, msg);
    }

    /**
     * 获取日志
     * @return
     */
    public Logger getLog() {
        return log;
    }

    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {

    }

    public void writeExternal(ObjectOutput out) throws IOException {

    }
    
    public String getloginuuid(){
    	 return (String) ActionContext.getContext().getSession().get(
                 Constants.LOG_USE_ID);
    }

}
