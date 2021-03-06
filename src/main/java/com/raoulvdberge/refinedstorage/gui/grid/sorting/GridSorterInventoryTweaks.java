package com.raoulvdberge.refinedstorage.gui.grid.sorting;

import com.raoulvdberge.refinedstorage.api.network.grid.IGrid;
import com.raoulvdberge.refinedstorage.gui.grid.stack.GridStackItem;
import com.raoulvdberge.refinedstorage.gui.grid.stack.IGridStack;
import invtweaks.api.InvTweaksAPI;
import net.minecraftforge.fml.common.Loader;

public class GridSorterInventoryTweaks implements IGridSorter {
    public static final String MOD_ID = "inventorytweaks";

    private InvTweaksAPI api = null;

    public GridSorterInventoryTweaks() {
        try {
            api = (InvTweaksAPI) Class.forName("invtweaks.forge.InvTweaksMod", true, Loader.instance().getModClassLoader()).getField("instance").get(null);
        } catch (Exception e) {
            // NO OP
        }
    }

    @Override
    public boolean isApplicable(IGrid grid) {
        return grid.getSortingType() == IGrid.SORTING_TYPE_INVENTORYTWEAKS;
    }

    @Override
    public int compare(IGridStack left, IGridStack right, GridSorterDirection sortingDirection) {
        if (api != null && left instanceof GridStackItem && right instanceof GridStackItem) {
            if (sortingDirection == GridSorterDirection.DESCENDING) {
                return api.compareItems(((GridStackItem) left).getStack(), ((GridStackItem) right).getStack());
            } else if (sortingDirection == GridSorterDirection.ASCENDING) {
                return api.compareItems(((GridStackItem) right).getStack(), ((GridStackItem) left).getStack());
            }
        }

        return 0;
    }

}