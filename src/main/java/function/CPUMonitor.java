//package function;
//
//import oshi.SystemInfo;
//import oshi.hardware.CentralProcessor;
//import oshi.hardware.HardwareAbstractionLayer;
//import oshi.software.os.OSProcess;
//import oshi.software.os.OperatingSystem;
//
//public class ProcessCpuUsageCalculator {
//    public static double calculateProcessTreeCpuUsage(long pid) {
//        SystemInfo systemInfo = new SystemInfo();
//        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
//        CentralProcessor processor = hardwareAbstractionLayer.getProcessor();
//
//        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
//        OSProcess rootProcess = operatingSystem.getProcess((int) pid);
//        double rootProcessCpuUsage = rootProcess.getProcessCpuLoadBetweenTicks() * processor.getLogicalProcessorCount();
//
//        double childrenCpuUsage = 0;
//        for (ProcessHandle child : rootProcess.children().toArray(ProcessHandle[]::new)) {
//            childrenCpuUsage += calculateProcessTreeCpuUsage(child.pid());
//        }
//
//        return rootProcessCpuUsage + childrenCpuUsage;
//    }
//}
//

