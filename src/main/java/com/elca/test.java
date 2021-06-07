package com.elca;

import com.elca.constant.Constant;
import com.elca.service.impl.CompanyService;
import java.io.IOException;
import java.nio.file.*;

public class test {

    private static CompanyService companyService;

    public static void main(String[] args) {

//        companyService = CompanyService.getInstance();
//
//        System.out.println("total capital of headquarters located in 'CH' = "+companyService.totalCapital());
//        System.out.println("\nCompanies name: \n");
//        companyService.filterAndPrint();
//
//        try {
//            WatchService watchService = FileSystems.getDefault().newWatchService();
//
//            Path path = Paths.get(Constant.watchedDir);
//
//            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
//
//            WatchKey key;
//
//            while ((key = watchService.take()) != null){
//                for (WatchEvent<?> event: key.pollEvents()){
//
//                    System.out.println("\nEvent kind:" + event.kind() + ". File affected: " + event.context() + ".");
//
//                    if (event.context().toString().equalsIgnoreCase("data1.csv")){
//                        System.out.println("total capital of headquarters located in 'CH' = "+companyService.totalCapital());
//                        System.out.println("\nCompanies name: \n");
//                        companyService.filterAndPrint();
//                    }
//                }
//
//                key.reset();
//            }
//        } catch (IOException | InterruptedException e) {
//           e.printStackTrace();
//        }
    }
}
