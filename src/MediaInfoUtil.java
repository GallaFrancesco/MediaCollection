import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

// this class is running mediainfo and storing the result
// into a mediainfo class
public class MediaInfoUtil {
	// the path in which the mediainfo executable is 
	private static String mediainfoPath;
	
	private static String executeMediaInfo(final String mediaPath) throws IOException, InterruptedException {
		final String exePath = MediaInfoUtil.getMediainfoPath();
		// the ProcessBuilder class is used to run a command and capture the output
		final ProcessBuilder builder = new ProcessBuilder(exePath, mediaPath);
		builder.redirectErrorStream(true);
		// here we start the mediainfo program
		final Process process = builder.start();
		final StringBuilder buffer = new StringBuilder();
		// capture output, append it to a buffer
		try (Reader reader = new InputStreamReader(process.getInputStream())){
			for (int i; (i = reader.read()) != -1;){
				buffer.append((char) i);
			}	
		}
		
		final int status = process.waitFor();
		if (status == 0){
			return buffer.toString();
		}
		throw new IOException("[Error] process exit status: "+ status);
	}
	
	public static MediaInfo getMediaInfo (Node<String> fileNode) throws IOException, InterruptedException {
		return MediaInfo.parse(MediaInfoUtil.executeMediaInfo(fileNode.getData()));
	}
	
	public static String getMediainfoPath() {
		return mediainfoPath;
	}

	public static void setMediainfoPath(String mediainfoPath) {
		MediaInfoUtil.mediainfoPath = mediainfoPath;
	}

}
