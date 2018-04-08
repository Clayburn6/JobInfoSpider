package com.pgb.spider.wechat.xom;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author Clayburn
 * @date : 2018/4/1 22:39
 * @description
 */
public class JAXBUtils {
    public final static String CHARSET_NAME = "utf-8";
    private static StringWriter writer = new StringWriter();
    private static StringReader reader = null;

    public static String marshal(Object obj) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, CHARSET_NAME);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // 去掉报头
        jaxbMarshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler",
                new CharacterEscapeHandler(){
                    @Override
                    public void escape(char[] ch, int start,
                                       int length, boolean isAttVal,
                                       Writer writer) throws IOException
                    {
                        writer.write(ch, start, length);
                    }
                });

        writer.flush();
        jaxbMarshaller.marshal(obj, writer);
        return writer.toString();
    }

    public static <T> T unmarshal(String xml, Class<T> cls) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(cls);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            try{
            reader = new StringReader(xml);
            return (T) jaxbUnmarshaller.unmarshal(reader);
        } finally {
            reader.close();
        }
    }
}
