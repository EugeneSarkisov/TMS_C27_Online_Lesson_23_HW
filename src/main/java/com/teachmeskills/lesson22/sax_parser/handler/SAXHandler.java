package com.teachmeskills.lesson22.sax_parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SAXHandler extends DefaultHandler {

    boolean bLastName = false;
    boolean bFirstName = false;
    boolean bTitle = false;
    boolean bLine = false;
    StringBuilder data = null;
    File sonnet = null;
    String fileName;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("lastName")){
            bLastName = true;
        } else if(qName.equalsIgnoreCase("firstName")){
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("title")){
            bTitle = true;
        } else if (qName.equalsIgnoreCase("line")) {
            bLine = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(bLastName){
            fileName = data.toString() + "_";
            bLastName = false;
        } else if (bFirstName) {
            fileName = data.toString() + "_" + fileName;
            bFirstName = false;
        } else if (bTitle) {
            fileName += data.toString();
            sonnet = new File("src/" + fileName + ".txt");
            bTitle = false;
        } else if (bLine){
            try {
                FileWriter fw = new FileWriter(sonnet, true);
                fw.write(data.toString() + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bLine = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
