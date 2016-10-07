import javax.sound.midi.*;

public class MiniMiniMusicApp{

	public static void main(String[] args){
		MiniMiniMusicApp mini = new MiniMiniMusicApp();
		mini.play();
	}//close main

    public void play(){
	try{
		Sequencer player = MidiSystem.getSequencer();
		player.open();

		Sequence seq = new Sequence(Sequence.PPQ, 4);

		Track track = seq.createTrack();

		//Midi events
		ShortMessage a = new ShortMessage();
		a.setMessage(144, 1, 44, 100);
		MidiEvent noteOn = new MidiEvent(a, 1);
		track.add(noteOn);

		ShortMessage c = new ShortMessage();
		c.setMessage(192, 1, 102, 0);
		MidiEvent noteOns = new MidiEvent(c, 1);
		track.add(noteOns);

		ShortMessage b = new ShortMessage();
		b.setMessage(128, 1, 44, 100);
		MidiEvent noteOff = new MidiEvent(b, 16);
		track.add(noteOff);

		ShortMessage d = new ShortMessage();
		d.setMessage(128, 1, 102, 0);
		MidiEvent noteOffs = new MidiEvent(d, 32);
		track.add(noteOffs);

		player.setSequence(seq);

		player.start();

	  } catch (Exception ex){
	 	ex.printStackTrace();
	  }
    }//close play
}//close class
