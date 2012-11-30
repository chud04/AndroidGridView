package com.example.listimageandroid;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CountryParser {
	private static String tag = "CountryParser";
	private static String FILE_EXTENSION = ".png";
	
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private final List<Country> list;
	
	public CountryParser() {
		list = new ArrayList<Country>();
	}
	
	private String getNodeValue(NamedNodeMap map, String key) {
		String nodeValue = null;
		
		Node node = map.getNamedItem(key);
		if(node != null) {
			nodeValue = node.getNodeValue();
		}
		
		return nodeValue;
	}
	
	public List<Country> getList() { return list; }
	
	public void parse(InputStream inputStream) {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			builder.isValidating();
			Document doc = builder.parse(inputStream, null);
			
			doc.getDocumentElement().normalize();
			
			NodeList countryList = doc.getElementsByTagName("country");
			final int length = countryList.getLength();
			
			for(int i = 0; i < length; i++) {
				final NamedNodeMap attr = countryList.item(i).getAttributes();
				final String countryName = getNodeValue(attr, "name");
				final String countryAbbreviation = getNodeValue(attr, "abbreviation");
				final String countryRegion = getNodeValue(attr, "region");
				
				Country country = new Country(countryName, countryAbbreviation, countryRegion, countryAbbreviation + FILE_EXTENSION);
				
				list.add(country);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
