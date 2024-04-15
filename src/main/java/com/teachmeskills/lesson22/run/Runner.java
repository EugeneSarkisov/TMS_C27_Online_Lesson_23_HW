package com.teachmeskills.lesson22.run;

import com.teachmeskills.lesson22.dom_parser.DomParser;
import com.teachmeskills.lesson22.sax_parser.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        File file = new File("Test.xml");
        System.out.print("1 - DOM; 2 - SAX ");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if (i == 1) {
            try {
                DomParser.parseXML(file);
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        } else if(i == 2){
            Parser.parseXMLWithSAX(file);
        } else{
            System.out.println("Input mismatch!");
        }
    }
}
