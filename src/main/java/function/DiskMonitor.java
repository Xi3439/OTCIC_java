package function;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class DiskMonitor {
    public static void main(String[] args) throws InterruptedException {
        long currentTime;
        long previousTime = 0;
        long timeDifference;

        double cpu = 0;
        int pid = 40208;
        OSProcess process;
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        CentralProcessor processor = si.getHardware().getProcessor();
        int cpuNumber = processor.getLogicalProcessorCount();
        boolean processExists = true;
        while (processExists) {
            process = os.getProcess(pid);
            if (process != null) {
                long read = process.getBytesRead();
                long write = process.getBytesWritten();
                System.out.println(read);
                System.out.println(write);
                Thread.sleep(5000);
                process = os.getProcess(pid);
                System.out.println(process.getBytesRead());
                System.out.println(process.getBytesWritten());
                long readComplete = process.getBytesRead() - read;
                long writeComplete = process.getBytesWritten() - write;
                float speed = (float) ((readComplete + writeComplete) / (5 * (1024.0 * 1024.0)));
                System.out.println("This is disk usage: " + speed + "MB");
            } else {
                processExists = false;
            }
        }
    }
}