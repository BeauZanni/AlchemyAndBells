package org.beauzanni.aab.bloques;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.block.material.Material;
import org.beauzanni.aab.entidades.AltarBloqueEntidad;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class AltarBloque extends Block {
    public AltarBloque() {
        super(AbstractBlock.Properties.of(Material.DIRT));
    }

    static VoxelShape baseBase = VoxelShapes.box(2 / 16.0D, 0 / 16.0D, 2 / 16.0D, 14 / 16.0D, 1 / 16.0D, 14 / 16.0D);
    static VoxelShape basePilar = VoxelShapes.box(7 / 16.0D, 1 / 16.0D, 7 / 16.0D, 9 / 16.0D, 10 / 16.0D, 9 / 16.0D);
    static VoxelShape cuencaBase = VoxelShapes.box(1 / 16.0D, 10 / 16.0D, 1 / 16.0D, 15 / 16.0D, 11 / 16.0D, 15 / 16.0D);
    static VoxelShape cuencaLateral1 = VoxelShapes.box(1 / 16.0D, 11 / 16.0D, 1 / 16.0D, 2 / 16.0D, 16 / 16.0D, 14 / 16.0D);
    static VoxelShape cuencaLateral2 = VoxelShapes.box(2 / 16.0D, 11 / 16.0D, 1 / 16.0D, 15 / 16.0D, 16 / 16.0D, 2 / 16.0D);
    static VoxelShape cuencaLateral3 = VoxelShapes.box(14 / 16.0D, 11 / 16.0D, 2 / 16.0D, 15 / 16.0D, 16 / 16.0D, 15 / 16.0D);
    static VoxelShape cuencaLateral4 = VoxelShapes.box(1 / 16.0D, 11 / 16.0D, 14 / 16.0D, 14 / 16.0D, 16 / 16.0D, 15 / 16.0D);

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isClientSide && player instanceof ServerPlayerEntity) {
            if (world.getBlockEntity(pos) instanceof AltarBloqueEntidad) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (AltarBloqueEntidad) world.getBlockEntity(pos), pos);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public net.minecraft.tileentity.TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AltarBloqueEntidad();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.or(baseBase, basePilar, cuencaBase, cuencaLateral1, cuencaLateral2, cuencaLateral3, cuencaLateral4);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.or(baseBase, basePilar, cuencaBase, cuencaLateral1, cuencaLateral2, cuencaLateral3, cuencaLateral4);
    }
}

