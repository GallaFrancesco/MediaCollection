import java.io.File;
import java.io.IOException;

// an interface to the Node tree class
// treats nodes as files / directories
public class FileTree {
	private Node<String> root;
	
	public Node<String> getRoot() {
		return root;
	}

	public void setRoot(Node<String> root) {
		this.root = root;
	}

	public FileTree(String rootData) throws IOException, InterruptedException{
		root = new Node<String>(rootData);
		System.out.println(rootData);
		this.build_directory_tree();
	}
	
	private void _build_tree(Node<String> node) throws IOException, InterruptedException {
		File file = new File(node.data);
		if (!file.isDirectory()){
			// file is a leaf of the tree: a music file that can be classified
			if (node.data.toLowerCase().contains(".mp3") || node.data.toLowerCase().contains(".flac") ||  node.data.toLowerCase().contains(".alac") ||node.data.toLowerCase().contains(".m4a")) {
				node.setInfo(MediaInfoUtil.getMediaInfo(node));
			}
			return; 
		}
		// file is a directory: recursively classify the files
		File[] fileList = file.listFiles();
		for (File childFile : fileList ){
			Node<String> newChild = new Node<String>(childFile.getAbsolutePath());
			newChild.parent = node;
			node.children.add(newChild);
			_build_tree(newChild);
		}
	}
	// build a tree of directories with files as leaves
	private void build_directory_tree() throws IOException, InterruptedException{
		File file = new File(this.root.data);
		if (file.isDirectory()) {
			_build_tree(root);
		} else {
			root.children = null;
		}
	}
	
	public void iterate_on_files(Node<String> node, String[] properties){
		if (node.is_leaf()){
			node.printInfo(properties);
			return;
		}
		for (Node<String> child : node.children) {
			iterate_on_files(child, properties);
		}
	}
}
