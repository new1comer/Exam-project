package com.example.logincheck.check.service.impl;


import com.example.logincheck.check.mapper.StuMapper;
import com.example.logincheck.check.pojo.Stu;
import com.example.logincheck.check.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sound.sampled.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private StuMapper stumapper;

    @Override
    public Stu login(Stu stu) {
        return stumapper.getByUsernameAndPassword(stu);
    }

    @Override
    public void sign(Stu stu) {
        stumapper.createNewStu(stu);
    }

    @Override
    public void recordAndPlay(String outputFile) {
        float SAMPLE_RATE = 44100;
        int SAMPLE_SIZE_BITS = 16;
        int CHANNELS = 1;
        boolean SIGNED = true;
        boolean BIG_ENDIAN = true;
        long RECORDING_TIME = 10000;
        try {
            Mixer.Info[] mxierInfo = AudioSystem.getMixerInfo();
            Mixer mixer = AudioSystem.getMixer(mxierInfo[0]);

            AudioFormat audioFormat = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_BITS, CHANNELS, SIGNED, BIG_ENDIAN);

            DataLine.Info daraLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            TargetDataLine targetDataLine = (TargetDataLine) mixer.getLine(daraLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);

            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(outputFile));

            audioInputStream.close();

            AudioInputStream recordedaudio = AudioSystem.getAudioInputStream(new File(outputFile));
            Clip clip = AudioSystem.getClip();
            clip.open(recordedaudio);
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.stop();
            clip.close();
            recordedaudio.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



