package pers.xkr.persistence.io;

import java.io.InputStream;

public class Resource {

    public static InputStream getStreamFromXmlFile(String fileName){
        InputStream resourceAsStream = Resource.class.getClassLoader().getResourceAsStream(fileName);
        return resourceAsStream;
    }


}
