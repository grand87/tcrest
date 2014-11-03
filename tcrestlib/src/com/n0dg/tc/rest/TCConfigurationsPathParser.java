package com.n0dg.tc.rest;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.n0dg.tc.rest.TCDocumentParserFactory.DocumentParser;

public class TCConfigurationsPathParser implements DocumentParser {

	static final String TC_RESOURCE = "buildTypes";

	@Override
	public Map<String, String> parse(Document aDoc) {
		Map<String, String> res = null;
		
		if(aDoc.hasChildNodes()){
			res = new HashMap<String, String>();
			
			//find projects node
			Node node = DomDocHelper.findRootNode(aDoc.getChildNodes(), TC_RESOURCE);
			if (node != null && node.hasChildNodes()) {
				// iterate over project items
				NodeList nodeList = node.getChildNodes();
				for (int count = 0; count < nodeList.getLength(); ++count) {

					Node tempNode = nodeList.item(count);
					if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
						NamedNodeMap attrs = tempNode.getAttributes();
						res.put(attrs.getNamedItem("id").getNodeValue(), attrs.getNamedItem("name").getNodeValue());
					}
				}
			}
		}
		return res;
	}

}
