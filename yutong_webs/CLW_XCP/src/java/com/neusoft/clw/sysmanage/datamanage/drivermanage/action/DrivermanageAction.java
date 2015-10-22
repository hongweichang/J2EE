package com.neusoft.clw.sysmanage.datamanage.drivermanage.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.driverManageService.DriverManageService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.fileupload.domain.UploadFileInfo;
import com.neusoft.clw.infomanage.studentmanage.action.DateFormatUtil;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

public class DrivermanageAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;
    
    private transient DriverManageService driverManageService;

    /** 显示数据list **/
    private List < DriverInfo > driverList;

    /** 修改数据用 **/
    private DriverInfo driverInfo;

    /** 显示消息 **/
    private String message = null;

    /** 性别编码 **/
    private Map < String, String > sexSysMap = new HashMap < String, String >();

    private final String FORBID = "forbid";

    private Map map = new HashMap();

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;
    
    private ByteArrayInputStream inputStream;
    
    /** 保存的照片ID **/
    private String picId = "";
    
    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }

    public Map < String, String > getSexSysMap() {
        return sexSysMap;
    }

    public void setSexSysMap(Map < String, String > sexSysMap) {
        this.sexSysMap = sexSysMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public DriverManageService getDriverManageService() {
        return driverManageService;
    }

    public void setDriverManageService(DriverManageService driverManageService) {
        this.driverManageService = driverManageService;
    }

    public List < DriverInfo > getDriverList() {
        return driverList;
    }

    public void setDriverList(List < DriverInfo > driverList) {
        this.driverList = driverList;
    }

    public DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }
    
    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 浏览驾驶员信息
     * @return
     */
    public String driverBrowse() {
        final String browseTitle = getText("dirver.browse.title");
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == driverInfo) {
                driverInfo = new DriverInfo();
            }
            UserInfo user = getCurrentUser();
            driverInfo.setEnterprise_id(user.getEntiID());
            if(null == driverInfo.getOrganization_id() || driverInfo.getOrganization_id().length() == 0) {
                driverInfo.setOrganization_id(user.getOrganizationID());
            }
            
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            driverInfo.setSortname(sortName);
            driverInfo.setSortorder(sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("DriverManage.getCount", driverInfo);
            // Page pageObj = new Page(page, totalCount, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);
            driverList = (List < DriverInfo >) service.getObjectsByPage(
                    "DriverManage.getInfos", driverInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(driverList, totalCount, pageIndex, rpNum);// 转换map

            if (driverList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_DRIVERMANAGE_QUERY);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String addBefore() {
        // 性别
        if (sexSysMap != null && sexSysMap.size() == 0) {
            sexSysMap = Constants.SEX_SYS_MAP;
        }
        return SUCCESS;
    }

    /**
     * 添加驾驶员
     */
    public String add() {
        if (null == driverInfo) {
            return addBefore();
        }
        final String addTitle = getText("driver.add.info");
        log.info(addTitle);
        try {
            UserInfo user = getCurrentUser();
            driverInfo.setCreater(user.getUserID());
            driverInfo.setModifier(user.getUserID());
            driverInfo.setEnterprise_id(user.getEntiID());
            //driverInfo.setOrganization_id(user.getOrganizationID());
            driverInfo.setValid_flag("0");
            driverInfo.setDriver_id(UUIDGenerator.getUUID());
            driverInfo.setPhoto_name(fileFileName);
            
            /**
            if (null != file) {
                // 判断是否包含相片文件
                String strFileName = file.getAbsolutePath().toLowerCase();

                BufferedInputStream in = null;
                ByteArrayOutputStream out = null;

                byte[] temp = new byte[1024];
                int size = 0;
                try {
                    in = new BufferedInputStream(new FileInputStream(
                            strFileName));
                    out = new ByteArrayOutputStream(1024);

                    while ((size = in.read(temp)) != -1) {
                        out.write(temp, 0, size);
                    }
                } catch (IOException e) {
                    ;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            ;
                        }
                    }
                }

                byte[] content = out.toByteArray();

                // 创建附件信息bean
                driverInfo.setPhotoContent(content);
            }
            **/
            if(null != picId && picId.length() > 0) {
                UploadFileInfo uploadFileInfo = new UploadFileInfo();
                uploadFileInfo.setDataId(picId);
                uploadFileInfo = (UploadFileInfo) service.getObject(
                        "FileUpload.getPhoto", uploadFileInfo);
                driverInfo.setPhoto_name(DateFormatUtil.getYYYYMMDDHHMISS()+ ".jpg");
                // 创建附件信息bean
                driverInfo.setPhotoContent(uploadFileInfo.getPhotoContent());
            }
            service.insert("DriverManage.insertdriverInfo", driverInfo);
        } catch (BusinessException e) {
            log.error(addTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("driverinfo.addsuccess.message");
        
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_DRIVERMANAGE_ADD);
        return SUCCESS;
    }

    /**
     * 修改驾驶员页面
     */
    public String editBefore() {
        final String editBefTitle = getText("driver.editbefore.title");
        log.info(editBefTitle);
        try {
            if (null != driverInfo) {
                if (sexSysMap != null && sexSysMap.size() == 0) {
                    sexSysMap = Constants.SEX_SYS_MAP;
                }
                driverInfo = (DriverInfo) service.getObject("DriverManage.getDriverInfo", driverInfo);
                if (null == driverInfo) {
                    setMessage("info.data.notexsist");
                    return ERROR;
                }
                // 设置当前驾驶员卡号值
                driverInfo.setOld_driver_card(driverInfo.getDriver_card());
                // 设置当前驾驶证号值
                driverInfo.setOld_driver_license(driverInfo.getDriver_license());
            } else {
                setMessage("info.data.notexsist");
                return ERROR;
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 显示驾驶员相片
     * @return
     */
    public String showDriverPhoto() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String driverId = request.getParameter("driver_id");
            
            DriverInfo tmp = new DriverInfo();
            tmp.setDriver_id(driverId);
            tmp = (DriverInfo) service.getObject(
                    "DriverManage.getDriverInfo", tmp);
            
            ByteArrayInputStream input = null;
            try {
                input = new ByteArrayInputStream(tmp.getPhotoContent());
            } catch (Exception e) {
                log.debug("显示驾驶员相片出现错误：" + e.toString());
                return ERROR;
            }
            this.setInputStream(input);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 修改驾驶员
     */
    public String updateDriver() {
        if (null == driverInfo) {
            return editBefore();
        }
        final String editTitle = getText("dirverinfo.update");
        try {
            UserInfo user = getCurrentUser();
            driverInfo.setModifier(user.getUserID());
            String updateStr = "";
            /**
            if (null != file) {
                // 判断是否包含相片文件
                String strFileName = file.getAbsolutePath().toLowerCase();

                BufferedInputStream in = null;
                ByteArrayOutputStream out = null;

                byte[] temp = new byte[1024];
                int size = 0;
                try {
                    in = new BufferedInputStream(new FileInputStream(
                            strFileName));
                    out = new ByteArrayOutputStream(1024);

                    while ((size = in.read(temp)) != -1) {
                        out.write(temp, 0, size);
                    }
                } catch (IOException e) {
                    ;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            ;
                        }
                    }
                }

                byte[] content = out.toByteArray();

                // 创建附件信息bean
                driverInfo.setPhotoContent(content);
                driverInfo.setPhoto_name(fileFileName);
                updateStr = "DriverManage.updateInfoAndPhotobyDriverid";
            } 
            **/
            if(null != picId && picId.length() > 0) {
                UploadFileInfo uploadFileInfo = new UploadFileInfo();
                uploadFileInfo.setDataId(picId);
                uploadFileInfo = (UploadFileInfo) service.getObject(
                        "FileUpload.getPhoto", uploadFileInfo);
                driverInfo.setPhoto_name(DateFormatUtil.getYYYYMMDDHHMISS()+ ".jpg");
                // 创建附件信息bean
                driverInfo.setPhotoContent(uploadFileInfo.getPhotoContent());
                updateStr = "DriverManage.updateInfoAndPhotobyDriverid";
            } else if("del".equals(driverInfo.getPhotoDelFlag())) {
                // 删除相片并更新信息
                updateStr = "DriverManage.updateInfoDelPhotobyDriverid";
            } else {
                updateStr = "DriverManage.updateInfoOnlybyDriverid";
            }
            
            service.update(updateStr, driverInfo);
        } catch (BusinessException e) {
            log.error(editTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("dirverinfo.editsuccess.message");
        // 设置操作描述
        // this.addOperationLog(formatLog(editTitle, driverInfo));
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_DRIVERMANAGE_MODIFY);
        return SUCCESS;
    }

    /**
     * 删除驾驶员
     */
    public String deleteDriver() {
        if (null == driverInfo) {
            return editBefore();
        }
        final String cancleTitle = getText("dirverinfo.delete");
        try {
            UserInfo user = getCurrentUser();
            driverInfo.setVaset_user_id(user.getUserID());
            int i = service.getCount("DriverManage.getvehCount", driverInfo);
            if (i > 0) {
                setMessage("dirverinfo.delete.nopermission");
                return FORBID;
            } else {
                service.update("DriverManage.deletebyDriverid", driverInfo);
            }
        } catch (BusinessException e) {
            log.error(cancleTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("driverinfo.delete.success");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_DRIVERMANAGE_DELETE);

        return SUCCESS;

    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, DriverInfo driverObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != driverObj) {
            if (null != driverObj.getDriver_id()) {
                OperateLogFormator.format(sb, "driverid", driverObj
                        .getDriver_id());
            }
        }
        return sb.toString();
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    /**
     * 转换Map
     * @param oilusedList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List driverList, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < driverList.size(); i++) {
            DriverInfo s = (DriverInfo) driverList.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getDriver_id());
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getDriver_name(),
                    s.getDriver_license(),
                    //s.getDriver_card(),
                    "<span id='"+ s.getDriver_id() +"'>"+ s.getDriver_card() +"</span>",
                    s.getDriver_sex(), 
                    s.getDriver_birth(),
                    s.getDriver_address(),
                    s.getCell_number()});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    /**
     * 导入页面初始化
     * @return
     */
    public String importDriverBefore() {
        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }
    
    /**
     * 获取文件内容错误
     * @return
     */
    private String getFileContentError(List < IEErrorMessage > list) {
        String errMsg = "";
        if (list.size() == 0) {
            return "";
        }
        for (IEErrorMessage tmp : list) {
            String msg = String.format("行:%s 列:%s 错误[%s]", tmp.getRow(), tmp
                    .getCol(), tmp.getMessage());
            errMsg = errMsg.concat(msg);
        }

        if (errMsg.length() > 150) {
            errMsg = errMsg.substring(0, 150);
            errMsg = errMsg.concat("......");
        }

        return errMsg;
    }

    /**
     * 获取文件内容列表
     * @return
     */
    private List < DriverInfo > formatDriverInfos(List < DriverInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        List < DriverInfo > ret = new ArrayList < DriverInfo >();

        for (DriverInfo tmp : list) {
            if (tmp.getDriver_name() != null
                    && tmp.getDriver_name().length() == 0
                    && tmp.getDriver_license() != null
                    && tmp.getDriver_license().length() == 0
                    && tmp.getDriver_sex() != null
                    && tmp.getDriver_sex().length() == 0
                    && tmp.getDriver_card() != null
                    && tmp.getDriver_card().length() == 0
                    && tmp.getDriver_birth() != null
                    && tmp.getDriver_birth().length() == 0
                    && tmp.getCell_number() != null
                    && tmp.getCell_number().length() == 0
                    && tmp.getDriver_address() != null
                    && tmp.getDriver_address().length() == 0
                    && tmp.getRemarks() != null
                    && tmp.getRemarks().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                // 创建用户ID
                tmp.setCreater(currentUser.getUserID());
                // 修改用户ID
                tmp.setModifier(currentUser.getUserID());
                tmp.setEnterprise_id(currentUser.getEntiID());
                tmp.setOrganization_id(currentUser.getOrganizationID());
                // 添加到list中
                ret.add(tmp);
            }
        }

        return ret;
    }

    /**
     * 导入驾驶员信息
     * @return
     */
    public String importDriver() {
        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            return ERROR;
        }

        List < DriverInfo > list = new ArrayList < DriverInfo >();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            IEExcelImporter excelImplortor = null;
            try {
                excelImplortor = new IEExcelImporter(fis);
            } catch (Exception e) {
                setMessage("file.import.error");
                log.error("Import file error:" + e.getMessage());
            }

            list = excelImplortor.getSheetData("importDriver", 0);

            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                addActionError(errMessage);
                return ERROR;
            }
        } catch (Exception e) {
            setMessage(getText("file.import.error"));
            log.error("Import file error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 格式化数据
        list = formatDriverInfos(list);

        if (list.size() == 0) {
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            return ERROR;
        } else {
            try {
                // 导入驾驶员信息
                driverManageService.importDriverInfos(list);
                setMessage("file.import.success");
            } catch (BusinessException e) {
                addActionError(getText("info.db.error"));
                log.error("Import driver error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                addActionError(getText("info.db.error"));
                log.error("Import driver error:" + e.getMessage());
                return ERROR;
            } finally {
                // 设置操作描述
                this.addOperationLog(formatLog("导入驾驶员信息", null));
                // 设置操作类型
                this.setOperationType(Constants.IMPORT);
                // 设置所属应用系统
                this.setApplyId(Constants.CLW_P_CODE);
                // 设置所属模块
                this.setModuleId(MouldId.XCP_DRIVERMANAGE_IMPORT);
            }
        }

        return SUCCESS;
    }
    
    /**
     * 导出驾驶员信息
     * @return
     */
    public String exportDriver() {
        List < DriverInfo > list = new ArrayList < DriverInfo >();
        try {
            UserInfo user = getCurrentUser();
            
            if (null == driverInfo) {
                driverInfo = new DriverInfo();
            }
            driverInfo.setEnterprise_id(user.getEntiID());
            if(null == driverInfo.getOrganization_id() || driverInfo.getOrganization_id().length() == 0) {
                driverInfo.setOrganization_id(user.getOrganizationID());
            }
            
            driverInfo.setDriver_name(SearchUtil.formatSpecialChar(driverInfo.getDriver_name()));
            driverInfo.setDriver_license(SearchUtil.formatSpecialChar(driverInfo.getDriver_license()));
            driverInfo.setDriver_card(SearchUtil.formatSpecialChar(driverInfo.getDriver_card()));
            
            list = (List < DriverInfo >) service.getObjects(
                    "DriverManage.exportDriverInfos", driverInfo);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export driver error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export driver error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "Driver.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("驾驶员信息");

            excelExporter.putAutoExtendSheets("exportDriver", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export driver error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 设置下载文件属性
        HttpServletResponse response = ServletActionContext.getResponse();
        response
                .setHeader("Content-disposition", "attachment;filename=DriverInfo-" + DateUtil.getCurrentDayTime() + ".xls");
        response.setContentType("application/msexcel; charset=\"utf-8\"");

        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export driver error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export driver error:" + e.getMessage());
            return null;
        } finally {
            // 关闭流
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    ;
                }
            }
            // 设置操作描述
            this.addOperationLog(formatLog("导出驾驶员信息", null));
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_DRIVERMANAGE_EXPORT);
        }
        // 导出文件成功
        return null;
        
    }
    
    
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
}
