/*******************************************************************************
 * @(#)CommunicateService.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.mina.interfaces;

import org.apache.mina.core.session.IoSession;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午02:21:21
 */
public interface CommunicateService {

    boolean connect() throws Exception;

    boolean reconnect() throws Exception;

    void close() throws Exception;

    void send(byte[] buf) throws Exception;

    void setSession(IoSession session);

    IoSession getSession();

    String getRemoteAddress();

    String getLocalAddress();

    void setAvailable(boolean isAvailable);

    boolean isAvailable();

}
