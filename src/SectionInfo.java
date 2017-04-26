import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

// this class contains the total line infos for a section of the file, in an hash table
// its methods are similar to LineInfo's ones 
public class SectionInfo {
	private final String name;
	private final Map <String, LineInfo> values = new LinkedHashMap<>();
	
	private SectionInfo(final String name){
		this.name = Objects.requireNonNull(name);
	}
	
	public static SectionInfo parse (final String line) throws IllegalArgumentException {
		if (line.contains(":")){
			throw new IllegalArgumentException("Line format invalid: Sections should not have ':'");
		}
		
		return new SectionInfo(line.trim());
	}
	
	public void add (final LineInfo lineInfo) throws IllegalArgumentException {
		final String name = lineInfo.getName();
		if (values.containsKey(name)) {
			throw new IllegalArgumentException("Duplicate name: " + name);
		}
		
		values.put(name, lineInfo);
	}
	
	public String get (final String lineName){
		final LineInfo lineInfo = values.get(lineName);
		if (lineInfo == null){
			return null;
		}
		
		return lineInfo.getValue();
	}

	public String getName() {
		return name;
	}

	public Map<String, LineInfo> getValues() {
		return values;
	}
	
}
