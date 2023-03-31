package function.monitor;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class MemoryMonitor {
    private final OperatingSystem os;

    public MemoryMonitor() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        this.os = systemInfo.getOperatingSystem();
    }

    public double getProcessMemoryUsage(int pid) throws InterruptedException {
        OSProcess process = this.os.getProcess(pid);
        double MemoryUsage = process.getResidentSetSize();;
        return MemoryUsage;
    }
}
