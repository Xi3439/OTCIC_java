package function.hardware;

import oshi.hardware.CentralProcessor;


public class CpuInfo extends HardwareInfo{
    CentralProcessor centralProcessor = hal.getProcessor();
    final String model = String.valueOf(centralProcessor);
    final int physicalPackageCount = centralProcessor.getPhysicalPackageCount();
    final int physicalProcessorCount = centralProcessor.getPhysicalProcessorCount();
    final int logicalProcessorCount = centralProcessor.getLogicalProcessorCount();

    public CentralProcessor getCentralProcessor() {
        return centralProcessor;
    }

    public String getModel() {
        return model;
    }

    public int getPhysicalPackageCount() {
        return physicalPackageCount;
    }

    public int getPhysicalProcessorCount() {
        return physicalProcessorCount;
    }

    public int getLogicalProcessorCount() {
        return logicalProcessorCount;
    }
}
