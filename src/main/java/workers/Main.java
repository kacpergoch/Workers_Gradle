package workers;

import workers.controller.WorkersController;

public class Main {
    public static void main(String[] args) {
        WorkersController controller = new WorkersController();
        controller.start();
    }
}