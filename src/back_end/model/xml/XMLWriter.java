package back_end.model.xml;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import back_end.exceptions.XMLException;


public class XMLWriter implements XMLInterface{
	
	public XMLWriter(){
	}
	
	public void writeCustomObject(String name, Object obj) throws XMLException{
		XMLEncoder mEncoder = null;
		try {
			mEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(XMLInterface.XML_ADDRESS + name)));
		} catch (FileNotFoundException e) {
			throw new XMLException("Fail to create to open the file: " + name);
		}
		mEncoder.writeObject(obj);
		mEncoder.close();
	}

}
