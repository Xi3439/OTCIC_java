package function.appplication;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class ProcessCoreCount {
    private final OperatingSystem os;

    public ProcessCoreCount() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        this.os = systemInfo.getOperatingSystem();
    }

    public int getDefaultCoreCount(int pid) {
        OSProcess process = this.os.getProcess(pid);
        long affinityMask = process.getAffinityMask();
        return Long.bitCount(affinityMask);
    }
}