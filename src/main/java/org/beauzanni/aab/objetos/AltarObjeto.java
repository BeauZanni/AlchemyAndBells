package org.beauzanni.aab.objetos;
import static org.beauzanni.aab.registros.RegistroBloques.ALTARBLOQUE;
import static org.beauzanni.aab.registros.RegistroGUI.PESTAÑACREATIVA;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;


public class AltarObjeto extends BlockItem {
	public AltarObjeto() {
		super(ALTARBLOQUE.get(), new Item.Properties().tab(PESTAÑACREATIVA));		
	}	
}