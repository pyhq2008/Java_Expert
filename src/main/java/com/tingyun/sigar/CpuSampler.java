package com.tingyun.sigar;

import org.hyperic.sigar.*;

/**
 * Created by Administrator on 2015/10/21.
 */
public class CpuSampler {
    private CpuInfo[] infos;
    private CpuPerc perc;
    private Cpu timer;

    public CpuSampler() {
    }

    public void populate(Sigar sigar) throws SigarException {
        infos = sigar.getCpuInfoList();
        perc = sigar.getCpuPerc();
        timer = sigar.getCpu();
    }

    public static CpuSampler gather(Sigar sigar) throws SigarException {
        CpuSampler cpuSampler = new CpuSampler();
        cpuSampler.populate(sigar);
        return cpuSampler;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.library.path"));
        System.out.println(System.getProperty("os.arch"));
        String osName = System.getProperty("os.name");
        System.out.println(osName.toLowerCase().contains("windows"));
        Sigar sigar = new Sigar();
//        CpuSampler cpuSampler = CpuSampler.gather(sigar);
////        for (int i = 0; i < cpuSampler.infos.length; i++) {// 不管是单块CPU还是多CPU都适用
////            CpuInfo info = cpuSampler.infos[i];
////            System.out.println("mhz=" + info.getMhz());// CPU的总量MHz
////            System.out.println("vendor=" + info.getVendor());// 获得CPU的卖主，如：Intel
////            System.out.println("model=" + info.getModel());// 获得CPU的类别，如：Celeron
////            System.out.println("cache size=" + info.getCacheSize());// 缓冲存储器数量
////        }
////
////        System.out.println("sys=" +  cpuSampler.perc.getSys() );
////
////        System.out.println("sys=" + cpuSampler.timer.getSys());
////        System.out.println("usr=" + cpuSampler.timer.getUser());
////
////        System.out.println("process=" + cpuSampler.timer.getTotal());

            long pid = sigar.getPid();
//            long[] pids = sigar.getProcList();
//            for (long pid : pids) {
            ProcState prs = sigar.getProcState(pid);
            ProcCpu pCpu = new ProcCpu();
            pCpu.gather(sigar, pid);
            System.out.println(pCpu.getUser());
            System.out.println(pCpu.getSys());
            System.out.println(prs.getName());
            System.out.println(pCpu.getUser()/1000.0D);
//            }



    }

}
