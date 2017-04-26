import java.io.File;
import java.io.IOException;

public class FilePath {
	final public File file;
	final public JTree dir_tree;
	
	// initialize file and dir_tree
	// dir_tree is null since it is not needed if the file is not a dir
	public FilePath (final String path){
		this.file = new File(path);
		this.dir_tree = null;
	}
	
	// check if directory
	public boolean check_dir() {
		if (this.file.isDirectory()) {
			return true;
		}
		return false;
	}
	
	//build directory tree
	// TODO a TREE class which supports unlimited children and String as node names
	private void _build_tree() {
		return;
	}
	
}
