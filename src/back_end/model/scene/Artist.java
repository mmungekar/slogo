package back_end.model.scene;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import back_end.exceptions.NotInMapException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/**
 * By Miguel Anderson
 */
public class Artist {
	public static final int DEFAULT_BACKGROUND_COLOR_INDEX = 0;
	public static final List<String> DEFAULT_COLOR_HTML_NAMES = Arrays.asList("white", "black", "blue", "red", "green",
			"yellow", "orange");

	private HashMap<Integer, Color> colorContainer;
	private HashMap<Integer, Image> shapeContainer;

	private Color backgroundColor;
	
	private Model myModel;

	public Artist(Model model) {
		this.myModel = model;
		setBackgroundColor(Color.WHITE);
		createDefaultColors();
		createDefaultShapes();
	}
	
	private void notifyModel(){
		myModel.setChangedAndNotifyObservers();
	}

	private void createDefaultShapes() {
		shapeContainer = new HashMap<Integer, Image>();
		for (int i = 0; i < DEFAULT_COLOR_HTML_NAMES.size(); i++) {
			// shapeContainer.put(i,
			// Color.web(DEFAULT_COLOR_HTML_NAMES.get(i)));
		}
	}
	
	public void setBackgroundColor(int index) {
		setBackgroundColor(colorContainer.get(index));
		notifyModel();
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		notifyModel();
	}

	public void setColorRGB(int index, int r, int g, int b) {
		colorContainer.put(index, Color.rgb(r, g, b));
	}

	private void createDefaultColors() {
		colorContainer = new HashMap<Integer, Color>();
		for (int i = 0; i < DEFAULT_COLOR_HTML_NAMES.size(); i++) {
			colorContainer.put(i, Color.web(DEFAULT_COLOR_HTML_NAMES.get(i)));
		}
		this.setBackgroundColor(colorContainer.get(DEFAULT_BACKGROUND_COLOR_INDEX));
	}

	public Double getIndexFromColor(Color penColor) throws NotInMapException {
		return getKeyFromValue(colorContainer, penColor).doubleValue();
	}

	private static <K, V> K getKeyFromValue(HashMap<K, V> map, V value) throws NotInMapException {
		for (K k : map.keySet()) {
			if (map.get(k).equals(value)) {
				return k;
			}
		}
		throw new NotInMapException(value.toString());
	}

	public Color getColorFromIndex(Integer index) {
		return colorContainer.get(index);
	}

	public Image getShapeFromIndex(Integer index) {
		return shapeContainer.get(index);
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public Double getIndexFromShape(Image shape) throws NotInMapException {
		return getKeyFromValue(shapeContainer, shape).doubleValue();
	}

}
