import java.io.IOException;

public class Main {
	public static void main (String args[]) throws IllegalArgumentException, IOException, InterruptedException {
		final String mediainfoPath = "/usr/bin/mediainfo";
		if (args[0] == null){
			throw new IllegalArgumentException("Usage: java prog. mediapath");
		}
		final String mediaPath = args[0];
		MediaInfoUtil.setMediainfoPath(mediainfoPath);
		// here we run mediainfo on the given path 
		final MediaInfo mediaInfo = MediaInfoUtil.getMediaInfo(mediaPath);
		
		final String[] properties = {"Album", "Track name", "Performer", "Composer", "Recorded date", "Format", "File size", "Duration", "Bit rate", "Sampling rate", "Channel(s)"};    
		
		for (final String property : properties) {
			System.out.printf("[%s]: '%s''%n", property, mediaInfo.get("General", property));
		}
	}
}
