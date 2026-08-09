package org.beauzanni.aab.gui;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import static org.beauzanni.aab.registros.RegistroGUI.ALTARTIPOCONTENEDOR;

import org.beauzanni.aab.entidades.AltarBloqueEntidad;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class AltarContenedor extends Container {
    private final AltarBloqueEntidad altar;
    
    public AltarContenedor(int id, PlayerInventory inventario, AltarBloqueEntidad entidad) {
        super(ALTARTIPOCONTENEDOR.get(), id);
        this.altar = entidad;
        ItemStackHandler handler = altar.getItemHandler();
        this.addSlot(new SlotItemHandler(handler, 0, 80, 35));

        for (int row = 0; row < 3; ++row) {
        	for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventario, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventario, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity jugador) {
        if (this.altar == null) return false;
        return jugador.distanceToSqr(
                this.altar.getBlockPos().getX() + 0.5D,
                this.altar.getBlockPos().getY() + 0.5D,
                this.altar.getBlockPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();
            final int altarSlotCount = 1;
            final int playerInvStart = altarSlotCount;
            final int playerInvEnd = playerInvStart + 27; // 3 filas
            final int hotbarStart = playerInvEnd;
            final int hotbarEnd = hotbarStart + 9;

            if (index < altarSlotCount) {
                if (!this.moveItemStackTo(slotStack, playerInvStart, hotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }
            } 
            
            else {
                if (!this.moveItemStackTo(slotStack, 0, altarSlotCount, false)) {
                    if (index >= playerInvStart && index < playerInvEnd) {
                        if (!this.moveItemStackTo(slotStack, hotbarStart, hotbarEnd, false)) {
                            return ItemStack.EMPTY;
                        }
                    } 
                    
                    else if (index >= hotbarStart && index < hotbarEnd) {
                        if (!this.moveItemStackTo(slotStack, playerInvStart, playerInvEnd, false)) {
                            return ItemStack.EMPTY;
                        }
                    } 
                    
                    else {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            
            else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    public AltarBloqueEntidad getAltar() {
        return altar;
    }
}