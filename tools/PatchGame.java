import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.File;

/** Patches the original me.pou.app.App class for the desktop port. */
public final class PatchGame {
    public static void main(String[] args) throws Exception {
        String gameJar = args.length > 0 ? args[0] : "game/pou-dex.jar";
        String shimOut = args.length > 1 ? args[1] : "shim/out";
        String androidJar = args.length > 2 ? args[2] : System.getenv().getOrDefault(
                "ANDROID_JAR",
                System.getProperty("user.home") + "/Android/Sdk/platforms/android-37.0/android.jar");
        String outDir = args.length > 3 ? args[3] : "patched";

        ClassPool pool = new ClassPool(true);
        pool.insertClassPath(gameJar);
        pool.insertClassPath(shimOut);
        pool.insertClassPath(androidJar);

        CtClass app = pool.get("me.pou.app.App");
        String[] methods = {"m2", "j2", "J1", "m3", "l2", "k2"};
        javassist.CtClass[] noParams = new javassist.CtClass[0];
        for (String name : methods) {
            try {
                CtMethod m = app.getDeclaredMethod(name, noParams);
                m.setBody("{}");
                System.out.println("patched App." + name + "()");
            } catch (NotFoundException e) {
                System.out.println("missing App." + name + "()");
            }
        }

        // The reference layout reserves space for a bottom adaptive banner ad.
        // Ads are disabled in this port, but the age overlay still needs the same inset.
        CtMethod v1 = app.getDeclaredMethod("V1", new javassist.CtClass[]{javassist.CtClass.booleanType});
        v1.insertBefore("if ($1) return 335.0f;");

        // The daily-reward dialog must not replace the age picker while it is open.
        CtClass g33 = pool.get("g33");
        CtMethod gI = g33.getDeclaredMethod("I", new javassist.CtClass[]{pool.get("j60")});
        gI.insertBefore("if ((this.b0 instanceof rb3) && ($1 instanceof x1)) return;");

        // App.onResume() sets these volumes on Android, but it can stop early
        // on desktop when a stubbed service throws.  Initialize the normal
        // defaults in the audio helper constructors instead.
        CtClass appType = pool.get("me.pou.app.App");
        CtClass ap2 = pool.get("ap2");
        CtConstructor ap2Ctor = ap2.getDeclaredConstructor(new CtClass[]{appType});
        ap2Ctor.insertBeforeBody("this.b = $1.o ? 0.0f : 0.7f;");

        CtClass pf1 = pool.get("pf1");
        CtConstructor pf1Ctor = pf1.getDeclaredConstructor(new CtClass[]{appType});
        pf1Ctor.insertBeforeBody("this.c = $1.p ? 0.0f : 0.3f;");

        File out = new File(outDir);
        out.mkdirs();
        app.writeFile(outDir);
        g33.writeFile(outDir);
        ap2.writeFile(outDir);
        pf1.writeFile(outDir);
        System.out.println("wrote " + new File(out, "me/pou/app/App.class"));
        System.out.println("wrote " + new File(out, "g33.class"));
        System.out.println("wrote " + new File(out, "ap2.class"));
        System.out.println("wrote " + new File(out, "pf1.class"));
    }
}
