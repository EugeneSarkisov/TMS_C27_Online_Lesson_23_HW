package com.teachmeskills.lesson22.dom_parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class DomParser {
    public static void parseXML(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        //getting first part of document name
        NodeList nodeList = document.getElementsByTagName("sonnet");
        Node node = nodeList.item(0);
        nodeList = node.getChildNodes();
        node = nodeList.item(1);
        String fileNamePref = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            fileNamePref = element.getElementsByTagName("firstName").item(0).getTextContent() + "_" +
                    element.getElementsByTagName("lastName").item(0).getTextContent() + "_";
        }
        //getting second part of document name and creating .txt file
        node = nodeList.item(3);
        File sonnet = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            sonnet = new File("src/" + fileNamePref + element.getTextContent() + ".txt");
        }
        //getting lines of sonnet
        node = nodeList.item(5);
        nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            FileWriter fw = new FileWriter(sonnet, true);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                fw.write(element.getTextContent() + "\n");
            }
            fw.close();
        }
    }
}
