package io.github.btesouthasia.terraconvert;

import com.google.common.primitives.Doubles;
import github.scarsz.discordsrv.api.Subscribe;
import org.jetbrains.annotations.NotNull;
import github.scarsz.discordsrv.dependencies.jda.api.events.message.MessageReceivedEvent;
import github.scarsz.discordsrv.dependencies.jda.api.events.message.guild.GuildMessageReceivedEvent;
import github.scarsz.discordsrv.dependencies.jda.api.hooks.ListenerAdapter;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.buildtheearth.terraminusminus.projection.OutOfProjectionBoundsException;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.regex.Pattern;

public class MessageEvent extends ListenerAdapter
{
    public double xorlat;
    public double zorlon;
    public TerraConvert terraplugin = TerraConvert.getInstance();
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
    {
        if (!(event.getMessage().getAuthor().isBot())) {
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        if (event.getMessage().getContentDisplay().startsWith(terraplugin.getPrefix() + "convert")) {
            if (event.getMessage().getContentDisplay().contains(" ")) {
                String[] args = event.getMessage().getContentDisplay().split(Pattern.quote(" "));



                if (args[1] == "tomc") {
                    try {
                        xorlat = new Double(args[2]);
                        zorlon = new Double(args[3]);
                        event.getMessage().getChannel().sendMessage(StringUtils.join(Doubles.asList(terraplugin.getProjection().fromGeo(xorlat, zorlon)), " "));
                    }
                    catch (Exception e) {
                        if (e instanceof NumberFormatException) event.getMessage().getChannel().sendMessage("Provide Valid Lan and Lon");
                        if (e instanceof OutOfProjectionBoundsException) event.getMessage().getChannel().sendMessage("Lat and Lon Outside of Projection Bounds");
                        else event.getMessage().getChannel().sendMessage("An Unknown Error Has Occurred, Contact The Bot Dev");
                    }

                }
                if (args[1] == "tolatlon") {
                    try {
                        xorlat = new Double(args[2]);
                        zorlon = new Double(args[3]);
                        event.getMessage().getChannel().sendMessage(StringUtils.join(Doubles.asList(terraplugin.getProjection().toGeo(xorlat, zorlon)), " "));
                    }
                    catch (Exception e) {
                        if (e instanceof NumberFormatException) event.getMessage().getChannel().sendMessage("Provide Valid X and Z");
                        if (e instanceof OutOfProjectionBoundsException) event.getMessage().getChannel().sendMessage("X and Z Outside of Projection Bounds");
                        else event.getMessage().getChannel().sendMessage("An Unknown Error Has Occurred, Contact The Bot Dev");
                    }

                }
                else event.getMessage().getChannel().sendMessage("Usage `" + terraplugin.getPrefix() + "convert <tomc | tolatlon> <x/lat> <z/lon>`");


            }
            else event.getMessage().getChannel().sendMessage("Usage `" + terraplugin.getPrefix() + "convert <tomc | tolatlon> <x/lat> <z/lon>`");



        }

    }
    }
}