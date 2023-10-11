package com.springwater.imlib;

import com.google.common.collect.Lists;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
@SuppressWarnings("unused")
public class LateMixinInit implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Lists.newArrayList("mixins.imlib.json");
    }
}
