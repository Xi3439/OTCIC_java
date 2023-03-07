package function;

import function.software.Application;

import java.util.ArrayList;

public class SystemInfo {
    public static void main(String[] args) throws InterruptedException {
//        ManufactureInfo manufactureInfo = new ManufactureInfo();
//        CpuInfo cpuInfo = new CpuInfo();
//        System.out.println(manufactureInfo.getManufacturer());
//        System.out.println(manufactureInfo.getModel());
//        System.out.println(manufactureInfo.getSerialNumber());
//        System.out.println(cpuInfo.getModel());
        Application application = new Application("idea");
//        System.out.println(application.getCPUUsage(0)/12);
//        application.GetProcessIDs();
        System.out.println("this is process id:" + application.getProcessId());
        System.out.println("this is process CPU usage:" + (float) application.getCPUUsage());
        ArrayList<Object> childInfos = application.getChildCPUUsage();
        System.out.println(childInfos);
        System.out.println("this is process memory usage:" + (float) application.getMemoryUsage());
    }

}
