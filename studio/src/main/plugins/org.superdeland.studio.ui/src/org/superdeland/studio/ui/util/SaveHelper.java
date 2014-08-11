package org.superdeland.studio.ui.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.Location;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.core.models.Size;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SaveHelper {

	private static final String ELEMENT = "element";
	private static final String NAME = "name";
	private static final String SIZE = "size";
	private static final String LOCATION = "location";
	private static final String TARGET = "target";
	private static final String SOURCE = "source";
	private static final String ID = "id";
	private static final String RELATION = "relation";
	private static final String DIAGRAM = "diagram";

	public static void saveModel(DiagramModel diagramModel, IFile file) {
		try {
			Document document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			Element rootElement = document.createElement(DIAGRAM);
			document.appendChild(rootElement);

			List<ElementModel> children = diagramModel.getChildren();
			List<RelationModel> relations = new ArrayList<RelationModel>();
			for (ElementModel child : children) {
				relations.addAll(saveElement(child, rootElement, document));
			}
			for (RelationModel relation : relations) {
				saveRelation(relation, rootElement, document);
			}
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(file.getLocation()
					.toFile());
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount",
					String.valueOf(4));
			transformer.transform(domSource, streamResult);
			file.refreshLocal(IResource.DEPTH_INFINITE,
					new NullProgressMonitor());
		} catch (ParserConfigurationException | TransformerException
				| CoreException e) {
			e.printStackTrace();
		}
	}

	private static void saveRelation(RelationModel relation,
			Element rootElement, Document document) {
		Element element = document.createElement(RELATION);
		element.setAttribute(ID, relation.getId());
		element.setAttribute(SOURCE, relation.getSource().getId());
		element.setAttribute(TARGET, relation.getTarget().getId());
		rootElement.appendChild(element);

	}

	private static List<RelationModel> saveElement(ElementModel child,
			Element rootElement, Document document) {
		Element element = document.createElement(ELEMENT);
		element.setAttribute(ID, child.getId());
		element.setAttribute(NAME, child.getName());
		element.setAttribute(SIZE, child.getSize().getWidth() + ","
				+ child.getSize().getHeight());
		element.setAttribute(LOCATION, child.getLocation().getX() + ","
				+ child.getLocation().getY());
		rootElement.appendChild(element);
		return child.getSources();

	}

	public static DiagramModel loadModel(IFile file) {
		try (InputStream is = file.getContents()) {
			Document document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);
			DiagramModel diagramModel = new DiagramModel();

			XPath xpath = XPathFactory.newInstance().newXPath();
			loadElements(document, diagramModel, xpath);
			loadConnections(document, diagramModel, xpath);

			return diagramModel;
		} catch (SAXException | IOException | ParserConfigurationException
				| CoreException | XPathExpressionException e) {
		}
		return new DiagramModel();
	}

	private static void loadConnections(Document document,
			DiagramModel diagramModel, XPath xpath)
			throws XPathExpressionException {
		NodeList relations = (NodeList) xpath.evaluate("//" + RELATION,
				document, XPathConstants.NODESET);
		int length = relations.getLength();

		List<ElementModel> elements = diagramModel.getChildren();

		for (int i = 0; i < length; i++) {
			Element item = (Element) relations.item(i);

			RelationModel relationModel = new RelationModel();
			relationModel.setId(item.getAttribute(ID));

			String sourceId = item.getAttribute(SOURCE);
			String targetId = item.getAttribute(TARGET);

			for (ElementModel element : elements) {
				if (sourceId.equals(element.getId())) {
					relationModel.setSource(element);
					relationModel.attachSource();
				} else if (targetId.equals(element.getId())) {
					relationModel.setTarget(element);
					relationModel.attachTarget();
				}
			}
		}
	}

	private static void loadElements(Document document,
			DiagramModel diagramModel, XPath xpath)
			throws XPathExpressionException {
		NodeList elements = (NodeList) xpath.evaluate("//" + ELEMENT, document,
				XPathConstants.NODESET);
		int length = elements.getLength();
		for (int i = 0; i < length; i++) {
			Element item = (Element) elements.item(i);
			ElementModel elementModel = new ElementModel();
			elementModel.setId(item.getAttribute(ID));
			elementModel.setName(item.getAttribute(NAME));

			String[] size = item.getAttribute(SIZE).split(",");
			elementModel.setSize(new Size(Integer.parseInt(size[0]), Integer
					.parseInt(size[1])));

			String[] location = item.getAttribute(LOCATION).split(",");
			elementModel.setLocation(new Location(
					Integer.parseInt(location[0]), Integer
							.parseInt(location[1])));

			diagramModel.addNode(elementModel);
		}
	}

}
