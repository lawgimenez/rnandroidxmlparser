package com.lawgimenez.rnandroidxmlparser;

import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Lawrence Gimenez on 11/1/16.
 */
public class XMLParserModule extends ReactContextBaseJavaModule {

    private static final String TAG = "XMLParserModule";

    private ArrayList<String> mListXmlTexts;

    private String mText = "";

    public XMLParserModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);

        mListXmlTexts = new ArrayList<>();
    }

    @Override
    public String getName() {
        return "RNAndroidXMLParser";
    }

    @ReactMethod
    public void parse(String xmlResponse, Callback callbackResponse) {
        // Before sending results to Javascript callback, make sure you clean up the existing
        // list in memory
        if(!mListXmlTexts.isEmpty()) {
            mListXmlTexts.clear();
        }

        try {
            XmlPullParserFactory xmlParserFactory = XmlPullParserFactory.newInstance();
            xmlParserFactory.setNamespaceAware(true);

            XmlPullParser xmlParser = xmlParserFactory.newPullParser();
            xmlParser.setInput(new StringReader(xmlResponse));

            int eventType = xmlParser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_DOCUMENT) {
                    Log.i(TAG, "Start Doc");

                } else if(eventType == XmlPullParser.START_TAG) {
                    Log.i(TAG, "Start Tag");

                } else if(eventType == XmlPullParser.END_TAG) {
                    Log.i(TAG, "End Tag");

                } else if(eventType == XmlPullParser.TEXT) {
                    Log.i(TAG, "Text: " + xmlParser.getText());

                    mText = xmlParser.getText();
                    mListXmlTexts.add(mText);
                }

                eventType = xmlParser.next();
            }

            callbackResponse.invoke(mListXmlTexts.toString());

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
