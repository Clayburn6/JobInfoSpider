package com.pgb.spider.wechat.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * dom解析工具
 */
public class DomUtils {
    private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public static String selectXmlByTagName(String xml, String tagName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

        NodeList nodeList = doc.getElementsByTagName(tagName);

        if (nodeList == null || nodeList.getLength() <= 0) {
            throw new RuntimeException();
        }

        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
