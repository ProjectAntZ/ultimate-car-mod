package de.maxhenkel.car.blocks;

import de.maxhenkel.car.blocks.BlockPaint.EnumPaintType;
import de.maxhenkel.car.blocks.fluid.CarFluidBlock;
import de.maxhenkel.car.fluids.ModFluids;
import de.maxhenkel.corelib.block.IItemBlock;
import de.maxhenkel.corelib.reflection.ReflectionUtils;
import de.maxhenkel.tools.NoRegister;
import de.maxhenkel.tools.OnlyBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModBlocks {

    public static final BlockAsphalt ASPHALT = new BlockAsphalt();
    public static final BlockAsphaltSlope ASPHALT_SLOPE = new BlockAsphaltSlope();
    public static final BlockAsphaltSlopeFlat ASPHALT_SLOPE_FLAT_UPPER = new BlockAsphaltSlopeFlat(true);
    public static final BlockAsphaltSlopeFlat ASPHALT_SLOPE_FLAT_LOWER = new BlockAsphaltSlopeFlat(false);
    public static final BlockAsphaltSlab ASPHALT_SLAB = new BlockAsphaltSlab();
    public static final BlockGasStation GAS_STATION = new BlockGasStation();
    @OnlyBlock
    public static final BlockGasStationTop GAS_STATION_TOP = new BlockGasStationTop();
    @OnlyBlock
    public static final BlockCanolaCrop CANOLA_CROP = new BlockCanolaCrop();
    public static final BlockOilMill OIL_MILL = new BlockOilMill();
    public static final BlockBlastFurnace BLAST_FURNACE = new BlockBlastFurnace();
    public static final BlockBackmixReactor BACKMIX_REACTOR = new BlockBackmixReactor();
    public static final BlockGenerator GENERATOR = new BlockGenerator();
    public static final BlockSplitTank SPLIT_TANK = new BlockSplitTank();
    @OnlyBlock
    public static final BlockSplitTankTop SPLIT_TANK_TOP = new BlockSplitTankTop();
    public static final BlockTank TANK = new BlockTank();
    @OnlyBlock
    public static final BlockGuardRail GUARD_RAIL = new BlockGuardRail();
    public static final BlockCarWorkshop CAR_WORKSHOP = new BlockCarWorkshop();
    public static final BlockCarWorkshopOutter CAR_WORKSHOP_OUTTER = new BlockCarWorkshopOutter();
    public static final BlockCable CABLE = new BlockCable();
    public static final BlockFluidExtractor FLUID_EXTRACTOR = new BlockFluidExtractor();
    public static final BlockFluidPipe FLUID_PIPE = new BlockFluidPipe();
    public static final BlockDynamo DYNAMO = new BlockDynamo();
    public static final BlockCrank CRANK = new BlockCrank();
    public static final BlockSign SIGN = new BlockSign();
    public static final BlockSignPost SIGN_POST = new BlockSignPost();
    public static final BlockCarPressurePlate CAR_PRESSURE_PLATE = new BlockCarPressurePlate();

    public static final LiquidBlock BIO_DIESEL = new CarFluidBlock(() -> ModFluids.BIO_DIESEL);
    public static final LiquidBlock CANOLA_METHANOL_MIX = new CarFluidBlock(() -> ModFluids.CANOLA_METHANOL_MIX);
    public static final LiquidBlock CANOLA_OIL = new CarFluidBlock(() -> ModFluids.CANOLA_OIL);
    public static final LiquidBlock GLYCERIN = new CarFluidBlock(() -> ModFluids.GLYCERIN);
    public static final LiquidBlock METHANOL = new CarFluidBlock(() -> ModFluids.METHANOL);

    public static final BlockPaint[] PAINTS;
    public static final BlockPaint[] YELLOW_PAINTS;

    static {
        PAINTS = new BlockPaint[EnumPaintType.values().length];
        for (int i = 0; i < PAINTS.length; i++) {
            PAINTS[i] = new BlockPaint(EnumPaintType.values()[i], false);
        }

        YELLOW_PAINTS = new BlockPaint[EnumPaintType.values().length];
        for (int i = 0; i < YELLOW_PAINTS.length; i++) {
            YELLOW_PAINTS[i] = new BlockPaint(EnumPaintType.values()[i], true);
        }
    }

    public static List<IItemBlock> getBlocksWithItems() {
        List<IItemBlock> blocks = new ArrayList<>();
        for (Field field : ModBlocks.class.getFields()) {
            if (ReflectionUtils.hasAnnotation(field, NoRegister.class) || ReflectionUtils.hasAnnotation(field, OnlyBlock.class)) {
                continue;
            }
            try {
                Object obj = field.get(null);
                if (obj instanceof IItemBlock) {
                    blocks.add((IItemBlock) obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        blocks.addAll(Arrays.asList(PAINTS));
        blocks.addAll(Arrays.asList(YELLOW_PAINTS));

        return blocks;
    }

    public static List<Block> getAll() {
        List<Block> blocks = new ArrayList<>();
        for (Field field : ModBlocks.class.getFields()) {
            if (ReflectionUtils.hasAnnotation(field, NoRegister.class)) {
                continue;
            }
            try {
                Object obj = field.get(null);
                if (obj instanceof Block) {
                    blocks.add((Block) obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        blocks.addAll(Arrays.asList(PAINTS));
        blocks.addAll(Arrays.asList(YELLOW_PAINTS));

        return blocks;
    }

}
