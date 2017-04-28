import java.io.IOException;

public class Main {
	static String[] properties = {"Album", "Track name", "Performer", "Composer", "Recorded date", "Format", "File size", "Duration", "Bit rate", "Sampling rate", "Channel(s)"};
	
	public static void main (String args[]) throws IllegalArgumentException, IOException, InterruptedException {
		final String mediainfoPath = "/usr/bin/mediainfo";
		if (args[0] == null){
			throw new IllegalArgumentException("Usage: java prog. mediapath");
		}
		final String mediaPath = args[0];
		MediaInfoUtil.setMediainfoPath(mediainfoPath);
		
		// build the file tree
		FileTree filetree = new FileTree(mediaPath);
		// here we run mediainfo on the given path, and print the result (print may be huge if directory is big)
		filetree.iterate_on_files(filetree.getRoot(), properties);	
	}
}
