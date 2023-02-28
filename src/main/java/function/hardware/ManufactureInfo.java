package function.hardware;


import oshi.hardware.ComputerSystem;

public class ManufactureInfo extends HardwareInfo {
    private final ComputerSystem computerSystem = hal.getComputerSystem();
    final private String manufacturer = computerSystem.getManufacturer();
    final private String model = computerSystem.getModel();
    final private String serialNumber = computerSystem.getSerialNumber();


    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
