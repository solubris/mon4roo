package solubris.mon4roo.sample;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import solubris.mon4roo.core.Monitor;

@Service
public class MonitoredServiceImpl implements MonitoredService {
	static private final Logger logger = LoggerFactory.getLogger(MonitoredServiceImpl.class);

	@Monitor
	@Override
	@Scheduled(fixedRate=1000)
//	@Transactional
	public void run() {
        Random random = new Random();
        
		logger.warn("running sample service");

        try {
            // Sleep a random length of time from 0.5-1s
            Thread.sleep(random.nextInt(500) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }		
    }

}
