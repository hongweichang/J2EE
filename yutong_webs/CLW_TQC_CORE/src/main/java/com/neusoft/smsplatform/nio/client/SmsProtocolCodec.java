package com.neusoft.smsplatform.nio.client;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-11-19 下午03:17:20
 */
public class SmsProtocolCodec implements ProtocolCodecFactory {

    public ProtocolDecoder getDecoder() throws Exception {
        return null;
    }

    public ProtocolEncoder getEncoder() throws Exception {
        return null;
    }

}
