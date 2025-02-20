package com.example.removie.update;

import com.example.removie.kobis.exception.ParsingResultEmptyException;
import com.example.removie.movie.NewReleaseMap;
import com.example.removie.kobis.KOBISMovieReleaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * 파싱 결과를 제공하는 서비스입니다, {@code getParsingResult()} 와 {@code getCurrentMovieList()}
 * 는 모든 프로세스가 시작되기 전 우선적으로 파싱 되는 결과입니다. 각 클래스에서 {@link KOBISMovieReleaseService}를
 * 사용하지 않고 해당 클래스를 사용하는 이유는 캐싱 시점을 확정하기 위해서입니다 이는 프로젝트에 안정성을 높이기 위한 결정입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
public class CompareBaseData implements CompareTargetProvider{
    private final KOBISMovieReleaseService kobisMovieReleaseService;
    private final Logger logger = LoggerFactory.getLogger(CompareBaseData.class);

    public CompareBaseData(KOBISMovieReleaseService kobisMovieReleaseService) {
        this.kobisMovieReleaseService = kobisMovieReleaseService;
    }

    @Cacheable(value = "parsingResult")
    public NewReleaseMap getParsingResult(){

        NewReleaseMap newReleaseMap = kobisMovieReleaseService.getReleaseMap();
        validateParsingResult(newReleaseMap);
        return newReleaseMap;
    }

    private void validateParsingResult(NewReleaseMap newReleaseMap){
        if(newReleaseMap.isEmpty()){
            logger.error("Parsing Process 오류 - ParsingResult 캐시 실패 : ParsingResult 가 비어 있음");
            throw new ParsingResultEmptyException("로직 오류 가능성 있음 - ParsingResult 캐시 실패 : ParsingResult 가 비어 있음");
        }
    }

}
