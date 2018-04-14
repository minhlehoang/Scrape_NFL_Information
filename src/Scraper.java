import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Scraper {
	
	private Matcher matcher;
	private Regex regex;
	private String url;
	private String display;
	private String url_string;
	private String url_string2;
	URLConnection urlConnection = null;
	// constructor
	public Scraper (String url) throws IOException {
		this.url = url;
	} 
	// reads the data from a web page and searches for the string matches
	public void parseData() throws IOException {
		//Declare variables to store scraped values
		String pos = "";
		String num = "";
		String playername = "";
		String status = "";
		String tckl = "";
		String sck = "";
		String inte = "";
		String team = "";
		String string_concat = "";
		String string_concat2 = "";
		String result = String.format("%-10s %-10s %-25s %-10s %-10s %-10s %-10s %-10s\n", "Pos", "Num", "Player Name", "Status", "TCKL", "SCK", "INT", "Team") + "\r\n";
		result += String.format("%-10s %-10s %-25s %-10s %-10s %-10s %-10s %-10s\n", "---", "---", "-----------", "------", "----", "---", "---", "----") + "\r\n";
		int i = 1;
		
		Pattern pat_pos = Pattern.compile("CB(?=</td>)|SAF(?=</td>)|DB(?=</td>)");
		Pattern pat_num = Pattern.compile("<td class=\"tbdy\">\\d\\d</td><td><a href=\"/player|<td class=\"tbdy\"></td><td><a href=\"/player");
		Pattern pat_playername = Pattern.compile("<td><a href=\"(.*?)profile\">(.*?)</a></td>");
		Pattern pat_status = Pattern.compile("<td class=\"tbdy\">(.*?)</td><td class=\"ra\">[\\s]+TCKL");
		Pattern pat_tckl = Pattern.compile("<td class=\"ra\">[\\s]+TCKL+[\\s]+</td>[\\s]*<td class=\"tbdy\">(.*?)</td>");
		Pattern pat_sck = Pattern.compile("<td class=\"ra\">[\\s]+SCK+[\\s]+</td>[\\s]*<td class=\"tbdy\">(.*?)</td>");
		Pattern pat_int = Pattern.compile("<td class=\"ra\">[\\s]+INT+[\\s]+</td>[\\s]*<td class=\"tbdy\">(.*?)</td>");
		Pattern pat_team = Pattern.compile("<td class=\"tbdy1\"><a href=\"/teams/(.*?)</a></td></tr>");
		
		regex = new Regex(pat_pos, pat_num, pat_playername, pat_status, pat_tckl, pat_sck, pat_int, pat_team);
			
		url_string = "http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p=1&filter=defensiveback&conferenceAbbr=null";
		java.net.URL url = new java.net.URL(url_string);
		Scanner input = new Scanner(url.openStream());
		
		while(input.hasNext()) {
			String line = input.nextLine();
			string_concat = string_concat.concat(line);
		}
		input.close();
		
		Pattern pat_numpage = Pattern.compile("<span class=\"linkNavigation floatRight\">(.*?)next");
		Matcher matcher_numpage = pat_numpage.matcher(string_concat);
		if(matcher_numpage.find()) {
			String numpage = matcher_numpage.group(1);
			Pattern pat_page = Pattern.compile("Go to page [\\d]");
			Matcher matcher_page = pat_page.matcher(numpage);
			while(matcher_page.find()) {
				i++;
			}
		}
		
		for(int z = 1; z<=i; z++) {
			url_string2 = "http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p="+z+"&filter=defensiveback&conferenceAbbr=null";
			java.net.URL url_2 = new java.net.URL(url_string2);
			Scanner input_2 = new Scanner(url_2.openStream());
			while(input_2.hasNext()) {
				String line = input_2.nextLine();
				string_concat2 = string_concat2.concat(line);
			}
			
			
			Matcher matcher_pos = regex.getPos().matcher(string_concat2);
			Matcher matcher_num = regex.getNum().matcher(string_concat2);
			Matcher matcher_playername = regex.getLlayerName().matcher(string_concat2);
			Matcher matcher_status = regex.getStatus().matcher(string_concat2);
			Matcher matcher_tckl = regex.getTckl().matcher(string_concat2);
			Matcher matcher_sck = regex.getSck().matcher(string_concat2);
			Matcher matcher_int = regex.getIntt().matcher(string_concat2);
			Matcher matcher_team = regex.getTeam().matcher(string_concat2);
			
			while(matcher_pos.find() && matcher_playername.find()&&matcher_num.find()&&matcher_status.find()&&matcher_tckl.find()&&matcher_sck.find()&&matcher_int.find()&&matcher_team.find()) {
				pos = matcher_pos.group();
				num = matcher_num.group().substring(matcher_num.group().indexOf(">") + 1, matcher_num.group().indexOf("<", 2));
				playername = matcher_playername.group(2);
				status = matcher_status.group(1).substring(matcher_status.group(1).length()-3);
				tckl = matcher_tckl.group(1);
				sck = matcher_sck.group(1);
				inte = matcher_int.group(1);
				team = matcher_team.group(1).substring(matcher_team.group(1).indexOf(">")+1);
				result += String.format("%-10s %-10s %-25s %-10s %-10s %-10s %-10s %-10s\n", pos, num, playername, status, tckl, sck, inte, team) + "\r\n";
				try {
					File file_output = new File("NFLStat.txt");
					FileWriter fileWriter = new FileWriter(file_output);
					fileWriter.write(result);
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			input_2.close();
		}
		JOptionPane.showMessageDialog(null, "Done writting to text file");
	}
	// shows the output (scraped data) in a text-area 
	public String display(String display){
		return null;
	}
} //end class