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
////        for (int i = 0; i < cpuSampler.infos.length; i++) {// �����ǵ���CPU���Ƕ�CPU������
////            CpuInfo info = cpuSampler.infos[i];
////            System.out.println("mhz=" + info.getMhz());// CPU������MHz
////            System.out.println("vendor=" + info.getVendor());// ���CPU���������磺Intel
////            System.out.println("model=" + info.getModel());// ���CPU������磺Celeron
////            System.out.println("cache size=" + info.getCacheSize());// ����洢������
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
