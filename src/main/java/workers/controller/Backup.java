package workers.controller;

import workers.model.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Backup implements Runnable{
    Employee employee;
    boolean flagCompressionType;
    String directoryName;
    public Backup(Employee employee, boolean flagCompressionType, String directoryName){
        this.employee = employee;
        this.flagCompressionType = flagCompressionType;
        this.directoryName = directoryName;

        Thread t = new Thread( this);
        t.start();
    }
    public void run(){
        backupEmployee();
    }

    private void backupEmployee(){
        try {
            if (flagCompressionType) {
                File file = new File( directoryName + "/" + employee.getPesel() + ".gz");
                FileOutputStream fos = new FileOutputStream(file);

                GZIPOutputStream gzip = new GZIPOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(gzip);

                oos.writeObject(employee);
                oos.close();
            }
            else {
                File file = new File(directoryName + "/" + employee.getPesel() + ".zip");
                FileOutputStream fos = new FileOutputStream(file);

                ZipOutputStream zip = new ZipOutputStream(fos);
                zip.putNextEntry(new ZipEntry("backup.bin"));

                ObjectOutputStream oos = new ObjectOutputStream(zip);
                oos.writeObject(employee);

                zip.closeEntry();
                zip.close();
                oos.close();
            }
        } catch(Exception e) {
            System.out.println("Could not write to file");
        }
    }
}
