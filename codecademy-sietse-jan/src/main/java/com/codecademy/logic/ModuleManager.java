package com.codecademy.logic;

import java.util.ArrayList;
import com.codecademy.domain.Module;

public class ModuleManager {

    public ArrayList<Module> modules;

    public ModuleManager() {
        createDummyData();
    }

    private void createDummyData() {
        modules = new ArrayList<>();
        modules.add(new Module("Java 1", "1.0", "This module is about Java 1", "Jan", "jan@mail.com", 1));
        modules.add(new Module("Java 2", "1.0", "This module is about Java 2", "Jan", "jan@mail.com", 2));
        modules.add(new Module("Java 3", "1.0", "This module is about Java 3", "Jan", "jan@mail.com", 3));
        modules.add(new Module("Java 4", "1.0", "This module is about Java 4", "Jan", "jan@mail.com", 4));
        modules.add(new Module("Java 5", "1.0", "This module is about Java 5", "Jan", "jan@mail.com", 5));
    }

    protected ArrayList<Module> getModules() {
        return modules;
    }
}
