package com.n0dg.tc.rest;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomDocHelper {

	static Node findRootNode(NodeList aNodeList, String nodeName) {
		for (int count = 0; count < aNodeList.getLength(); ++count) {

			Node tempNode = aNodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				if (tempNode.getNodeName().equals(nodeName)) {
					return tempNode;
				}
			}
		}
		return null;
	}
}
