package com.example.removie.update;

import com.example.removie.update.updateCommand.ChangeCommandBundle;
import com.example.removie.update.updateCommand.factory.CommandManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



/**
 * 모든 프로세스에 파사드 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
@Endpoint(id = "run-update")
public class UpdateServiceImpl implements UpdateService{
    private final Logger logger = LoggerFactory.getLogger(UpdateServiceImpl.class);

    private final CommandManagerFactory commandManagerFactory;
    private final UpdateCommandExecutor updateCommandExecutor;
    private final CinemaUpdateService cinemaUpdateService;
    private final UpdateMessagingService updateMessagingService;


    public UpdateServiceImpl(CommandManagerFactory commandManagerFactory, UpdateCommandExecutor updateCommandExecutor, CinemaUpdateService cinemaUpdateService, UpdateMessagingService updateMessagingService) {
        this.commandManagerFactory = commandManagerFactory;
        this.updateCommandExecutor = updateCommandExecutor;
        this.cinemaUpdateService = cinemaUpdateService;
        this.updateMessagingService = updateMessagingService;
    }


    /**
     * {@link com.example.removie.aws.LambdaHandler} 에 의해 실행되며 전체 프로세스가 시작되는 메서드입니다.
     */
    @Override
    public void runUpdate(){
        movieUpdate();
        cinemaUpdate();
        updateNotify();
    }


    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduledUpdate() {
        logger.info("Scheduled run");
        runUpdate();
    }

    @ReadOperation
    public void actuatorUpdate() {
        logger.info("actuator run");
        runUpdate();
    }


    private void movieUpdate(){
        try{
            ChangeCommandBundle commandBundle = commandManagerFactory.createChangeCommandManager();
            if(!commandBundle.isEmpty()) updateCommandExecutor.updateProcess(commandBundle);

        }catch (Exception e){
            logFatalError(e);
        }
    }

    private void cinemaUpdate(){
        try{
            cinemaUpdateService.cinemaDataUpdate();
        }catch (Exception e){
            logFatalError(e);
        }
    }

    private void updateNotify(){
        updateMessagingService.sendMessage();
    }

    private void logFatalError(Exception e){
        logger.info("""
                프로세스 종료
                예외 내용 :
                {}
                """, e.getMessage(), e);
    }

}