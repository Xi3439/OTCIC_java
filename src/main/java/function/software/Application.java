package function.software;

import function.hardware.CpuInfo;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    final private String name;
    private ArrayList<Object> childProcessIDs;
    private double cpuUsage;

    SystemInfo systemInfo = new SystemInfo();
    OperatingSystem os = systemInfo.getOperatingSystem();
    CpuInfo cpuInfo = new CpuInfo();
    private final int cpuNumber = cpuInfo.getLogicalProcessorCount();

    public Application(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private List<OSProcess> getProcesses() {
        List<OSProcess> processes = new ArrayList<>();
        for (OSProcess process : os.getProcesses()) {
            if (process.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(process.getName().toLowerCase())) {
                processes.add(process);
            }
        }
        return processes;
    }

    public ArrayList<Integer> getProcessId() {
        List<OSProcess> processes = getProcesses();
        ArrayList<Integer> processID = new ArrayList<>();
        for (OSProcess osProcess : processes) {
            processID.add(getProcessId(osProcess));
        }
        return processID;
    }

    public int getProcessId(OSProcess process) {
        return process.getProcessID();
    }

    public ArrayList<Integer> getProcessId(List<OSProcess> processes) {
        ArrayList<Integer> processIDs = new ArrayList<>();
        for (OSProcess process : processes) {
            processIDs.add(process.getProcessID());
        }
        return processIDs;
    }

    public List<OSProcess> getChildProcesses() {
        ArrayList<Integer> parentProcessID = getProcessId();
        List<OSProcess> parentProcesses = getProcesses();
        List<OSProcess> childProcesses = new ArrayList<>();
        if (parentProcessID.size() <= 1) {
            childProcesses = os.getChildProcesses(parentProcessID.get(0), null, null, 0);
        } else {
            for (Integer integer : parentProcessID) {
                List<OSProcess> currentChildProcess;
                ArrayList<Integer> currentChildProcessIds;
                currentChildProcess = os.getChildProcesses(integer, null, null, 0);
                currentChildProcessIds = removeDuplicate(parentProcessID, getProcessId(currentChildProcess));
                for (Integer currentChildProcessId : currentChildProcessIds) {
                    childProcesses.add(os.getProcess(currentChildProcessId));
                }

            }
        }
        return childProcesses;
    }

    public double getCPUUsage(OSProcess process) {
        return (100d * (process.getKernelTime() + process.getUserTime()) / process.getUpTime()) / (os.getFamily().equalsIgnoreCase("windows") ? cpuNumber : 1);
    }

    public double getCPUUsage() {
        double cpuUsage = 0;
        List<OSProcess> processes = getProcesses();
        if (processes.size() <= 1) {
            cpuUsage = (100d * (processes.get(0).getKernelTime() + processes.get(0).getUserTime()) / processes.get(0).getUpTime()) / (os.getFamily().equalsIgnoreCase("windows") ? cpuNumber : 1);
        } else {
            for (OSProcess osProcess : processes) {
                double processCPUUsage = getCPUUsage(osProcess);
                cpuUsage += processCPUUsage;
            }
        }
        return cpuUsage;
    }

    public double getCPUUsage(List<OSProcess> processes) {
        double cpuUsage = 0;
        if (processes.size() <= 1) {
            cpuUsage = 100d * (processes.get(0).getKernelTime() + processes.get(0).getUserTime()) / processes.get(0).getUpTime();
        } else {
            for (OSProcess osProcess : processes) {
                double processCPUUsage = getCPUUsage(osProcess);
                cpuUsage += processCPUUsage;
            }
        }
        return cpuUsage;
    }


    public ArrayList<Object> getChildCPUUsage() {
        ArrayList<Object> childCPUUsage = new ArrayList<>();
        List<OSProcess> childProcesses = getChildProcesses();
        if (childProcesses != null) {
            System.out.println(childProcesses.size());
            for (OSProcess osProcess : childProcesses) {
                String childInfo;
                childInfo = "This is" + getProcessId(osProcess) + "child and its name is " + osProcess.getName() + ",  its cpu usage is:" + getCPUUsage(osProcess);
                childCPUUsage.add(childInfo);
            }
        }
        return childCPUUsage;
    }


    public double getMemoryUsage() {
        List<OSProcess> processes = getProcesses();
        double memoryUsage = 0;
        for (OSProcess osProcess : processes) {
            memoryUsage += osProcess.getResidentSetSize();
        }
        return memoryUsage / 1024.0 / 1024.0;
    }

    private List<OSProcess> removeDuplicate(List<OSProcess> dupArr) {
        Set<OSProcess> arr = new HashSet<>(dupArr);
        dupArr.clear();
        dupArr.addAll(arr);
        return dupArr;
    }

    private ArrayList<Integer> removeDuplicate(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        Set<Integer> set1 = new HashSet<>(arr1);
        Set<Integer> set2 = new HashSet<>(arr2);
        set1.retainAll(set2);
        arr2.removeAll(set1);
        return arr2;
    }

//    public ArrayList<Object> getChildProcessesUsage() {
//        ArrayList<Object> childProcessUsage = new ArrayList<>();
//        int parentProcessID = getProcessId();
//        List<OSProcess> childProcesses = os.getChildProcesses(parentProcessID, null, null, 0);
//        if (childProcesses.size() > 0) {
//            for (OSProcess childProcess : childProcesses) {
//                System.out.println(childProcess.getProcessID());
//                long affinityMask = childProcess.getAffinityMask();
//                int cpuCount = Long.bitCount(affinityMask);
//                double cpuLoad = 100d * (childProcess.getKernelTime() + childProcess.getUserTime()) / childProcess.getUpTime();
////                System.out.println("这个是新的："+cpuLoad / cpuCount);
////                childProcessUsage.add(100d * (childProcess.getKernelTime() + childProcess.getUserTime()) / childProcess.getUpTime());
//                childProcessUsage.add(cpuLoad);
//            }
//        }
//        return childProcessUsage;
//    }

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