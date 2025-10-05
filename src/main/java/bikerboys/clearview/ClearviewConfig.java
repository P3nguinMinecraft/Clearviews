package bikerboys.clearview;

import eu.midnightdust.lib.config.MidnightConfig;

public class ClearviewConfig extends MidnightConfig {
    public static final String CLEARVIEW = "clearview";

    @Entry(category = CLEARVIEW) public static boolean DarknessEffect = true;
    @Entry(category = CLEARVIEW) public static boolean BlindnessEffect = true;
    @Entry(category = CLEARVIEW) public static boolean NauseaEffect = true;

    @Entry(category = CLEARVIEW) public static boolean PortalOverlayDisabled = true;
    @Entry(category = CLEARVIEW) public static boolean RemoveSpyglassBorder = true;
    @Entry(category = CLEARVIEW) public static boolean FogDisabled = true;


    @Entry(category = CLEARVIEW) public static float fogStart = 1000.0F;
    @Entry(category = CLEARVIEW) public static float fogEnd = 1000.0F;
}
