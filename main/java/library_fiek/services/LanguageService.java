package library_fiek.services;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageService {
    private static String currentLanguage = "sq";
    private static ResourceBundle bundle = loadBundle(currentLanguage);

    public static void setLanguage(String languageCode) {
        currentLanguage = languageCode;
        bundle = loadBundle(languageCode);
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static String get(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            return key;
        }
    }

    private static ResourceBundle loadBundle(String languageCode) {
        Locale locale = new Locale(languageCode);
        return ResourceBundle.getBundle("library_fiek.lang.messages", locale);
    }
}
