package function;

import function.hardware.CPUInfo;
import function.hardware.ManufactureInfo;
import function.software.Application;

import java.util.ArrayList;

public class SystemInfo {
    public static void main(String[] args) throws InterruptedException {
//        ManufactureInfo manufactureInfo = new ManufactureInfo();
//        CPUInfo cpuInfo = new CPUInfo();
//        System.out.println(manufactureInfo.getManufacturer());
//        System.out.println(manufactureInfo.getModel());
//        System.out.println(manufactureInfo.getSerialNumber());
//        System.out.println(cpuInfo.getModel());
        Application application = new Application("valorant");
//        application.GetProcessIDs();
        System.out.println("this is process id:" + application.getProcessId());
        System.out.println("this is process CPU usage:" + (float) application.getCPUUsage());
        ArrayList<Object> childInfos = application.getChildCPUUsage();
        System.out.println(childInfos);
        System.out.println("this is process memory usage:" + (float) application.getMemoryUsage());
    }

}
