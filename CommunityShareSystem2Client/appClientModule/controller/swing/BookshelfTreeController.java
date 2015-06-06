package controller.swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import model.EMedium;
import view.Dialogs;
import view.IBookshelfUI;
import view.swing.TreeNodeUserData;
import css.AppProperties;

abstract class BookshelfTreeController implements IBookshelfTreeController {

	/**
	 * Reference to the UI's delegate
	 */
	private IBookshelfUI bookshelfUI;
	private DefaultMutableTreeNode selectedNode;
	
	/**
	 * Links the delegate back to its UI
	 * 
	 * @param bookshelf The bookshelf UI
	 */
	public void setBookshelfUI (IBookshelfUI bookshelfUI) {
		this.bookshelfUI = bookshelfUI;
	}
	
	public MouseAdapter treeContextMenuOpened () {
		return new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON3) {
					// detect the clicked tree node
					JTree tree = (JTree) event.getSource ();
                    TreePath path = tree.getPathForLocation (event.getX(), event.getY()); 
                    if (path == null)         // if clicked outside a tree node, get the root node
                    	path = tree.getPathForRow(0);
                    else
                    	tree.setSelectionPath(path);
                    // show the context menu
                	DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent(); 
                	TreeNodeUserData nodeUserData = (TreeNodeUserData) selectedNode.getUserObject(); 
                	JPopupMenu menu = nodeUserData.getContextMenu();
                	if (menu != null)
                		menu.show(tree, event.getX(), event.getY());
				}
			}
		};
	}
	
	public ActionListener addNormalShelf () { 
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Container c = ((JMenuItem) event.getSource()).getParent();
				String shelfName = JOptionPane.showInputDialog(c, "Shelf name: ", 
						"Add normal shelf", JOptionPane.QUESTION_MESSAGE);
				if (shelfName != null) {
					if (!isValidShelfName(shelfName) || !addNormalShelf(shelfName))
						JOptionPane.showMessageDialog(c, "Cannot add shelf " + shelfName, 
								"Error adding shelf", JOptionPane.ERROR_MESSAGE);
				}
			}

			private boolean isValidShelfName(String name) {
				return !name.equals(AppProperties.INSTANCE.LIBRARY_NAME)
						&& !name.equals(AppProperties.INSTANCE.RENTALS_SHELF_NAME);
			}
		};
	}

	public ActionListener addSmartShelf () { 
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Container parent = ((JMenuItem) event.getSource()).getParent();
				JOptionPane.showMessageDialog(parent, "Not yet implemented. Sorry! :( ", 
						"Error adding smart shelf", JOptionPane.ERROR_MESSAGE);
			}
		};
	}


	public ActionListener deleteShelf () { 
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
            	TreeNodeUserData nodeUserData = (TreeNodeUserData) selectedNode.getUserObject(); 
				try {
					removeShelf (nodeUserData.getCaption());
				} catch (Exception e) {
					Dialogs.showException("Error removing shelf", e);
				}
			}
		};
	}

	
	/**
	 * @param libraryNode The library node
	 * @return The tree controller
	 */
	public TreeSelectionListener treeNodeSelection() {
		return new TreeSelectionListener () {
			@Override
			public void valueChanged(TreeSelectionEvent event) {
				selectedNode = 
					(DefaultMutableTreeNode) event.getNewLeadSelectionPath().getLastPathComponent();
	          	TreeNodeUserData nodeUserData = (TreeNodeUserData) selectedNode.getUserObject(); 
	          	nodeUserData.doAction(selectedNode);	
			}
		};
	}

	public ActionListener libraryNodeSelected () { 
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				shelfSelected(AppProperties.INSTANCE.LIBRARY_NAME);
				bookshelfUI.showEMedia(AppProperties.INSTANCE.LIBRARY_NAME, getLibraryEMedia());
			}
		};
	}

	public ActionListener shelfNodeSelected () { 
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
	           	DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) event.getSource(); 
            	TreeNodeUserData nodeUserData = (TreeNodeUserData) selectedNode.getUserObject(); 
				String selectedShelfName = nodeUserData.getCaption();
				shelfSelected(selectedShelfName);
				try {
					bookshelfUI.showEMedia(selectedShelfName, getShelfRentals(selectedShelfName));
				} catch (Exception e) {
					Dialogs.showException("Could not read the rentals", e);
				}
			}
		};
	}
	
	public ActionListener RentalNodeSelected () { 
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
	           	DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) event.getSource(); 
            	TreeNodeUserData nodeUserData = (TreeNodeUserData) selectedNode.getUserObject(); 
				String selectedShelfName = nodeUserData.getCaption();
				shelfSelected(selectedShelfName);
				try {
					bookshelfUI.showEMedia(selectedShelfName, getRentals());
				} catch (Exception e) {
					Dialogs.showException("Could not read the rentals", e);
				}
			}
		};
	}
	
	public abstract Iterable<EMedium> getShelfRentals(String selectedShelfName) throws Exception;

	public abstract Iterable<EMedium> getRentals() throws Exception;

	public abstract boolean addNormalShelf(String shelfName);
	
	public abstract Iterable<EMedium> getLibraryEMedia();
	
	public abstract void removeShelf(String caption) throws Exception;
	
	public abstract void shelfSelected(String selectedShelfName);
	
}
