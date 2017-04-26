import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

// this section contains the hash table with all the info contained in the mediainfo file
// it encapsulates the classes LineInfo and SectionInfo
public class MediaInfo {
	private final String rawData;
	private final Map<String, SectionInfo> sections = new LinkedHashMap<>();

	private MediaInfo (final String rawData){
		this.rawData = Objects.requireNonNull(rawData);
	}
	
	private SectionInfo addSection(final SectionInfo section) throws IllegalArgumentException {
		final String name = section.getName();
		if(sections.containsKey(name)){
			throw new IllegalArgumentException("Duplicate section: " + name);
		}
		sections.put(name, section);
		return section;
	}
	
	public static MediaInfo parse(final String data) throws IllegalArgumentException {
		final MediaInfo mediaInfo = new MediaInfo(data);
		SectionInfo section = null;
		
		for (final String line : data.split("(\\r\\n|\\r|\\n)")) {
			if (line.isEmpty()){
				section = null;
				continue;
			}
			if (section == null){
				section = mediaInfo.addSection(SectionInfo.parse(line));
				continue;
			}
			section.add(LineInfo.parse(line));
		}
		return mediaInfo;
	}
	
	public String get(final String sectionName, final String valueName){
		final SectionInfo section = sections.get(sectionName);
		if (section == null){
			return null;
		}
		return section.get(valueName);
	}

	public String getRawData() {
		return rawData;
	}

	public Map<String, SectionInfo> getSections() {
		return sections;
	}
}
