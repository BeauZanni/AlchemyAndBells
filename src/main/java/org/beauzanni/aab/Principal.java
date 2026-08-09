package org.beauzanni.aab;
import org.beauzanni.aab.registros.RegistroBloques;
import org.beauzanni.aab.registros.RegistroEntidades;
import org.beauzanni.aab.registros.RegistroGUI;
import org.beauzanni.aab.registros.RegistroObjetos;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod("alchemyandbells")
public class Principal {
    public Principal() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        RegistroBloques.registrar(bus);
        RegistroObjetos.registrar(bus);
        RegistroEntidades.registrar(bus);
        RegistroGUI.registrar(bus);   
        bus.addListener(this::cosasCliente);   
    }
    
	private void cosasCliente(final FMLClientSetupEvent event) {
		RegistroGUI.registrarCliente(event);
    }
    
}

