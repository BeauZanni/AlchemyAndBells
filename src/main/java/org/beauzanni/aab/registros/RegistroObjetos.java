package org.beauzanni.aab.registros;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

import org.beauzanni.aab.objetos.*;

import net.minecraft.item.Item;

public class RegistroObjetos {
	public static final DeferredRegister<Item> OBJETOS = DeferredRegister.create(ForgeRegistries.ITEMS, "alchemyandbells");
	public static final RegistryObject<Item> ALTAROBJETO = OBJETOS.register("altar", () -> new AltarObjeto());
	
	public static void registrar (IEventBus bus) {		
		OBJETOS.register(bus);
	}
}