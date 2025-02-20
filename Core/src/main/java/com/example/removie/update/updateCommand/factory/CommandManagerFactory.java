package com.example.removie.update.updateCommand.factory;

import com.example.removie.update.updateCommand.ChangeCommandBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 커맨드를 생성하여 최종적으로 반환하는 클래스입니다.
 * 또한 해당 클래스는 모든 파싱 및 비교 프로세스의 최상단 트리거 역할을 합니다.
 *
 * <h3>아키텍처 개요:</h3>
 * <ul>
 *     <li>커맨드(Command) → 비교 프로세스(Compare Process) → 파싱 프로세스(Parsing Process) 순으로 실행</li>
 *     <li>각각의 커맨드는 {@link CommandFactory} 에 의해 생성됨</li>
 * </ul>
 *
 * <p> 상세한 아키텍처 설명은 <b>README</b> 파일을 참고하세요.</p>
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
public class CommandManagerFactory {
    private final List<CommandFactory> commandFactories;

    @Autowired
    public CommandManagerFactory(List<CommandFactory> commandFactories) {
        this.commandFactories = commandFactories;
    }

    /**
     * {@link ChangeCommandBundle}을 생성하여 반환합니다.
     *
     * <p>
     * 내부적으로 모든 {@link CommandFactory}를 순회하며,
     * 각 팩토리에서 생성된 커맨드를 {@link ChangeCommandBundle}에 추가합니다.
     * </p>
     *
     * @return 여러 개의 커맨드를 포함하는 {@link ChangeCommandBundle} 객체
     */
    public ChangeCommandBundle createChangeCommandManager(){
        ChangeCommandBundle changeCommandBundle = new ChangeCommandBundle();

        for(CommandFactory commandFactory : commandFactories){
            changeCommandBundle.addChangeCommand(commandFactory.createCommand());
        }

        return changeCommandBundle;
    }
}
