package function.hardware;

import oshi.hardware.HardwareAbstractionLayer;

public abstract class HardwareInfo {
    private final oshi.SystemInfo si = new oshi.SystemInfo();
    final HardwareAbstractionLayer hal = si.getHardware();
}
