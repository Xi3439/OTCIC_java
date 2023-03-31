package function.appplication;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RuntimePid {
    public static int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); //format: pid@hostname
        return Integer.parseInt(name.substring(0, name.indexOf('@')));
    }
}
