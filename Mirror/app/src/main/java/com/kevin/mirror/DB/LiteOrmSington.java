package com.kevin.mirror.DB;

import com.kevin.mirror.MyApp;
import com.litesuits.orm.LiteOrm;

/**
 * Created by kevin on 16/6/20.
 */
public class LiteOrmSington {
    private static LiteOrm liteOrm=LiteOrm.newCascadeInstance(MyApp.context,"mirror.db");

    public static LiteOrm getInstance() {
        return liteOrm;
    }

    private LiteOrmSington() {
    }
}
