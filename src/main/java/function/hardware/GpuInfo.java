package function.hardware;

import function.software.Application;
import org.jocl.CL;
import org.jocl.Pointer;
import org.jocl.cl_device_id;
import org.jocl.cl_platform_id;

import java.util.ArrayList;

public class GpuInfo {

    public void getGpuInfo() {
        // initialise the exception raiser
        CL.setExceptionsEnabled(true);

        // capture the numbers of OpenCL of platform
        int[] numPlatformsArray = new int[1];
        CL.clGetPlatformIDs(0, null, numPlatformsArray);
        int numPlatforms = numPlatformsArray[0];

        // capture all IDs on OpenCL
        cl_platform_id[] platforms = new cl_platform_id[numPlatforms];
        CL.clGetPlatformIDs(platforms.length, platforms, null);

        for (cl_platform_id platform : platforms) {
            // get the number of devices
            int[] numDevicesArray = new int[1];
            CL.clGetDeviceIDs(platform, CL.CL_DEVICE_TYPE_ALL, 0, null, numDevicesArray);
            int numDevices = numDevicesArray[0];


            cl_device_id[] devices = new cl_device_id[numDevices];
            CL.clGetDeviceIDs(platform, CL.CL_DEVICE_TYPE_ALL, numDevices, devices, null);


            for (cl_device_id device : devices) {
//                String vendor = getString(device, CL.CL_DEVICE_VENDOR);
                String inf = getString(device);
//                System.out.println(vendor);
                System.out.println(inf);
            }
        }
    }

    private static String getString(cl_device_id device) {
        long[] size = new long[1];
        CL.clGetDeviceInfo(device, CL.CL_DEVICE_NAME, 0, null, size);
        byte[] buffer = new byte[(int) size[0]];
        CL.clGetDeviceInfo(device, CL.CL_DEVICE_NAME, buffer.length, Pointer.to(buffer), null);
        return new String(buffer, 0, buffer.length - 1);
    }
}
