package com.teachmeskills.lesson22.sax_parser;

import com.teachmeskills.lesson22.sax_parser.handler.SAXHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Parser {
    public static void parseXMLWithSAX(File file){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            saxParser.parse(file, handler);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
