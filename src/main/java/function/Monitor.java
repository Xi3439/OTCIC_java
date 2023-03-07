package function;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class Monitor {
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
                // CPU
                currentTime = process.getKernelTime() + process.getUserTime();

                if (previousTime != -1) {
                    // If we have both a previous and a current time
                    // we can calculate the CPU usage
                    timeDifference = currentTime - previousTime;
                    cpu = (100d * (timeDifference / ((double) 1000))) / cpuNumber;
                }
                System.out.println("This is CPU usage: "+cpu);
                previousTime = currentTime;

                Thread.sleep(1000);
            } else {
                processExists = false;
            }
        }
    }
}
