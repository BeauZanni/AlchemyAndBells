package org.beauzanni.aab.registros;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

import org.beauzanni.aab.bloques.AltarBloque;

import net.minecraft.block.Block;

public class RegistroBloques {
    public static final DeferredRegister<Block> BLOQUES = DeferredRegister.create(ForgeRegistries.BLOCKS, "alchemyandbells");
    public static final RegistryObject<Block> ALTARBLOQUE = BLOQUES.register("altar", AltarBloque::new);

    public static void registrar(IEventBus bus) {
        BLOQUES.register(bus);
    }
}