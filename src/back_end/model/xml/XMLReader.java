package back_end.model.xml;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import back_end.exceptions.XMLException;

public class XMLReader implements XMLInterface{
	
	public <T> T readCustomObject(String name, Class<T> clazz) throws XMLException{
		XMLDecoder mDecoder = null;
		try{
			mDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(XMLInterface.XML_ADDRESS + name)));
		} catch(FileNotFoundException e){
			throw new XMLException("File is not found: " + name	);
		}
		T customObject = clazz.cast(mDecoder.readObject());
		mDecoder.close();
		return customObject;
	}

}
