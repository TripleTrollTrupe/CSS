package css;

import java.util.Properties;

import javax.ejb.Singleton;

@Singleton
public class AppPropertiesServer {
	
	public final String APP_ROOT_NAME;
	public final String LIBRARY_NAME;
	public final String  RENTALS_SHELF_NAME;
	public final String  RENTALS_SHELF_GROUP_NAME;
	public final String RECENTLY_BORROWED_SHELF_NAME;

	private Properties appProperties;
	
	AppPropertiesServer() {
		String propertiesFileName = "/app.properties";
		appProperties = new Properties();
		try {
			appProperties.load(getClass().getResourceAsStream(propertiesFileName));
		} catch (Exception e) {
			// It was not able to load properties file.
			// Bad luck, proceed with the default values
		}
		APP_ROOT_NAME = parseString("app_root_name", "Lending E-media Initiative");
		LIBRARY_NAME = parseString("library_name", "Library");
		RENTALS_SHELF_NAME = parseString("rentals_shelf", "My Rentals");
		RENTALS_SHELF_GROUP_NAME = parseString("rentals_shelf_group", "Rentals");
		RECENTLY_BORROWED_SHELF_NAME = parseString("recently_shelf", "Recently Borrowed");
		
	}
	
	private String parseString(String property, String defaultValue) {
		try {
			return appProperties.getProperty(property);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

}
