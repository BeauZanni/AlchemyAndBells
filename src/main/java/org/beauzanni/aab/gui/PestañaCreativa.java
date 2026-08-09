package org.beauzanni.aab.gui;
import net.minecraft.item.ItemStack;

import static org.beauzanni.aab.registros.RegistroBloques.ALTARBLOQUE;

import net.minecraft.item.ItemGroup;

public class PestañaCreativa extends ItemGroup {
	public PestañaCreativa() {
		super("alchemyandbells");
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ALTARBLOQUE.get());	
	}
}
