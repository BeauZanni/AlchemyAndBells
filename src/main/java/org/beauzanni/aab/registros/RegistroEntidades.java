package org.beauzanni.aab.registros;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

import org.beauzanni.aab.entidades.AltarBloqueEntidad;

import net.minecraft.tileentity.TileEntityType;

public class RegistroEntidades {
    public static final DeferredRegister<TileEntityType<?>> ENTIDADES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "alchemyandbells");
    public static final RegistryObject<TileEntityType<AltarBloqueEntidad>> ALTARBLOQUETIPOENTIDAD = ENTIDADES.register("altar", () -> TileEntityType.Builder.of(AltarBloqueEntidad::new, RegistroBloques.ALTARBLOQUE.get()).build(null));

    public static void registrar(IEventBus bus) {
        ENTIDADES.register(bus);
    }
}