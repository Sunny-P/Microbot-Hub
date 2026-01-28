package net.runelite.client.plugins.microbot.pestcontrol;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.PluginConstants;
import net.runelite.client.plugins.microbot.api.npc.Rs2NpcQueryable;
import net.runelite.client.plugins.microbot.util.camera.Rs2Camera;
import net.runelite.client.plugins.microbot.util.npc.Rs2NpcModel;
import net.runelite.client.plugins.microbot.util.walker.Rs2Walker;
import net.runelite.client.plugins.pestcontrol.Portal;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.runelite.client.plugins.microbot.pestcontrol.PestControlScript.portals;
import static net.runelite.client.plugins.microbot.util.Global.sleepGaussian;

@PluginDescriptor(
        name = PluginConstants.MOCROSOFT + "Pest Control",
        description = "Supports all boats, portals, and shields.",
        tags = {"pest control", "minigames"},
        authors = { "Mocrosoft" },
        version = PestControlPlugin.version,
        minClientVersion = "2.1.0",
		iconUrl = "https://chsami.github.io/Microbot-Hub/PestControlPlugin/assets/icon.png",
        cardUrl = "https://chsami.github.io/Microbot-Hub/PestControlPlugin/assets/card.png",
		enabledByDefault = PluginConstants.DEFAULT_ENABLED,
        isExternal = PluginConstants.IS_EXTERNAL
)
@Slf4j
public class PestControlPlugin extends Plugin {

	static final String version = "2.2.8C";

    @Inject
    PestControlScript pestControlScript;

    @Inject
    private PestControlConfig config;

    @Provides
    PestControlConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(PestControlConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;
    @Inject
    private PestControlOverlay pestControlOverlay;

    private final Pattern SHIELD_DROP = Pattern.compile("The ([a-z]+), [^ ]+ portal shield has dropped!", Pattern.CASE_INSENSITIVE);


    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(pestControlOverlay);
        }
        pestControlScript.initialise = true;
        pestControlScript.run(config);
    }

    protected void shutDown() {
        pestControlScript.shutdown();
        overlayManager.remove(pestControlOverlay);
    }

    @Subscribe
    public void onChatMessage(ChatMessage chatMessage) {
        if (chatMessage.getType() == ChatMessageType.GAMEMESSAGE) {
            Matcher matcher = SHIELD_DROP.matcher(chatMessage.getMessage());
            if (matcher.lookingAt()) {
                switch (matcher.group(1)) {
                    case "purple":
                        portals.stream().filter(x -> x == Portal.PURPLE).findFirst().get().setHasShield(false);
                        break;
                    case "blue":
                        portals.stream().filter(x -> x == Portal.BLUE).findFirst().get().setHasShield(false);
                        break;
                    case "red":
                        portals.stream().filter(x -> x == Portal.RED).findFirst().get().setHasShield(false);
                        break;
                    case "yellow":
                        portals.stream().filter(x -> x == Portal.YELLOW).findFirst().get().setHasShield(false);
                        break;
                }
            }
        }
    }

    // Yellow Portal No Shield ID:  1741
    // Purple Portal No Shield ID:  1739
    // Blue Portal No Shield ID:    1740
    // Red Portal No Shield ID:     1742
    //int[] portalIDs = {1739, 1740, 1741, 1742};
    //@Subscribe
    //public void onNpcSpawned(NpcSpawned npcSpawned)
    //{
    //    if (npcSpawned == null) return;
    //    if (!pestControlScript.isInPestControl()) return;
//
    //    int id = npcSpawned.getNpc().getId();
    //    switch(id)
    //    {
    //        case 1739:
    //        case 1740:
    //        case 1741:
    //        case 1742:
    //            // Stop walker from completing its path, and try and attack the portal faster
    //            Rs2Walker.setTarget(null);
//
    //            // npcSpawned matches a portal ID, so a portal has "spawned".
    //            //if (!Rs2Camera.isTileOnScreen(npcSpawned.getNpc().getLocalLocation()))
    //            //{
    //            Rs2Camera.turnTo(npcSpawned.getNpc().getLocalLocation());
    //            sleepGaussian(600, 100);
    //            //}
//
    //            Rs2NpcQueryable npcQueryable = Microbot.getRs2NpcCache().query().fromWorldView().withId(id);
    //            if (npcQueryable == null) return;
    //            npcQueryable.interact("Attack");
    //            break;
//
    //        default:
    //            return;
    //    }
    //}
}
