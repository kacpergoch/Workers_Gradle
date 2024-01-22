package workers.controller;

import workers.model.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

public class Restore implements Runnable{
    String filePath;
    public Restore( String filePath){
        this.filePath = filePath;

        Thread t = new Thread( this);
        t.start();
    }
    public void run(){
        restoreEmployee();
    }

    public Employee restoreEmployee(){
        try{
            Employee employee = null;
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);

            if (filePath.contains(".gz")){ // Restore from GZIP
                GZIPInputStream gis = new GZIPInputStream(fis);

                ObjectInputStream ois = new ObjectInputStream(gis);
                Object object = ois.readObject();
                employee = (Employee) object;

                gis.close();
                ois.close();
            }
            else if(filePath.contains(".zip")){ // Restore from ZIP
                ZipInputStream zis = new ZipInputStream(fis);
                zis.getNextEntry();

                ObjectInputStream ois = new ObjectInputStream(zis);
                Object object = ois.readObject();
                employee = (Employee) object;

                zis.closeEntry();
                zis.close();
                ois.close();
            }
            return employee;
        } catch (Exception e) {
            System.out.println("Could not read from file");
        }
        return null;
    }
}
