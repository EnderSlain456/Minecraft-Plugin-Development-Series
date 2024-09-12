package com.tutorial.core;

public class Utils {

    public static String joinPrefix;
    public static String quitPrefix;
    public static Integer launchX;
    public static Integer launchY;
    public static Integer launchZ;
    public static Integer spawnX;
    public static Integer spawnY;
    public static Integer spawnZ;



    static {
        Utils.joinPrefix = Tutorial.getPlugin(Tutorial.class).getConfig().getString("join.joinPrefix");
    }

    static {
        Utils.quitPrefix = Tutorial.getPlugin(Tutorial.class).getConfig().getString("join.quitPrefix");
    }

    static {
        Utils.launchX = Tutorial.getPlugin(Tutorial.class).getConfig().getInt("launchPad.launchX");
    }

    static {
        Utils.launchY = Tutorial.getPlugin(Tutorial.class).getConfig().getInt("launchPad.launchY");
    }

    static {
        Utils.launchZ = Tutorial.getPlugin(Tutorial.class).getConfig().getInt("launchPad.launchZ");
    }

    static {
        Utils.spawnX = Tutorial.getPlugin(Tutorial.class).getConfig().getInt("spawn.spawnX");
    }

    static {
        Utils.spawnY = Tutorial.getPlugin(Tutorial.class).getConfig().getInt("spawn.spawnY");
    }

    static {
        Utils.spawnZ = Tutorial.getPlugin(Tutorial.class).getConfig().getInt("spawn.spawnZ");
    }

}
