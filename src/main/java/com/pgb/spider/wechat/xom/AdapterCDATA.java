package com.pgb.spider.wechat.xom;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Clayburn
 * @date : 2018/4/1 17:13
 * @description
 */
public class AdapterCDATA extends XmlAdapter<String, String> {
    @Override
    public String marshal(String arg0) throws Exception
    {
        return "<![CDATA[" + arg0 + "]]>";
    }

    @Override
    public String unmarshal(String arg0) throws Exception
    {
        return arg0;
    }
}
