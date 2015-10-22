package com.yutong.clw.nio.msg.req;

import com.yutong.clw.nio.msg.up.InsideMsg;
import com.yutong.clw.nio.msg.util.InsideMsgUtils;


/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-11-28 上午09:56:05
 */
public class BindReq extends InsideMsg {

    public static final String COMMAND = "0001";

    public static final String STATUS = "0000";

    public static final int TIMESIZE = 14;

    public static final int SYSTEMIDSIZE = 16;

    public static final int PASSWORDSIZE = 16;

    public static final int MD5CODESIZE = 32;

    private String time;

    private String systemId;

    private String password;

    public void setTime(String time) {
        this.time = (time == null || time.equals("")) ? null : InsideMsgUtils.formatTime(time
                .trim());
    }

    public String getTime() {
        return time;
    }

    public void setSystemId(String systemId) {
        this.systemId = (systemId == null || systemId.equals("")) ? null : InsideMsgUtils
                .formatSystemId(systemId);
    }

    public String getSystemId() {
        return systemId;
    }

    private String getMd5Code() {
        String content = getTime() + getSystemId() + getPassword();
//        System.out.println(content);
        String md5 = InsideMsgUtils.getMd5(content);
//        System.out.println(md5);
//        System.out.println(InsideMsgUtils.formatMd5Code(md5));
        return md5 == null ? null : InsideMsgUtils.formatMd5Code(md5);
    }

    public void setPassword(String password) {
        this.password = (password == null || password.equals("")) ? null : InsideMsgUtils.formatPassword(password);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public byte[] getBytes() {
        int location = 0;
        byte[] buf = new byte[Integer.parseInt(this.getMsgLength())];
        byte[] header = super.getBytes();
        System.arraycopy(header, 0, buf, location, MSGHEADERSIZE);
        location += MSGHEADERSIZE;
        System.arraycopy(this.getTime().getBytes(), 0, buf, location, TIMESIZE);
        location += TIMESIZE;
        System.arraycopy(this.getSystemId().getBytes(), 0, buf, location, SYSTEMIDSIZE);
        location += SYSTEMIDSIZE;
        System.arraycopy(this.getMd5Code().getBytes(), 0, buf, location, MD5CODESIZE);
//        System.out.println(this.getMd5Code());
        return buf;
    }
    
    public String toString(){
    	return new String(getBytes());
    }
}
