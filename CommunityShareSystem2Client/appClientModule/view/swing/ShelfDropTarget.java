package view.swing;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import model.EMedium;
import css.AppProperties;
import delegates.BookshelfUIDelegate;

class ShelfDropTarget extends EMediumDropTarget {

	public ShelfDropTarget(JFrame frame, JTree tree, BookshelfUIDelegate uiDelegate) {
		super(frame, tree, uiDelegate);
		new DropTarget(tree, this);
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		TreeNode node = getNodeForEvent(dtde);
		if (node.isLeaf())
			dtde.acceptDrag(dtde.getDropAction());
		else
			dtde.rejectDrag();
	}
	
	private TreeNode getNodeForEvent(DropTargetEvent dtde) {
		Point pt = getDropTargetLocation(dtde);
		DropTargetContext dtc = dtde.getDropTargetContext();
		JTree tree = (JTree) dtc.getComponent();
		TreePath path = tree.getClosestPathForLocation(pt.x, pt.y);
		return (TreeNode) path.getLastPathComponent();
	}
	
	private Point getDropTargetLocation(DropTargetEvent dtde) {
		if (dtde instanceof DropTargetDropEvent)
			return ((DropTargetDropEvent)dtde).getLocation();
		else if (dtde instanceof DropTargetDragEvent)
			return ((DropTargetDragEvent)dtde).getLocation();
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void drop(DropTargetDropEvent dtde) {
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode) getNodeForEvent(dtde);
		if (parent.isLeaf()) 
			dtde.acceptDrop(DnDConstants.ACTION_LINK);
		else {
			dtde.rejectDrop();
			return;
		}

		try {
			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				if (tr.isDataFlavorSupported(flavors[i])) {
					dtde.acceptDrop(dtde.getDropAction());
					EMedium eMedium = (EMedium) tr.getTransferData(flavors[i]);
					TreeNodeUserData userData = (TreeNodeUserData) parent.getUserObject();
					String shelfName = userData.getCaption();
					if (isLibrarySelected(dtde) && 
							shelfName.equals(AppProperties.INSTANCE.RENTALS_SHELF_NAME)) {
						if (!uiDelegate.rentLendable(eMedium))
							JOptionPane.showMessageDialog(frame, "Cannot lend e-medium!", 
									"Add document error", JOptionPane.ERROR_MESSAGE);
					} else if (!isLibrarySelected(dtde) && 
							!shelfName.equals(AppProperties.INSTANCE.RENTALS_SHELF_NAME)) {
						uiDelegate.addRental(shelfName, eMedium);
					} else
						JOptionPane.showMessageDialog(frame, "Need to rent the e-medium first!", 
								"Add document error", JOptionPane.ERROR_MESSAGE);	
					dtde.dropComplete(true);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Cannot add a document to this shelf", 
					"Add document error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
