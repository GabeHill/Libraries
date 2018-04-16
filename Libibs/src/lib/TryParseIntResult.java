package lib;

public class TryParseIntResult {
	public final boolean didParse;
	public final Integer result;

	public TryParseIntResult(boolean didParse, Integer result) {
		this.didParse = didParse;
		if (didParse) {
			this.result = result;
		} else {
			this.result = null;
		}
	}
}
