package de.maxhenkel.tools;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class FluidUtils {
	
	public static boolean containsFluid(List<FluidSelector> list, Fluid fluid){
		for(FluidSelector sel:list){
			if(sel.isValid(fluid)){
				return true;
			}
		}
		
		return false;
	}

	@Nullable
	public static FluidStack tryFluidTransfer(IFluidHandler fluidDestination, IFluidHandler fluidSource, int maxAmount,
			boolean doTransfer, Fluid filter) {
		FluidStack drainable = fluidSource.drain(new FluidStack(filter, maxAmount), false);
		if (drainable != null && drainable.amount > 0) {
			int fillableAmount = fluidDestination.fill(drainable, false);
			if (fillableAmount > 0) {
				if (doTransfer) {
					FluidStack drained = fluidSource.drain(new FluidStack(filter, fillableAmount), true);
					if (drained != null) {
						drained.amount = fluidDestination.fill(drained, true);
						return drained;
					}
				} else {
					drainable.amount = fillableAmount;
					return drainable;
				}
			}
		}
		return null;
	}

}
