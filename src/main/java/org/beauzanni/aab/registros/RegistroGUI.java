package org.beauzanni.aab.registros;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import org.beauzanni.aab.entidades.AltarBloqueEntidad;
import org.beauzanni.aab.gui.AltarContenedor;
import org.beauzanni.aab.gui.AltarPantalla;
import org.beauzanni.aab.gui.PestañaCreativa;

import net.minecraft.client.gui.ScreenManager;

public class RegistroGUI {
    public static final DeferredRegister<ContainerType<?>> CONTENEDORES = DeferredRegister.create(ForgeRegistries.CONTAINERS, "alchemyandbells");    
    public static final RegistryObject<ContainerType<AltarContenedor>> ALTARTIPOCONTENEDOR = CONTENEDORES.register("altar", () -> IForgeContainerType.create((id, inventario, info) -> {TileEntity te = inventario.player.level.getBlockEntity(info.readBlockPos()); if (te instanceof AltarBloqueEntidad) { return new AltarContenedor(id, inventario, (AltarBloqueEntidad) te); } return null;}));
    public static final PestañaCreativa PESTAÑACREATIVA = new PestañaCreativa();
    
    public static void registrar(IEventBus bus) {
        CONTENEDORES.register(bus);
    }

    public static void registrarCliente(final FMLClientSetupEvent event) {
        ScreenManager.register(ALTARTIPOCONTENEDOR.get(), AltarPantalla::new);
    }
}