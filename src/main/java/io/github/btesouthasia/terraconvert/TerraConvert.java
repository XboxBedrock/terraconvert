package io.github.btesouthasia.terraconvert;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.buildtheearth.terraminusminus.projection.GeographicProjection;
import net.buildtheearth.terraminusminus.projection.dymaxion.BTEDymaxionProjection;
import org.bukkit.plugin.java.JavaPlugin;

public final class TerraConvert extends JavaPlugin {

    public MessageEvent messageEventtoSub = new MessageEvent();

    static TerraConvert instance;

    private final String prefix = "m!";

    private BTEDymaxionProjection proj = new BTEDymaxionProjection();

    public BTEDymaxionProjection getProjection() {
        return this.proj;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public static TerraConvert getInstance() {
        return instance;
    }
    

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        //DiscordSRV.api.subscribe(new MessageEvent());
        if (DiscordSRV.isReady) {
            DiscordUtil.getJda().addEventListener(messageEventtoSub);
        }

    }

    @Override
    public void onDisable() {
        DiscordUtil.getJda().removeEventListener(messageEventtoSub);
    }
}
