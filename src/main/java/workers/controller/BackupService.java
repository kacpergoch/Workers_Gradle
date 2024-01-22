package workers.controller;

import workers.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackupService{
    public static void start(boolean flagOperation,boolean flagCompressionType, String directoryName){
        if(flagOperation){
            backupInitializeThreads(flagCompressionType, directoryName);
        }
        else{
            restoreInitializeThreads(directoryName);
        }
    }

    private static void backupInitializeThreads(boolean flagCompressionType, String directoryName){
        try {
            ExecutorService executorService;
            List<CompletableFuture<Void>> futures;
            executorService = Executors.newFixedThreadPool(10);
            futures = new ArrayList<>();

            File directory = new File(directoryName);

            if (!directory.exists()) {
                boolean dirSuccessful = directory.mkdirs();
                if(!dirSuccessful) {
                    throw new SecurityException("Could not create directory");
                }
            }

            for (int i = 0; i < Repository.getEmployees().size(); i++){
                Backup backup = new Backup(Repository.getEmployees().get(i), flagCompressionType, directoryName);
                futures.add(i, CompletableFuture.runAsync(backup, executorService));
            }

            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

            try {
                allOf.join();
            } catch (Exception e){
                e.printStackTrace();
            }

            executorService.shutdown();
        } catch (Exception e){
            System.out.println("Could not write to directory");
        }
    }

    private static void restoreInitializeThreads(String directoryName){
        try {
            ExecutorService executorService;
            List<CompletableFuture<Employee>> futures;
            executorService = Executors.newFixedThreadPool(10);
            futures = new ArrayList<>();

            File directory = new File(directoryName);
            if (!directory.exists()){
                throw new RuntimeException();
            }
            File[] contents = directory.listFiles();

            for (int i = 0; i < contents.length; i++){
                String filePath = directoryName + "/" + contents[i].getName();
                Restore restore = new Restore(filePath);
                futures.add(i, CompletableFuture.supplyAsync(restore::restoreEmployee, executorService));
            }

            for (CompletableFuture<Employee> future : futures){
                try {
                    Employee employee = future.join();
                    Repository.addEmployee(employee);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            executorService.shutdown();
        } catch (Exception e){
            System.out.println("Could not read from directory");
        }
    }
}
