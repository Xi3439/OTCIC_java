package function;

import function.software.Process;

public class test {
    public static void main(String[] args) throws InterruptedException{
        Process process = new Process(1);
        System.out.println(process.getName());
        System.out.println(process.getCpuUsage());
        System.out.println(process.getMemoryUsage());
        System.out.println(process.getIORead());
        System.out.println(process.getIOWrite());
    }
}
