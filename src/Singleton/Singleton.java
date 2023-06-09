package Singleton;

import AccountEntry.AccountEntry;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton sc = null;
    public List<AccountEntry> accountList = null;
    private Singleton(){
        accountList = new ArrayList<AccountEntry>();
    }

    public static Singleton getInstance() {
        if(sc == null) {
            sc = new Singleton();
        }
        return sc;
    }
}
