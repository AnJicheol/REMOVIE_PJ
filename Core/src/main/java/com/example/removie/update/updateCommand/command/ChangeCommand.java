package com.example.removie.update.updateCommand.command;


/**
 * 커맨드 패턴에 커맨드 객체들이 사용하는 인터페이스입니다, update()는 DB 통신 과정을 동반됩니다.
 * 해당 프로젝트에서 커맨드 패턴은 트랜잭션을 콜 체인 상단에 위치 시키고 시간을 단축시키기 위함입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
public interface ChangeCommand {
    void update(Integer version);
    boolean isEmpty();
}
