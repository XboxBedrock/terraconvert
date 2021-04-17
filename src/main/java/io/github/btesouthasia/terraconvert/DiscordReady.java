package io.github.btesouthasia.terraconvert;

import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordReadyEvent;
import github.scarsz.discordsrv.util.DiscordUtil;

import static io.github.btesouthasia.terraconvert.TerraConvert.messageEvent;

public class DiscordReady {
    private final TerraConvert plugin;


    public DiscordReady(TerraConvert plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void discordReadyEvent(DiscordReadyEvent event) {

        DiscordUtil.getJda().addEventListener(messageEvent);

    }
}
