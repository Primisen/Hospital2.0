package training.nadia.hospital.entity.test;

import training.nadia.hospital.entity.*;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        User user = new Doctor();

//        System.out.println(user instanceof Doctor);
        System.out.println(user.getClass());
    }
}
