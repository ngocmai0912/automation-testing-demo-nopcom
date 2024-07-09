package commons;

import java.io.File;

public class GlobalConstants {
    public static final long LONG_TIMEOUT = 20;
    public static final long SHORT_TIMEOUT = 5;
    public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
    public static final String DATA_TEST_PATH = RELATIVE_PROJECT_PATH + File.separator + "dataTest" + File.separator;
}

