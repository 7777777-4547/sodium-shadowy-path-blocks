package rynnavinx.sspb.mixin.reeses_sodium_options;

import me.flashyreese.mods.reeses_sodium_options.client.gui.SodiumVideoOptionsScreen;

import me.jellysquid.mods.sodium.client.gui.options.OptionPage;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import rynnavinx.sspb.client.gui.SSPBGameOptionPages;

import java.util.List;


@Pseudo @Mixin(SodiumVideoOptionsScreen.class)
public class MixinSodiumVideoOptionsScreen {

    @Shadow @Final
    private List<OptionPage> pages;


    @Inject(method = "init", at = @At("TAIL"))
    public void postInit(CallbackInfo ci) {
        this.pages.add(SSPBGameOptionPages.sspb());
    }
}
