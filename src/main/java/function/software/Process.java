package function.software;

import function.hardware.CpuInfo;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.util.List;

public class Process {
    private String name;
    private int pid;
    private OSProcess process = getProcess();

    SystemInfo systemInfo = new SystemInfo();
    OperatingSystem os = systemInfo.getOperatingSystem();
    CpuInfo cpuInfo = new CpuInfo();

    public String getName() {
        return name;
    }

    public int getPid() {
        return pid;
    }

    public OSProcess getProcess() {
        return process;
    }

    public List<OSProcess> getChildProcesses(){
        int parentPid = getPid();
        OSProcess process = getProcess();
        return os.getChildProcesses(parentPid, null, null, 0);
    }

    public Process(String name) {
        for (OSProcess process : os.getProcesses()) {
            if (process.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(process.getName().toLowerCase())) {
                this.process = process;
                this.name = name;
            }
        }
    }

    public Process(int pid) {
        for (OSProcess process : os.getProcesses()) {
            if (process.getProcessID() == pid) {
                this.process = process;
                this.name = process.getName();
                this.pid = pid;
            }
        }
    }

    public Process(OSProcess inputProcess) {
        for (OSProcess process : os.getProcesses()) {
            if (process.equals(inputProcess)) {
                this.process = process;
                this.name = process.getName();
                this.pid = process.getProcessID();
            }
        }
    }

    public Process(String name, int pid) {
        for (OSProcess process : os.getProcesses()) {
            if (process.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(process.getName().toLowerCase()) && process.getProcessID() == pid) {
                this.process = process;
                this.name = process.getName();
                this.pid = pid;
            }
        }
    }


    public double getCpuUsage() {
        double cpuUsage;
        int cpuNumber = cpuInfo.getLogicalProcessorCount();
        OSProcess process = getProcess();
        cpuUsage = (100d * (process.getKernelTime() + process.getUserTime()) / process.getUpTime()) / (os.getFamily().equalsIgnoreCase("windows") ? cpuNumber : 1);
        return cpuUsage;
    }

    public double getMemoryUsage() {
        double memoryUsage;
        OSProcess process = getProcess();
        memoryUsage = process.getResidentSetSize() / 1024.0 / 1024.0;
        return memoryUsage;
    }

    public double getIORead() {
        double diskRead;
        OSProcess process = getProcess();
        diskRead = process.getBytesRead() / 1024.0 / 1024.0;
        return diskRead;
    }

    public double getIOWrite() {
        double diskWritten;
        OSProcess process = getProcess();
        diskWritten = process.getBytesWritten() / 1024.0 / 1024.0;
        return diskWritten;
    }
}
