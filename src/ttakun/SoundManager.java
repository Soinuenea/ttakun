package Txalaparta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	private static SoundManager instance;
	private static Map<File, Clip[]> listaSonidos;
	private static final int numPrecargas = 20;
	private static final int BUFFER_LENGTH = 1024;

	private SoundManager() {

	}

	public static SoundManager getInstance() {
		if (instance == null) {
			instance = new SoundManager();
			listaSonidos = new HashMap<File, Clip[]>();
		}
		return instance;
	}

	public void addSonido(File file) {
		if (listaSonidos.get(file) == null) {
			Clip precargas[] = preCargar(file, numPrecargas);
			listaSonidos.put(file, precargas);
		}
	}

	private Clip[] preCargar(File sourceFile, int numPrecargas) {
		byte[] sonidoPrecargado = null;
		AudioFormat formato = null;
		Clip myClip[] = new Clip[numPrecargas];
		/*
		 * Get the type of the source file. We need this information later to
		 * write the audio data to a file of the same type.
		 */
		AudioFileFormat fileFormat;
		try {
			fileFormat = AudioSystem.getAudioFileFormat(sourceFile);

			AudioFormat audioFormat = fileFormat.getFormat();

			/*
			 * Read the audio data into a memory buffer.
			 */
			AudioInputStream inputAIS;
			try {
				inputAIS = AudioSystem.getAudioInputStream(sourceFile);

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int nBufferSize = BUFFER_LENGTH * audioFormat.getFrameSize();
				byte[] abBuffer = new byte[nBufferSize];
				while (true) {
					// if (DEBUG) { out("trying to read (bytes): " +
					// abBuffer.length); }
					int nBytesRead = inputAIS.read(abBuffer);
					// if (DEBUG) { out("read (bytes): " + nBytesRead); }
					if (nBytesRead == -1) {
						break;
					}
					baos.write(abBuffer, 0, nBytesRead);
				}
				sonidoPrecargado = baos.toByteArray();
				formato = audioFormat;

				for (int i = 0; i < myClip.length; i++) {
					if (myClip[i] != null) {
						myClip[i].stop();
						myClip[i].drain();
						myClip[i].close();
					}
					final ByteArrayInputStream bais = new ByteArrayInputStream(
							sonidoPrecargado);
					final AudioInputStream sound = new AudioInputStream(bais,
							formato, sonidoPrecargado.length
									/ formato.getFrameSize());
					final DataLine.Info info = new DataLine.Info(Clip.class,
							formato);
					try {
						myClip[i] = (Clip) AudioSystem.getLine(info);
						myClip[i].open(sound);
					} catch (LineUnavailableException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return myClip;
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*
			 * Here's the byte array everybody wants.
			 */
		} catch (UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

	public void sonar(File file, float volumen) {
		Clip precargas[] = listaSonidos.get(file);
		for (Clip clip : precargas) {
			if (!clip.isActive()) {
				clip.stop();
				final FloatControl volume = (FloatControl) clip
						.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(volumen);

				clip.setFramePosition(0);
				clip.start();
				return;
			}
		}
	}

}
