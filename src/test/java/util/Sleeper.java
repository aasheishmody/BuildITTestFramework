package util;

import org.picocontainer.classname.ClassName;

import java.util.logging.Logger;

public class Sleeper {
    private static Logger logger = Logger.getLogger(ClassName.class.getName());

    private Sleeper() {
    }

    public static void sleep(Long millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.severe("Sleep has been interrupted:"+e.getMessage() );
        }
    }
}
