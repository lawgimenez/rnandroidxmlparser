# React Native Android XML Parser

A React Native bridge/wrapper for parsing XML.

### Setup
Clone this repository and import it in your Android Studio project. Once imported, go to your project's build.gradle file and add 

- android/app/build.gradle
```gradle
dependencies {
    compile project(':rnandroidxmlparser')
}
```
Register module XMLParserReactPackage in your MainActivity.java
```java
public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .addPackage(new XMLParserReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
    }
}
```

### React Native Usage
```javascript
var AndroidXMLParser = require('rnandroidxmlparser');

var xmlResponse = "<?xml version=\"1.0\"?><!-- This is a comment --><address><name>Lars </name><street> Test </street><telephone number= \"0123\"/></address>";

AndroidXMLParser.parse(xmlResponse, (response) => {
    // Will return [Lars ,  Test ,  Test ,  Test ]
      console.log("XML response: " + response);
    });
```

### Updates/Changes
Please submit any pull requests to the development branch

### License
MIT