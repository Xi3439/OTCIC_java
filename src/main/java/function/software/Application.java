package function.software;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;


import java.util.ArrayList;
import java.util.List;

public class Application {
    final private String name;
    private OSProcess process;
    private ArrayList<Object> childProcessIDs;
    private double cpuUsage;

    SystemInfo systemInfo = new SystemInfo();
    OperatingSystem os = systemInfo.getOperatingSystem();

    public Application(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private OSProcess getProcess(){
        for (OSProcess process : os.getProcesses()) {
            if (process.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(process.getName().toLowerCase())) {
                this.process = process;
            }
        }
        return process;
    }

    public int getProcessId() {
        process = getProcess();
        return process.getProcessID();
    }


    public ArrayList<Object> getChildProcessIDs(){
        int parentProcessID = getProcessId();
        List<OSProcess> childProcesses = os.getChildProcesses(parentProcessID, null, null, 0);
        for(OSProcess childProcess:childProcesses){
            int childProcessID = childProcess.getProcessID();
            childProcessIDs.add(childProcessID);
        }
        return childProcessIDs;
    }

    public double getCPUUsage(){
        process = getProcess();
        return 100d * (process.getKernelTime() + process.getUserTime()) / process.getUpTime();
    }

    public double getMemoryUsage() {
        process = getProcess();
        int processID = getProcessId();
        double memoryUsage = process.getResidentSetSize();
        List<OSProcess> childProcesses = os.getChildProcesses(processID, null, null, 0);
        if(childProcesses.size()>0){
            for(OSProcess childProcess:childProcesses){
                memoryUsage += childProcess.getResidentSetSize();
            }
        }
        return memoryUsage / 1024.0 / 1024.0;
    }

    public ArrayList<Object> getChildProcessesUsage(){
       ArrayList<Object> childProcessUsage = new ArrayList<>();
        int parentProcessID = getProcessId();
        List<OSProcess> childProcesses = os.getChildProcesses(parentProcessID, null, null, 0);
        if(childProcesses.size()>0){
            for(OSProcess childProcess:childProcesses){
                childProcessUsage.add(100d * (childProcess.getKernelTime() + childProcess.getUserTime()) / childProcess.getUpTime());
            }
        }
        return childProcessUsage;
    }

//    public double getDiskUsage(){
//        process = getProcess();
//        long bytesRead = process.getBytesRead();
//        long bytesWritten = process.getBytesWritten();
//        return (bytesRead + bytesWritten) / (diskBandwidth * timeInterval);
//    }



//    public ArrayList<Object> getProcessIDs() {
//
//        return ProcessIDs;
//    }

//    public ArrayList<Object> GetProcessIDs() {
//        SystemInfo systemInfo = new SystemInfo();
//        HardwareAbstractionLayer hardware = systemInfo.getHardware();
//        CentralProcessor processor = hardware.getProcessor();
//        OperatingSystem os = systemInfo.getOperatingSystem();
//
//        for (OSProcess process : os.getProcesses()) {
//            if (process.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(process.getName().toLowerCase())) {
//                int pid = process.getProcessID();
//                List<OSProcess> childProcesses = os.getChildProcesses(pid, null, null, 0);
//                String groupPid = process.getGroupID();
//                System.out.println("this is group id:" + groupPid);
////                int parentPid = process.getParentProcessID();
////                OSProcess parentProcess = operatingSystem.getProcess(parentPid);
//                ProcessIDs.add(pid);
//                double cpuUsage = 100d * (process.getKernelTime() + process.getUserTime()) / process.getUpTime();// CPU占用率（百分比）
//                double cpuUsage2 = process.getProcessCpuLoadCumulative();
////                double ParentCpuUsage = 100d * (parentProcess.getKernelTime() + parentProcess.getUserTime()) / parentProcess.getUpTime();// CPU占用率（百分比）
//                System.out.println(process.getParentProcessID());
//                System.out.println("PID: " + pid);
//                System.out.println("CPU usage: " + cpuUsage + "%");
//                System.out.println("CPU usage2: " + cpuUsage2 * 100 + "%");
//                if (childProcesses.size() != 0) {
//                    for (OSProcess childProcess : childProcesses) {
//                        int childPid = childProcess.getProcessID();
//                        String childProcessName = childProcess.getName();
//                        if (process.getName().equals("System Idle Process")) {
//                            continue;
//                        }
//                        double childCpuUsage = 100d * (childProcess.getKernelTime() + childProcess.getUserTime()) / childProcess.getUpTime();// CPU占用率（百分比）
//                        System.out.println("this is child name:" + childProcessName);
//                        System.out.println("this is child pid:" + childPid);
//                        System.out.println("this is the cpu usage:" + childCpuUsage);
//                    }
//                }
////                System.out.println("PARENT PID: " + parentPid);
////                System.out.println("PARENT CPU usage: " + ParentCpuUsage + "%");
//                return ProcessIDs;
//            }
//        }
//        return ProcessIDs;
    }