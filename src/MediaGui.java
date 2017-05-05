import javax.swing.JFrame;
import javax.swing.JLabel;

public class MediaGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	JLabel label;
	
	public MediaGui (){
		super("A frame");
		this.frame = new JFrame();
		frame.setSize(200, 100);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void add_text(String st){
		this.label = new JLabel(st);
		frame.add(label);
	}
	
}
