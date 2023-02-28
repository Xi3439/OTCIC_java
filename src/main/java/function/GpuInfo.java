//package function;
//
//import function.software.Application;
//import org.jocl.CL;
//import org.jocl.Pointer;
//import org.jocl.cl_device_id;
//import org.jocl.cl_platform_id;
//
//import java.util.ArrayList;
//
//public class GpuInfo {
//
//    public static void main(String[] args) {
//        // initialise the exception raiser
//        CL.setExceptionsEnabled(true);
//
//        // capture the numbers of OpenCL of platform
//        int[] numPlatformsArray = new int[1];
//        CL.clGetPlatformIDs(0, null, numPlatformsArray);
//        int numPlatforms = numPlatformsArray[0];
//
//        // capture all IDs on OpenCL
//        cl_platform_id[] platforms = new cl_platform_id[numPlatforms];
//        CL.clGetPlatformIDs(platforms.length, platforms, null);
//
//        // 遍历每个平台，获取所有设备信息，并打印设备厂商名称
//        for (cl_platform_id platform : platforms) {
//            // 获取当前平台上所有设备的数量
//            int[] numDevicesArray = new int[1];
//            CL.clGetDeviceIDs(platform, CL.CL_DEVICE_TYPE_ALL, 0, null, numDevicesArray);
//            int numDevices = numDevicesArray[0];
//
//            // 获取当前平台上所有设备的ID
//            cl_device_id[] devices = new cl_device_id[numDevices];
//            CL.clGetDeviceIDs(platform, CL.CL_DEVICE_TYPE_ALL, numDevices, devices, null);
//
//            // 遍历每个设备并打印设备厂商名称
//            for (cl_device_id device : devices) {
////                String vendor = getString(device, CL.CL_DEVICE_VENDOR);
//                String inf = getString(device);
////                System.out.println(vendor);
//                System.out.println(inf);
//            }
//        }
//
//        Application application = new Application("firefox");
//        ArrayList<Object> PIDs = new ArrayList<>();
//        PIDs = application.GetProcessIDs();
//        for (Object pid : PIDs) {
//            System.out.println(pid);
//        }
//    }
//
//    // 从设备获取指定参数的字符串值
//    private static String getString(cl_device_id device) {
//        long[] size = new long[1];
//        CL.clGetDeviceInfo(device, CL.CL_DEVICE_NAME, 0, null, size);
//        byte[] buffer = new byte[(int) size[0]];
//        CL.clGetDeviceInfo(device, CL.CL_DEVICE_NAME, buffer.length, Pointer.to(buffer), null);
//        return new String(buffer, 0, buffer.length - 1);
//    }
//}
