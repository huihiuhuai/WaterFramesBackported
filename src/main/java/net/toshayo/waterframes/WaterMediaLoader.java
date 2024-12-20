package net.toshayo.waterframes;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.watermedia.WaterMedia;
import org.watermedia.core.exceptions.IllegalEnvironmentException;
import org.watermedia.core.exceptions.IllegalTLauncherException;
import org.watermedia.core.exceptions.IncompatibleModException;
import org.watermedia.loaders.ILoader;

import java.io.File;
import java.nio.file.Path;

import static org.watermedia.WaterMedia.LOGGER;

@Mod(
        modid = WaterMedia.ID,
        name = WaterMedia.NAME,
        version = "2.1.8"
)
public class WaterMediaLoader implements ILoader {
    private static final Marker IT = MarkerManager.getMarker("WaterMediaLoader");
    private static final Path tmpPath = new File(System.getProperty("java.io.tmpdir")).toPath().toAbsolutePath().resolve(WaterMedia.ID);
    private static final Path processPath = new File("").toPath().toAbsolutePath();

    public WaterMediaLoader() {
        /* FIXME: i don't know exactly what this does
        try {
            String pairClassName = concatPackage("org", "apache", "commons", "lang3", "tuple", "Pair");
            Method pairOf = Class.forName(pairClassName).getMethod("of", Object.class, Object.class);

            Supplier<String> stringSupplier = () -> "";
            Supplier<Boolean> booleanSupplier = () -> true;

            Object o = pairOf.invoke(null, stringSupplier, booleanSupplier);

            ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> o);
            LOGGER.info(IT, "DISPLAYTEST correctly updated");
        } catch (Throwable ignored) {}*/

        try {
            if (tlcheck()) throw new IllegalTLauncherException();
            if (modInstalled("xenon")) throw new IncompatibleModException("xenon", "Xenon", "Embeddium (embeddium) or Sodium (sodium)");

            if (clientSide()) WaterMedia.prepare(this).start();
            else if (!developerMode()) throw new IllegalEnvironmentException();
        } catch (Exception e) {
            throw new RuntimeException("Failed starting " + WaterMedia.NAME + " for " + name() +": " + e.getMessage(), e);
        }
    }

    @Override
    public String name() {
        return "Forge";
    }

    @Override
    public Path tempDir() {
        return tmpPath;
    }

    @Override
    public Path processDir() {
        return processPath;
    }

    @Override
    public boolean tlcheck() {
        return false;
    }

    public boolean modInstalled(String id) {
        return Loader.isModLoaded(id);
    }

    @Override
    public boolean clientSide() {
        try {
            return FMLCommonHandler.instance().getSide() == Side.CLIENT;
        } catch (Throwable t2) {
            LOGGER.error(IT, "Cannot check if was client, assuming it was");
            return true;
        }
    }

    @Override
    public boolean developerMode() {
        try {
            return !WaterFramesMod.isObfEnv();
        } catch (Throwable t) {
            LOGGER.error(IT, "Cannot check if was developer env, assuming it wasn't");
            return false;
        }
    }

    private String concatPackage(String... pgk) {
        StringBuilder r = new StringBuilder();
        for (String s : pgk) {
            r.append(s).append(".");
        }

        if (r.length() > 0) {
            r.setLength(r.length() - 1);
        }

        return r.toString();
    }
}
