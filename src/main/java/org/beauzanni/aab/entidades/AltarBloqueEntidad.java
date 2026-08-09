package org.beauzanni.aab.entidades;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;

import org.beauzanni.aab.gui.AltarContenedor;
import org.beauzanni.aab.registros.RegistroEntidades;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

import net.minecraftforge.items.ItemStackHandler;

public class AltarBloqueEntidad extends TileEntity implements INamedContainerProvider {
    private int experiencia;
    private final ItemStackHandler items = new ItemStackHandler(1) {
    	@Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            AltarBloqueEntidad.this.setChanged(); // marca para guardar
            if (AltarBloqueEntidad.this.level != null && AltarBloqueEntidad.this.level.isClientSide() == false) {
                BlockPos pos = AltarBloqueEntidad.this.getBlockPos();
                AltarBloqueEntidad.this.level.sendBlockUpdated(pos, AltarBloqueEntidad.this.getBlockState(),
                        AltarBloqueEntidad.this.getBlockState(), 3);
            }
        }
    };

    public AltarBloqueEntidad() {
        super(RegistroEntidades.ALTARBLOQUETIPOENTIDAD.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.experiencia = nbt.getInt("experience");
        if (nbt.contains("items")) {
            items.deserializeNBT(nbt.getCompound("items"));
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        nbt.putInt("experience", this.experiencia);
        nbt.put("items", items.serializeNBT());
        return nbt;
    }

    public int getExperiencia() { return experiencia; }

    public void setExperiencia(int valor) {
        this.experiencia = valor;
        this.setChanged();
        if (this.level != null && !this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        }
    }

    public ItemStackHandler getItemHandler() {
        return items;
    }

    @Override
    public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
        return new AltarContenedor(id, inventory, this);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Altar");
    }
}

