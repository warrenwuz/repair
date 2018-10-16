package org.tysf.gt.junit;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tysf.gt.pojo.RepairProject;
import org.tysf.gt.pojo.Repairman;
import org.tysf.gt.service.IRepairmanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RepairManJunit {
    @Resource
    private IRepairmanService repairmanServie;
    @Test
    public void queryRepairmanService(){
    /*	List<Repairman> queryRepariman = repairmanServie.queryRepariman();
    	System.out.println(queryRepariman.size());
        for(Repairman repairman:queryRepariman){
        	System.out.println(repairman);
        }*/
    List<RepairProject> queryProjectByRmid = repairmanServie.queryProjectByRmid("231311");
    System.out.println(queryProjectByRmid.size());
    }
}
