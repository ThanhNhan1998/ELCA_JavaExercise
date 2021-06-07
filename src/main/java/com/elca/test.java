package com.elca;

import com.elca.constant.Constant;
import com.elca.service.impl.CompanyService;
import java.io.IOException;
import java.nio.file.*;

public class test {

    static CompanyService companyService = CompanyService.getInstance();

    public static void main(String[] args) {

        System.out.println("Total capital of headquarters located in 'CH' = "+companyService.totalCapital());
        System.out.println("\nCompanies name: \n");
        companyService.filterAndPrint();

        try(WatchService watchService = FileSystems.getDefault().newWatchService();) {

            Path path = Paths.get(Constant.watchedDir);

            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;

            String fileName = Constant.filePath.substring(Constant.filePath.lastIndexOf('/') + 1);

            while ((key = watchService.take()) != null){
                for (WatchEvent<?> event: key.pollEvents()){

                    System.out.println("\nEvent kind:" + event.kind() + ". File affected: " + event.context() + ".");

                    if (event.context().toString().equalsIgnoreCase(fileName)){
                        System.out.println("\nTotal capital of headquarters located in 'CH' = "+companyService.totalCapital());
                        System.out.println("\nCompanies name: \n");
                        companyService.filterAndPrint();
                    }
                }

                key.reset();
            }
        } catch (IOException | InterruptedException e) {
           e.printStackTrace();
        }
    }
}
