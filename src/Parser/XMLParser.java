/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Global.Interval;
import Tasks.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Tasks.*;
/**
 *
 * @author ASSO1011D
 */
public class XMLParser {

    
    public List<Component> loadDatabase(){
		List<Component> database = new ArrayList<Component>();

		try {

			File fXmlFile = new File("database.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			Node root = (Node) doc.getDocumentElement();
			System.out.println("Root element :" + root.getNodeName());

			System.out.println("-----------------------");

			NodeList nList = root.getChildNodes();

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);	    
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if(eElement.getNodeName().equals("job")){
						Job job = createJob(eElement);
						database.add(job);

					}
					else if(eElement.getNodeName().equals("task")){
						Task task = new Task(eElement.getAttribute("name"), eElement.getAttribute("description"), eElement.getAttribute("state"));
						NodeList intervals = eElement.getChildNodes();

						for(int i = 0; i < intervals.getLength(); i++){
							Node interval = intervals.item(i);	    
							if (interval.getNodeType() == Node.ELEMENT_NODE){
								Element intvl = (Element) interval;

								Interval newInterval = new Interval(intvl.getAttribute("start"),intvl.getAttribute("end"));
								task.getIntervals().add(newInterval);
							}
						}
						database.add(task);
					}
					else if(eElement.getNodeName().equals("action")){
						Action action = new Action(eElement.getAttribute("name"), eElement.getAttribute("description"), eElement.getAttribute("state"));
						database.add(action);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
                
                return database;
	}

    public Job createJob(Element eElement) {
        Job job = new Job(eElement.getAttribute("name"),eElement.getAttribute("description"));

        if(eElement.hasChildNodes()){
                NodeList list = eElement.getChildNodes();

                for(int i = 0; i < list.getLength(); i++){
                        Node nNode = list.item(i);	    
                        if (nNode.getNodeType() == Node.ELEMENT_NODE){
                                Element child = (Element) nNode;

                                if(child.getNodeName().equals("job")){
                                        Job childjob = createJob(child);
                                        job.addChild(childjob);

                                }
                                else if(child.getNodeName().equals("task")){
                                        Task task = new Task(child.getAttribute("name"), child.getAttribute("description"), child.getAttribute("state"));
                                        NodeList intervals = child.getChildNodes();

                                        for(int j = 0; j < intervals.getLength(); j++){
                                                Node interval = intervals.item(j);	    
                                                if (interval.getNodeType() == Node.ELEMENT_NODE){
                                                        Element intvl = (Element) interval;

                                                        Interval newInterval = new Interval(intvl.getAttribute("start"),intvl.getAttribute("end"));
                                                        task.getIntervals().add(newInterval);
                                                }
                                        }
                                        job.addChild(task);
                                }
                                else if(child.getNodeName().equals("action")){
                                        Action action = new Action(child.getAttribute("name"), child.getAttribute("description"), child.getAttribute("state"));
                                        job.addChild(action);
                                }
                        }
                }
        }

        return job;
}

    public void saveDatabase(List<Component> database){
        try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder;

                docBuilder = docFactory.newDocumentBuilder();

                //root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("taskmania");
                doc.appendChild(rootElement);

                prepareXML(doc,database, rootElement);
                
                //write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result =  new StreamResult(new File("database.xml"));
                transformer.transform(source, result);

                System.out.println("Done");
        } catch (ParserConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (TransformerConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (TransformerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }

    public void prepareXML(Document doc, List<Component> list, Element parent){
        for(Component cp : list){
            if(cp instanceof Job){
                    Element job = doc.createElement("job");
                    Attr name = doc.createAttribute("name");
                    name.setValue(((Job) cp).getName());
                    Attr description = doc.createAttribute("description");
                    description.setValue(((Job) cp).getDescription());
                    job.setAttributeNode(name);
                    job.setAttributeNode(description);
                    parent.appendChild(job);

                    prepareXML(doc, ((Job) cp).getChildren(), job);
            }
            else if(cp instanceof Task){
                    prepareTask(doc, (Task) cp, parent);

            }
            else if(cp instanceof Action){
                    Element action = doc.createElement("action");
                    Attr name = doc.createAttribute("name");
                    name.setValue(((Action) cp).getName());
                    Attr description = doc.createAttribute("description");
                    description.setValue(((Action) cp).getDescription());
                    Attr state = doc.createAttribute("state");
                    state.setValue(((Action) cp).getState());
                    action.setAttributeNode(name);
                    action.setAttributeNode(state);
                    action.setAttributeNode(description);
                    parent.appendChild(action);
            }
        }
    }
    
    public Element prepareTask(Document doc, Task t, Element parent){
        Element task = doc.createElement("task");
        Attr name = doc.createAttribute("name");
        name.setValue(t.getName());
        Attr state = doc.createAttribute("state");
        state.setValue(t.getState());
        Attr description = doc.createAttribute("description");
        description.setValue(t.getDescription());

        task.setAttributeNode(name);
        task.setAttributeNode(state);
        task.setAttributeNode(description);

        parent.appendChild(task);

        for(Interval i : t.getIntervals()){
                Element interval = doc.createElement("interval");
                Attr start = doc.createAttribute("start");
                start.setValue(String.valueOf(i.startTime.getTimeInMillis()));
                Attr end = doc.createAttribute("end");
                end.setValue(String.valueOf(i.endTime.getTimeInMillis()));

                interval.setAttributeNode(start);
                interval.setAttributeNode(end);

                task.appendChild(interval);
        }

        return task;
    }   
}
