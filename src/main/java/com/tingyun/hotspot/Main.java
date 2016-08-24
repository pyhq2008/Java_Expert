package com.tingyun.hotspot;

/**
 * Created by Administrator on 2016/4/26.
 */
public class Main {

    public static void main(String[] args){
        String ss = "HotSpotTingyun#tingyun[1] [2]com.networkbench.base.filter.XSSRequestFilter#doFilter[1461563051167][0] [3]com.tingyun.report.core.security.interceptor.UserServiceTimeInterceptor#preHandle[1461563051217][50] [4]com.tingyun.report.core.commons.service.impl.UserPackageService#getByAgreeIdAndType[1461563051217][50] [5]com.tingyun.report.core.commons.service.impl.UserPackageService#getAllByAgreeIdAndType[1461563051217][50] [3]com.tingyun.api.server.controller.TopologyController#topology[1461563051267][175] [4]com.tingyun.api.core.util.ReflectionUtils#invokeGetterMethod[1461563051267][50] [5]com.tingyun.api.core.util.ReflectionUtils#invokeMethod[1461563051267][50] [6]com.tingyun.api.core.util.ReflectionUtils#getAccessibleMethod[1461563051267][50] [4]com.tingyun.api.server.chart.service.impl.TopologyService#getApplicationTopologyOverview[1461563051317][125] [5]com.tingyun.api.server.chart.service.impl.TopologyService#findUserComponents[1461563051317][50] [6]com.tingyun.api.server.chart.service.impl.TopologyService#getTopologysComponents[1461563051317][50] [7]com.networkbench.base.database.jda.QueryImpl#list[1461563051317][125] [8]com.networkbench.base.database.jda.JdbcDaoImpl#find[1461563051317][125] [5]com.tingyun.api.server.chart.service.impl.TopologyService#findUserApps[1461563051367][50] [6]com.tingyun.api.server.chart.service.impl.TopologyService#getTopologysApps[1461563051367][50] [5]com.tingyun.api.server.chart.service.impl.TopologyService#processApplication[1461563051417][25] [6]com.tingyun.api.server.chart.service.impl.TopologyService#findApplicationsCallerService[1461563051417][25]";

        System.out.println(ss.getBytes().length);
    }
}
