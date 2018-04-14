import java.util.regex.Pattern;

public class Regex {
	//pattern attributes
	private Pattern pos;
	private Pattern num;
	private Pattern llayerName;
	private Pattern status;
	private Pattern tckl;
	private Pattern sck;
	private Pattern intt;
	private Pattern team;
	
	//constructor
	public Regex(Pattern pos, Pattern num, Pattern llayerName, Pattern status, Pattern tckl, Pattern sck, Pattern intt,
			Pattern team) {
		this.pos = pos;
		this.num = num;
		this.llayerName = llayerName;
		this.status = status;
		this.tckl = tckl;
		this.sck = sck;
		this.intt = intt;
		this.team = team;
	}

	public Pattern getPos() {
		return pos;
	}

	public void setPos(Pattern pos) {
		this.pos = pos;
	}

	public Pattern getNum() {
		return num;
	}

	public void setNum(Pattern num) {
		this.num = num;
	}

	public Pattern getLlayerName() {
		return llayerName;
	}

	public void setLlayerName(Pattern llayerName) {
		this.llayerName = llayerName;
	}

	public Pattern getStatus() {
		return status;
	}

	public void setStatus(Pattern status) {
		this.status = status;
	}

	public Pattern getTckl() {
		return tckl;
	}

	public void setTckl(Pattern tckl) {
		this.tckl = tckl;
	}

	public Pattern getSck() {
		return sck;
	}

	public void setSck(Pattern sck) {
		this.sck = sck;
	}

	public Pattern getIntt() {
		return intt;
	}

	public void setIntt(Pattern intt) {
		this.intt = intt;
	}

	public Pattern getTeam() {
		return team;
	}

	public void setTeam(Pattern team) {
		this.team = team;
	}
	
	// 	add getter and setter methods
	
}
