package io.github.btesouthasia.terraconvert;

import github.scarsz.discordsrv.dependencies.jda.api.events.message.guild.GuildMessageReceivedEvent;
import github.scarsz.discordsrv.dependencies.jda.api.hooks.ListenerAdapter;
import net.buildtheearth.terraminusminus.projection.GeographicProjection;
import net.buildtheearth.terraminusminus.projection.OutOfProjectionBoundsException;
import net.buildtheearth.terraminusminus.projection.dymaxion.BTEDymaxionProjection;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

public class MessageEvent extends ListenerAdapter {
    public double xorlat;
    public double zorlon;
    public TerraConvert terraplugin = TerraConvert.getInstance();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (!(event.getMessage().getAuthor().isBot())) {
            if (event.getMessage().getContentRaw().startsWith("m!convert")) {
                if (event.getChannel().canTalk()) {
                    String[] args = StringUtils.replace(event.getMessage().getContentRaw(), "m!convert", "").trim().split("\\s+");
                    GeographicProjection projection = new BTEDymaxionProjection();

                    if (args.length < 2) {
                        event.getChannel().sendMessage("`Usage: m!convert <x/lat> <z/lon>`").queue();
                        return;
                    }

                    double x;
                    double y;
                    try {
                        x = Double.parseDouble(args[0]);
                        y = Double.parseDouble(args[1]);
                    } catch (Exception e) {
                        event.getChannel().sendMessage("An unknown error has occurred").queue();
                        return;
                    }


                    double[] c = {x, y};

                    if (-180 <= c[1] && c[1] <= 180 && -90 <= c[0] && c[0] <= 90) {
                        try {
                            c = projection.fromGeo(c[1], c[0]);
                        } catch (OutOfProjectionBoundsException e) {
                            e.printStackTrace();
                            event.getChannel().sendMessage("Out of projection bounds").queue();
                            return;
                        }
                        event.getChannel().sendMessage("Result: `" + c[0] + ", " + c[1] + "`").queue();
                    } else {
                        try {
                            c = projection.toGeo(c[0], c[1]);
                        } catch (OutOfProjectionBoundsException e) {
                            e.printStackTrace();
                        }
                        event.getChannel().sendMessage("Result: `" + c[1] + ", " + c[0] + "`").queue();
                    }
                }
            }
        }
    }
}