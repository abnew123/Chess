package frontend.screens;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
/**
 * a screen for the project. Displays as a window to the user
 * @author shichengrao
 *
 */
public interface Screen {
	public static final String STYLE_PATH = "/css/Screen.css";
	public static final String IMAGE_PATH = "/images/";
	public static final String TITLE_PROPERTY = "title";
	public static final String WIDTH_PROPERTY = "width";
	public static final String HEIGHT_PROPERTY = "height";
	
	public static final int INITIAL_SCENE_WIDTH = 1200;
	public static final int INITIAL_SCENE_HEIGHT = 700;
	public static final int RIGHT_TABS_WIDTH = 500;
	public static final int LEFT_TABS_WIDTH = 700;
	public static final int TABS_HEIGHT = 800;

	public static final String DEFAULT_LABEL = "default_authoring_label";
	public static final String DEFAULT_TITLE = "default_authoring_title";
	
	public static final Paint DEFAULT_BACKGROUND = Color.WHITE;
	public static final int PANEL_WIDTH = 700;
	public static final int PANEL_HEIGHT = 580;
	
	public static final int SPACING_SMALL = 10;
	public static final Insets LINE_INSETS = new Insets(10, 10, 10, 10);
}
