package de.maxhenkel.car.items;

import de.maxhenkel.car.ModItemGroups;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

import javax.annotation.Nullable;

public class CarBucketItem extends BucketItem {

    public CarBucketItem(Fluid containedFluidIn, ResourceLocation registryName) {
        super(() -> containedFluidIn, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ModItemGroups.TAB_CAR));
        setRegistryName(registryName);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new FluidBucketWrapper(stack);
    }
}
