package function;

import function.hardware.CPUInfo;
import function.hardware.ManufactureInfo;
import function.software.Application;

public class SystemInfo {
    public static void main(String[] args) {
        ManufactureInfo manufactureInfo = new ManufactureInfo();
        CPUInfo cpuInfo = new CPUInfo();
        System.out.println(manufactureInfo.getManufacturer());
        System.out.println(manufactureInfo.getModel());
        System.out.println(manufactureInfo.getSerialNumber());
        System.out.println(cpuInfo.getModel());
        Application application = new Application("idea");
//        application.GetProcessIDs();
        System.out.println("this is process id:"+application.getProcessId());
        System.out.println("this is process CPU usage:"+(float)application.getCPUUsage());
        System.out.println("this is process memory usage:"+(float)application.getMemoryUsage());
    }
}