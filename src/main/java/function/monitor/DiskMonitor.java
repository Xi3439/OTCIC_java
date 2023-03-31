package function.monitor;

import function.appplication.ProcessCoreCount;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class DiskMonitor {
    private final OperatingSystem os;

    public DiskMonitor() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        this.os = systemInfo.getOperatingSystem();
    }

    public float getProcessDiskUsage(int pid, int interval) throws InterruptedException {
        OSProcess process = this.os.getProcess(pid);
        float DiskUsage = 0;
        if (process != null) {
            long read = process.getBytesRead();
            long write = process.getBytesWritten();

            Thread.sleep(1000L * interval);
            process = os.getProcess(pid);

            long readComplete = process.getBytesRead() - read;
            long writeComplete = process.getBytesWritten() - write;
            DiskUsage = (float) ((readComplete + writeComplete) / (interval * (1024.0 * 1024.0)));
            System.out.println("This is disk usage: " + DiskUsage + "MB");
        }
        return DiskUsage;
    }
}
