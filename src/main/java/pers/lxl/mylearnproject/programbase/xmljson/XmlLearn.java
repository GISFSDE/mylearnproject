package pers.lxl.mylearnproject.programbase.xmljson;




import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;

import org.w3c.dom.Node;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lxl
 * @Date: 2023/05/11/14:16
 * @Description: 读取解析xml
 */
public class XmlLearn {
    public static void main(String[] args) throws Exception {
//      DOM解析
//        parseXML();
//     使用SAX解析
//        parseXMLSAX();
//    Jackson  转为JavaBean
        File f = new File("D:\\PROJECTS\\MY\\mylearnproject\\src\\main\\java\\pers\\lxl\\mylearnproject\\programbase\\xmljson\\xmlLearn.xml");
//        InputStream input = XmlLearn.class.getClassLoader().getResourceAsStream(f);
        InputStream input =new FileInputStream(f);
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module);
        Book book = mapper.readValue(input, Book.class);
        System.out.println(book);
    }
    public static void parseXML() throws ParserConfigurationException, IOException, SAXException {
        File f = new File("D:\\PROJECTS\\MY\\mylearnproject\\src\\main\\java\\pers\\lxl\\mylearnproject\\programbase\\xmljson\\xmlLearn.xml");
        InputStream input =new FileInputStream(f);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(input);
        printNode((Node) doc, 0);
    }

    //遍历以读取指定元素的值：
    static void printNode(Node n, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(' ');
        }
        switch (n.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println("Document: " + n.getNodeName());
                break;
            case Node.ELEMENT_NODE:
                System.out.println("Element: " + n.getNodeName());
                break;
            case Node.TEXT_NODE:
                System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.ATTRIBUTE_NODE:
                System.out.println("Attr: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.CDATA_SECTION_NODE:
                System.out.println("CDATA: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.COMMENT_NODE:
                System.out.println("Comment: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            default:
                System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
        }
        for (Node child = (Node) n.getFirstChild(); child != null; child = (Node) child.getNextSibling()) {
            printNode(child, indent + 1);
        }
    }
    public static void parseXMLSAX() throws Exception {
        File f = new File("D:\\PROJECTS\\MY\\mylearnproject\\src\\main\\java\\pers\\lxl\\mylearnproject\\programbase\\xmljson\\xmlLearn.xml");
        InputStream input =new FileInputStream(f);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        saxParser.parse(input, new MyHandler());
    }}
    class MyHandler extends DefaultHandler {
        public void startDocument() throws SAXException {
            print("start document");
        }

        public void endDocument() throws SAXException {
            print("end document");
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            print("start element:", localName, qName);
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            print("end element:", localName, qName);
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            print("characters:", new String(ch, start, length));
        }

        public void error(SAXParseException e) throws SAXException {
            print("error:", e);
        }

        void print(Object... objs) {
            for (Object obj : objs) {
                System.out.print(obj);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


