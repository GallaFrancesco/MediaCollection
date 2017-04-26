import java.util.Objects;
// this class contains the line information
// from the mediainfo generated file
// each lineinfo is a pair [name:value] inside the file

public class LineInfo {
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	private final String name;
	private final String value;
	
	private LineInfo(final String name, final String value) {
		this.name = Objects.requireNonNull(name);
		this.value = Objects.requireNonNull(value);
	}
	
	public static LineInfo parse(final String line) throws IllegalArgumentException {
		// parse a line, retrieve info about it
		if (!line.contains(" : ")){
			throw new IllegalArgumentException ("Line format invalid: [arg : value]");
		}
		
		final String[] parts = line.split(" : ", 2);
		return new LineInfo(parts[0].trim(), parts[1].trim());
	}
	
}
